package ReversiFiles;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by אורי on 08/01/2018.
 */
public class HumanPlayer extends Player {
    // members
    private Printer printer;

    /**
     * ReversiFiles.HumanPlayer ctor.
     * @param type ReversiFiles.Board::Cell (White/Black)
     */
    HumanPlayer(Board.Cell type, Printer printer) {
        super(type);
        this.printer = printer;
    }
    /**
     * display to the human player all his possible moves and gets an input of a wished move.
     * @param options vector of possible moves.
     * @param board ReversiFiles.Board
     * @param currentCellType ReversiFiles.Board::Cell (White/Black)
     * @param opponentCellType ReversiFiles.Board::Cell (White/Black)
     * @return pair<int, int> of cell in the board - the desired move of the player.
     */
    @Override
    Pair<Integer, Integer> getInput(List< Pair<Integer, Integer> > options, Board board, Board.Cell currentCellType,
                                    Board.Cell opponentCellType) throws Exception {
        this.printer.printPossibleMoves(options);
        String move;
        int row = 0, col = 0;
        while (true) {
            move = this.printer.enterYourMove();
            try {
                long index = move.indexOf(',');
                if (index == -1) {
                    throw new Exception("Bad argument");
                }
                row = Integer.parseInt(move.substring(0, (int)index));
                String coll = move.substring((int)index + 1 , move.length());
                col = Integer.parseInt(coll);
            } catch (Exception ex) {
                this.printer.illegalMoveInputFormat();
                continue;
            }
            row--;
            col--;
            if (isValidMove(options, row, col)) {
                break;
            } else {
                this.printer.illegalMoveInput();
            }
        }
        return new Pair(row, col);
    }

    /**
     * Checks if a user input of a move is among the user possible moves.
     * @param options vector of all possible moves.
     * @param row row
     * @param col col
     * @return true - a possible move, else- false.
     */
    private boolean isValidMove(List< Pair<Integer, Integer> > options, int row, int col) {
        for (int i = 0; i < options.size(); i++) {
            // user move exists in the options vector.
            if (options.get(i).getKey() == row && options.get(i).getValue() == col) {
                return true;
            }
        }
        return false;
    }
}

