package sample;

import ReversiFiles.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    private int size;
    private BoardGame boardGame;
    private Color player1;
    private Color player2;
    private Color currentPlayer;
    private ClientMessagesPrinter clientMessagesPrinter;
    private IGame game;
    private Board board;

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
        this.boardGame = new BoardGame(board,size, player1, player2);
        this.clientMessagesPrinter = new ClientMessagesPrinter(player1, player2);
        boardGame.setPrefWidth(400);
        boardGame.setPrefHeight(400);
        root.getChildren().add(0, boardGame);
        boardGame.draw();
        this.lblCurrPlayer.setText(this.getCurrPlayerText());
        this.lblUserMessages.setText(this.clientMessagesPrinter.printPossibleMoves(this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent())));
        this.lblScores1.setText("0");
        this.lblScores2.setText("0");
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            boardGame.setPrefWidth(boardNewWidth);
            boardGame.draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            boardGame.setPrefHeight(newValue.doubleValue());
            boardGame.draw();
        });
    }

    /**
     * btnMoveClick func.
     * @param e - MouseEvent obj.
     */
    @FXML
    protected void btnMoveClick(MouseEvent e) {
        Stage stage = (Stage) root.getScene().getWindow();
        int x=(int)e.getX();
        int y=(int)e.getY();
        double cellHeight =(double) this.boardGame.cellHeight();
        double cellWidth = (double)this.boardGame.cellWidth();
        int xPos=0;
        int yPos=0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (xPos<=x && x<=xPos+cellWidth &&yPos<=y && y<=yPos+cellHeight) {
                    if (this.boardGame.getBoard().getCell(i,j) == null) {
                        IGame.Status status = this.game.playOneTurn(new Pair(i,j));
                        this.boardGame.draw();
                        switch (status) {
                            case GameOver:
                            case Tie:
                                String text = this.clientMessagesPrinter.announceWinner(this.board);
                                this.presentAlert(text);
                                this.lblCurrPlayer.setText("");
                                this.lblUserMessages.setText("");
                                break;
                            case NoPossibleMovesForBothPlayers:
                                this.lblUserMessages.setText("");
                                String text1 = this.clientMessagesPrinter.noPossibleMovesForBothPlayers();
                                String text2 = this.clientMessagesPrinter.announceWinner(this.board);
                                this.presentAlert(text1 + " " + text2);
                                break;
                            case NoPossibleMoves:
                                this.lblUserMessages.setText(this.clientMessagesPrinter.noPossibleMovesForCurrentPlayer());
                                this.presentAlert(this.clientMessagesPrinter.noPossibleMovesForCurrentPlayer());
                                this.swapPlayers();
                                break;

                            case NotValidMove:
                                break;

                            case Playing:
                                Rectangle rectangle = new Rectangle(cellWidth, cellHeight, this.currentPlayer);
                                rectangle.setStroke(Color.BLACK);
                                rectangle.setStrokeWidth(2);
                                this.boardGame.add(rectangle, j, i);
                                this.swapPlayers();
                                break;

                        }
                        this.lblScores1.setText(String.valueOf(this.game.getScoresPlayer(player1)));
                        this.lblScores2.setText(String.valueOf(this.game.getScoresPlayer(player2)));
                        this.lblCurrPlayer.setText(this.getCurrPlayerText());
                        break;

                    }
                }
                xPos+=cellWidth;
            }
            xPos = 0;
            yPos+=cellHeight;
        }
    }

    /**
     * updateBoardByUserSettings func.
     * update the board by user settings.
     */
    private void updateBoardByUserSettings(){
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("src/settings.txt"));
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
            if (settingsCategories[2] == "Player 1") {
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
        this.lblUserMessages.setText(this.clientMessagesPrinter.printPossibleMoves(moves));
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
}