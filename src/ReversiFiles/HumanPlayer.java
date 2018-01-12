package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.util.List;

/**
 * HumanPlayer class.
 */
public class HumanPlayer extends Player {
    // members
    private Printer printer;

    /**
     * ReversiFiles.HumanPlayer ctor.
     * @param type - Color of player
     */
    public HumanPlayer(Color type) {
        super(type);
    }

    /**
     * Checks if a user input of a move is among the user possible moves.
     * @param options vector of all possible moves.
     * @param row row
     * @param col col
     * @return true - a possible move, else- false.
     */
    public boolean isValidMove(List< Pair<Integer, Integer> > options, int row, int col) {
        for (int i = 0; i < options.size(); i++) {
            // user move exists in the options vector.
            if (options.get(i).getKey() == row && options.get(i).getValue() == col) {
                return true;
            }
        }
        return false;
    }
}

