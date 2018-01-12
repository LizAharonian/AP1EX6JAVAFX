package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.util.List;

/**
 * GameLogic class.
 */
public interface GameLogic {

    /**
     * prints end stores in a array the possible moves of a player in the game.
     * @param player ReversiFiles.Player (Color)
     * @param opponent  Opponent (Color)
     * @return possible moves array
     */
    public List<Pair<Integer,Integer>> possibleMoves(Color player, Color opponent);
    /**
     * gets form the player his next move choice and make the changes in the board according
     * to the move (row,col).
     * @param row row
     * @param col col
     * @param player ReversiFiles.Player (Color)
     * @param opponent Opponent (Color)
     */
    public boolean makeMove(int row, int col, Color player, Color opponent);

}
