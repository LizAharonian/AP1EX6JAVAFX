package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by lizah on 08/01/2018.
 */
public interface GameLogic {


    /**
     * prints end stores in a array the possible moves of a player in the game.
     * @param moves pair<int, int> array that stores all the possible cells for player's next move.
     * @param player ReversiFiles.Player (Cell enum)
     * @param opponent  Opponent (Cell enum)
     * @return possible moves array
     */
    public List<Pair<Integer,Integer>> possibleMoves(Color player, Color opponent);
    /**
     * gets form the player his next move choice and make the changes in the board according
     * to the move (row,col).
     * @param row row
     * @param col col
     * @param player ReversiFiles.Player (Cell enum)
     * @param opponent Opponent (Cell enum)
     */
    public boolean makeMove(int row, int col, Color player, Color opponent);
    /**
     * returns ths game of the board.
     * @return ReversiFiles.Board
     */

    /**
     * returns score of current player : #cells of current player type - #cells of opponent player type.
     * @param current ReversiFiles.Board::Cell (Black/White)
     * @param opponent ReversiFiles.Board::Cell (Black/White)
     * @return score
     */
    public int getScores(Color current, Color opponent);
}
