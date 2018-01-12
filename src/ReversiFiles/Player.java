package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

/**
 * ReversiFiles.Player Interface.
 * ReversiFiles.Player can be human or computer.
 */
abstract public class Player {
    //members
    protected Color type;

    /**
     * ReversiFiles.Player c'tor
     * @param type ReversiFiles.Board::Cell - Can be Black or White.
     */
    Player(Color type) {
        this.type = type;
    }

    /**
     * returns the player type.
     * @return Color of player
     */
    Color getType() {
        return this.type;
    }

    /**
     * isValidMove func
     * @param options - list of options
     * @param row - row index
     * @param col - col index
     * @return - true or false
     */
    abstract boolean isValidMove(List< Pair<Integer, Integer> > options, int row, int col);


    /**
     * Sets the type of the player
     * @param newType ReversiFiles.Board:Cell
     */
    void setType(Color newType) {
        this.type = newType;
    }

}
