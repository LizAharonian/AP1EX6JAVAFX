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

        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int size = board.getSize();

        int cellHeight = height / size;
        int cellWidth = width / size;

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
}


