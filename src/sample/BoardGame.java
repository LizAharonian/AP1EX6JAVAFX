package sample;

import ReversiFiles.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Created by lizah on 11/01/2018.
 */
public class BoardGame extends GridPane {

    private Board board;
    private double cellHeight;
    private double cellWidth;
    private Color player1;
    private Color player2;


    public BoardGame(Board board,int size, Color player1, Color player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("ReversiBoard1.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void draw() {
        this.getChildren().clear();

        double height = this.getPrefHeight();
        double width = this.getPrefWidth() - 120;
        int size = board.getSize();

        this.cellHeight = (double) height /(double) size;
        this.cellWidth = (double) width /(double) size;
        int u;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == player1) {
                    this.add(new Rectangle(cellWidth, cellHeight, player1), j, i);
                } else if (board.getCell(i, j) == player2) {

                    this.add(new Rectangle(cellWidth, cellHeight, player2), j, i);

                }else {
                    //todo: if user checks this color we are sucks
                    this.add(new Rectangle(cellWidth, cellHeight, Color.LAVENDER), j, i);
                }
            }
        }
    }
    Board getBoard() {
        return this.board;
    }

    public  double cellWidth(){

        return this.cellWidth;
    }
    public double cellHeight(){
       // int height = (int) this.getPrefHeight();
        return this.cellHeight;
    }
}


