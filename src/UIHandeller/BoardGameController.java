package UIHandeller;

import ReversiFiles.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.util.Pair;

/**
 * BoardGameController class.
 * manages our game board UI
 */
public class BoardGameController implements Initializable{
    //members
    @FXML
    private HBox root;
    @FXML
    private Label lblScores1;
    @FXML
    private Label lblScores2;
    @FXML
    private Label lblCurrPlayer;
    @FXML
    private Label lblUserMessages;
    @FXML
    private Button btnEndGame;
    private int size;
    private BoardGame boardGame;
    private Color player1;
    private Color player2;
    private Color currentPlayer;
    private ClientMessagesPrinter clientMessagesPrinter;
    private IGame game;
    private Board board;
    ClickListener clickListener;

    /**
     * initialize func.
     * @param location - url obj
     * @param resources - ResourceBundle obj
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateBoardByUserSettings();
        this.board = new Board(size,player1, player2);
        this.game = new TwoPlayersOneComputerGame(board,player1, player2,currentPlayer );
        this.clickListener = new ClickListener(this);
        this.boardGame = new BoardGame(board,size, player1, player2,clickListener);
        this.clientMessagesPrinter = new ClientMessagesPrinter(player1, player2);
        boardGame.setPrefWidth(400);
        boardGame.setPrefHeight(400);
        root.getChildren().add(0, boardGame);
        // draw possible moves for start player
        List<Pair<Integer, Integer>> possibleMoves = this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent());
        boardGame.draw(possibleMoves);
        this.lblCurrPlayer.setText(this.getCurrPlayerText());
        this.lblCurrPlayer.setTextFill(currentPlayer);
        this.lblUserMessages.setText("");
        this.lblScores1.setText("0");
        this.lblScores2.setText("0");
        // handling window resize
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            boardGame.setPrefWidth(boardNewWidth);
            boardGame.draw(possibleMoves);
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            boardGame.setPrefHeight(newValue.doubleValue());
            boardGame.draw(possibleMoves);
        });
    }


    public void handleClick(int i, int j) {
        if (this.boardGame.getBoard().getCell(i, j) == null) {
            IGame.Status status = this.game.playOneTurn(new Pair(i, j));
            switch (status) {
                case GameOver:
                case Tie:
                    this.boardGame.addWasher(i, j, currentPlayer);
                    this.boardGame.draw(null);
                    String text = this.clientMessagesPrinter.announceWinner(this.board);
                    this.presentAlert(text);
                    this.lblCurrPlayer.setText("");
                    this.lblUserMessages.setText("");
                    break;
                case NoPossibleMovesForBothPlayers:
                    String text1 = this.clientMessagesPrinter.noPossibleMovesForBothPlayers();
                    String text2 = this.clientMessagesPrinter.announceWinner(this.board);
                    this.presentAlert(text1 + " " + text2);
                    break;
                case NoPossibleMoves:
                    this.lblUserMessages.setText(this.clientMessagesPrinter.noPossibleMovesForCurrentPlayer());
                    this.presentAlert(this.clientMessagesPrinter.noPossibleMovesForCurrentPlayer());
                    this.swapPlayers();
                    this.boardGame.draw(this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent()));
                    break;

                case NotValidMove:
                    break;

                case Playing:
                    this.boardGame.addWasher(i, j, currentPlayer);
                    this.swapPlayers();
                    this.boardGame.draw(this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent()));
                    break;

            }
            this.lblScores1.setText(String.valueOf(this.game.getScoresPlayer(player1)));
            this.lblScores2.setText(String.valueOf(this.game.getScoresPlayer(player2)));
            this.lblCurrPlayer.setText(this.getCurrPlayerText());
            this.lblCurrPlayer.setTextFill(this.currentPlayer);

        }
    }

    /**
     * updateBoardByUserSettings func.
     * update the board by user settings.
     */
    private void updateBoardByUserSettings(){
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("settings.txt"));
            String line;
            line = file.readLine();
            if (line == null) {
                throw new Exception("Empty File");
            }
            String settingsCategories[] = line.split(",");
            // four settings categories: Color of player 1, Color of player 2, Start player and Board size
            if (settingsCategories.length != 4) {
                throw new Exception("Missing Settings");
            }
            // sets settings from the settings file
            this.player1 = Color.web(settingsCategories[0]);
            this.player2 = Color.web(settingsCategories[1]);
            if (settingsCategories[2].equals("Player 1")) {
                this.currentPlayer = player1;
            } else {
                this.currentPlayer = player2;
            }
            this.size = Integer.parseInt(settingsCategories[3]);
            file.close();
        } catch (Exception e) {
            System.out.println("Problem reading file.");
            System.exit(1);
        }
    }

    /**
     * presentAlert func.
     * @param text - text to be printed
     */
    private void presentAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * swapPlayers func.
     * swaps the players.
     */
    private void swapPlayers(){
        this.currentPlayer = this.game.getCurr();
        List<Pair<Integer,Integer>> moves = this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent());
        this.lblUserMessages.setText("");
    }

    /**
     * getCurrPlayerText text.
     * @return curr player name
     */
    private String getCurrPlayerText() {
        if(currentPlayer == player1) {
            return "Player 1";
        } else  {
            return "Player 2";
        }
    }

    @FXML
    /**
     * Go back to menu.
     */
    private void btnEndGameClick() {
        try {
            Stage stage = (Stage) btnEndGame.getScene().getWindow();
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex) {

        }
    }
}