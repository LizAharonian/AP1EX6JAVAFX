package sample;

import ReversiFiles.Board;
import ReversiFiles.ClientMessagesPrinter;
import ReversiFiles.HumanPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.scene.control.Label;
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
    private Color Player2;
    private Color currentPlayer;
    private ClientMessagesPrinter clientMessagesPrinter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //todo: add reading settiond from file
        this.size = 8;
        this.player1 = Color.ORANGE;
        this.Player2 = Color.WHITE;
        this.currentPlayer = player1;


        this.boardGame = new BoardGame(size, player1, Player2);
        this.clientMessagesPrinter = new ClientMessagesPrinter(player1, Player2);
        boardGame.setPrefWidth(400);
        boardGame.setPrefHeight(400);
        /*this.boardHeight=400;
        this.boardWidth=400;*/
        root.getChildren().add(0, boardGame);
        boardGame.draw();
        this.lblUserMessages.setText(this.clientMessagesPrinter.announceWhoPlayNow(new HumanPlayer(currentPlayer)));
      //  this.lblUserMessages.setText();
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
           /* this.boardWidth=boardNewWidth;*/
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
        int xPos=0;
        int yPos=0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (xPos<=x && x<=xPos+cellWidth &&yPos<=y && y<=yPos+cellHeight) {
                    if (this.boardGame.getBoard().getCell(i,j) == null) {
                        this.boardGame.add(new Rectangle(cellWidth, cellHeight, this.currentPlayer), j, i);
                        //todo: to be added for update board vals- important!!!
                        //todo: check if valid option(from certein moves)
                        //this.boardGame.getBoard().setCell();
                        this.lblScores1.setText("1");
                        this.lblScores2.setText("2");
                        this.lblCurrPlayer.setText("lalalala");
                        break;

                    }
                }
                xPos+=cellWidth;
            }
            xPos = 0;
            yPos+=cellHeight;
        }



    }


}