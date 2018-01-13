package UIHandeller;

import ReversiFiles.Board;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.w3c.dom.css.Rect;

import java.io.IOException;
import java.util.List;

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
     *
     * @param board   - the reversi board game
     * @param size    - size of board
     * @param player1 - color of player 1
     * @param player2 - color of player 2
     */
    public BoardGame(Board board, int size, Color player1, Color player2) {
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
        double height = this.getPrefHeight() - 20;
        double width = this.getPrefWidth() - 120;
        int size = board.getSize();
        this.cellHeight = (double) height / (double) size ;
        this.cellWidth = (double) width / (double) size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == player1) {
                    this.addWasher(i, j, player1);
                } else if (board.getCell(i, j) == player2) {
                    this.addWasher(i, j, player2);
                } else {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.TRANSPARENT);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(2);
                    this.add(rectangle, j, i);
                }
            }
        }
    }

    /**
     * getBoard func.
     *
     * @return the board game
     */
    Board getBoard() {
        return this.board;
    }

    /**
     * cellWidth func.
     *
     * @return cell width
     */
    public double cellWidth() {

        return this.cellWidth;
    }

    /**
     * cellHeight func.
     *
     * @return cell height
     */
    public double cellHeight() {
        return this.cellHeight;
    }

    /**
     * Adds whaser to the board
     * @param i row
     * @param j coloumn
     * @param player color of the washer
     */
    public void addWasher(int i, int j, Color player) {
        HBox hbox = new HBox();
        hbox.setPrefWidth(cellWidth);
        hbox.setPrefHeight(cellHeight);
        this.add(hbox, j, i);
        double radius = 0.0;
        if (cellWidth < cellHeight) {
            radius = cellWidth / 2 - 7;
        } else {
            radius = cellHeight / 2 - 7;
        }
        Circle circle = new Circle(radius, player);
        Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        hbox.getChildren().add(circle);
        hbox.setAlignment(Pos.CENTER);
        this.add(rectangle, j, i);
    }

    /**
     * draw func.
     * draws the board game and fills in green cells that are possibles moves for the current player.
     * @param possibleMoves possible move for current player.
     */
    public void draw(List<Pair<Integer, Integer>> possibleMoves) {
        this.getChildren().clear();
        double height = this.getPrefHeight();
        double width = this.getPrefWidth() - 120;
        int size = board.getSize();
        this.cellHeight = (double) height / (double) size;
        height = this.getPrefHeight() - this.cellHeight;
        this.cellHeight = (double) height / (double) size;
        this.cellWidth = (double) width / (double) size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == player1) {
                    this.addWasher(i, j, player1);
                } else if (board.getCell(i, j) == player2) {
                    this.addWasher(i, j, player2);
                } else {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.TRANSPARENT);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(2);
                    this.add(rectangle, j, i);
                }
            }
        }
        for (Pair<Integer, Integer> pair : possibleMoves) {
            Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.LIGHTGREEN);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(2);
            this.add(rectangle, pair.getValue(), pair.getKey());
        }
    }
}