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
 * Created by lizah on 11/01/2018.
 */
public class BoardGameController implements Initializable{

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



    /* private double boardHeight;
    private double boardWidth;*/
    private int size;
    private BoardGame boardGame;
    private Color player1;
    private Color player2;
    private Color currentPlayer;
    private ClientMessagesPrinter clientMessagesPrinter;
    private IGame game;
    private Board board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // addition
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("src/settings.txt"));
            String line;
            //StringBuffer inputBuffer = new StringBuffer();
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
        // end addition
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
            /*this.boardHeight = newValue.doubleValue();*/
            boardGame.setPrefHeight(newValue.doubleValue());
            boardGame.draw();
        });
    }
    @FXML
    protected void btnMoveClick(MouseEvent e) {
        Stage stage = (Stage) root.getScene().getWindow();
        int x=(int)e.getX();
        int y=(int)e.getY();

        double cellHeight =(double) this.boardGame.cellHeight();//getHeight()/ (double)size;
        double cellWidth = (double)this.boardGame.cellWidth();// / (double)size;
       // List<Pair<Integer,Integer>> moves = this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent());

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
                                //todo: add alert;
                                String text = this.clientMessagesPrinter.announceWinner(this.board);

                              this.presentAlert(text);
                                this.lblUserMessages.setText("");
                                break;
                            case NoPossibleMoves:
                                this.presentAlert(this.clientMessagesPrinter.noPossibleMovesForCurrentPlayer());
                                this.swapPlayers();
                                break;
                            case NoPossibleMovesForBothPlayers:
                                this.lblUserMessages.setText(this.clientMessagesPrinter.noPossibleMovesForBothPlayers());
                                //todo: add alert;
                                String text1 = this.clientMessagesPrinter.announceWinner(this.board);
                                this.presentAlert(text1);
                                break;
                            case NotValidMove:
                                //todo: add alert;
                                this.presentAlert("Not valid move!");

                                break;

                            case Playing:
                                this.boardGame.add(new Rectangle(cellWidth, cellHeight, this.currentPlayer), j, i);
                                this.swapPlayers();

                                break;

                        }
                        //todo: to be added for update board vals- important!!!
                        //todo: check if valid option(from certein moves)
                        //this.boardGame.getBoard().setCell();
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

    private void presentAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
    private void swapPlayers(){
        this.currentPlayer = this.game.getCurr();
        List<Pair<Integer,Integer>> moves = this.game.getGameLogic().possibleMoves(currentPlayer, game.getOpponent());
        this.lblUserMessages.setText(this.clientMessagesPrinter.printPossibleMoves(moves));
        //this.currentPlayer = this.game.getOpponent();
    }
    private String getCurrPlayerText() {
        if(currentPlayer == player1) {
            return "Player 1";

        } else  {
            return "Player 2";
        }
    }



}