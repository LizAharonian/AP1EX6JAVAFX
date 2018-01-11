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


    public BoardGame(int size) {
        this.board = new Board(size);
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
                if (board.getCell(i, j) == Board.Cell.Black) {
                    this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);
                } else {

                    this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);

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


