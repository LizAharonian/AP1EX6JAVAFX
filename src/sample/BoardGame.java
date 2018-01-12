package sample;

import ReversiFiles.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

/**
 * BoardGame class.
 * represent the reversi board game.
 */
public class BoardGame extends GridPane {
    //members
    private Board board;
    private double cellHeight;
    private double cellWidth;
    private Color player1;
    private Color player2;

    /**
     * constructor.
     * @param board - the reversi board game
     * @param size - size of board
     * @param player1 - color of player 1
     * @param player2 - color of player 2
     */
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

    /**
     * draw func.
     * draws the board game.
     */
    public void draw() {
        this.getChildren().clear();
        double height = this.getPrefHeight();
        double width = this.getPrefWidth() - 120;
        int size = board.getSize();
        this.cellHeight = (double) height /(double) size;
        this.cellWidth = (double) width /(double) size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == player1) {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, player1);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(2);
                    this.add(rectangle, j, i);
                } else if (board.getCell(i, j) == player2) {

                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, player2);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(2);
                    this.add(rectangle, j, i);

                }else {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.LAVENDER);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(2);
                    this.add(rectangle, j, i);
                }
            }
        }
    }

    /**
     * getBoard func.
     * @return the board game
     */
    Board getBoard() {
        return this.board;
    }

    /**
     * cellWidth func.
     * @return cell width
     */
    public double cellWidth(){

        return this.cellWidth;
    }

    /**
     * cellHeight func.
     * @return cell height
     */
    public double cellHeight(){
        return this.cellHeight;
    }
}


