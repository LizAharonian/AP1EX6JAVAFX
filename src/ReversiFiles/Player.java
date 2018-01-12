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
     * @return ReversiFiles.Board::Cell (Black/White)
     */
    Color getType() {
        return this.type;
    }

    /**
     * Sets the type of the player
     * @param newType ReversiFiles.Board:Cell
     */
    void setType(Color newType) {
        this.type = newType;
    }

    /**
     * display to the player all his possible moves and gets an input of a wished move.
     * @param options vector of possible moves.
     * @param board ReversiFiles.Board
     * @param currentCellType ReversiFiles.Board::Cell (White/Black)
     * @param opponentCellType ReversiFiles.Board::Cell (White/Black)
     * @return pair<int, int> of cell in the board - the desired move of the player.
     */
   /* abstract Pair<Integer, Integer> getInput(List< Pair<Integer,Integer> > options, final Board board,
                                             Board.Cell currentCellType, Board.Cell opponentCellType) throws Exception;*/
}
