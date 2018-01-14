package UIHandeller;

import ReversiFiles.Board;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

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
    private ClickListener clickListener;
    private final int distanceFromCellBorders = 7;
    private final int heightDivider = 8;
    private final int heightMultiplier = 35;
    private final int clientMessagesColumnWidth = 120;
    private final int storkeRectangleWidth = 2;
    /**
     * constructor.
     *
     * @param board   - the reversi board game
     * @param size    - size of board
     * @param player1 - color of player 1
     * @param player2 - color of player 2
     */
    public BoardGame(Board board, int size, Color player1, Color player2, ClickListener clickListener) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.clickListener = clickListener;
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
        // create HBox to put inside the grid cell. This Hbox will contain a circle.
        HBox hbox = new HBox();
        hbox.setPrefWidth(cellWidth);
        hbox.setPrefHeight(cellHeight);
        this.add(hbox, j, i);
        double radius = 0.0;
        if (cellWidth < cellHeight) {
            radius = cellWidth / 2 - distanceFromCellBorders;
        } else {
            radius = cellHeight / 2 - distanceFromCellBorders;
        }
        Circle circle = new Circle(radius, player);
        Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(storkeRectangleWidth);
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
        // fix height length
        double height = this.getPrefHeight() - ((this.board.getSize()/heightDivider) * heightMultiplier);
        double width = this.getPrefWidth() - clientMessagesColumnWidth;
        int size = board.getSize();
        this.cellHeight = (double) height / (double) size;
        this.cellWidth = (double) width / (double) size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // draw washer of the Player 1
                if (board.getCell(i, j) == player1) {
                    this.addWasher(i, j, player1);
                    // draw washer of Player 2
                } else if (board.getCell(i, j) == player2) {
                    this.addWasher(i, j, player2);
                    // empty cells
                } else {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.TRANSPARENT);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(storkeRectangleWidth);
                    this.add(rectangle, j, i);
                }
                BorderPane pane =new BorderPane();
                this.add(pane, j,i);
                final int ii =i;
                final int jj = j;
                pane.setOnMouseClicked(event -> {this.clickListener.clickEvent(ii,jj);});

            }
        }
        // paints in green cells of possible moves to the current player
        if (possibleMoves != null) {
            if(possibleMoves.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please press on board to continue!");
                alert.showAndWait();
            }else {
                for (Pair<Integer, Integer> pair : possibleMoves) {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight, Color.LIGHTGREEN);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(storkeRectangleWidth);
                    this.add(rectangle, pair.getValue(), pair.getKey());
                    BorderPane pane = new BorderPane();
                    this.add(pane, pair.getValue(), pair.getKey());
                    final int ii = pair.getKey();
                    final int jj = pair.getValue();
                    pane.setOnMouseClicked(event -> {
                        this.clickListener.clickEvent(ii, jj);
                    });
                }

            }
        }
    }

}