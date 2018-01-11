import javafx.util.Pair;

import java.util.List;

/**
 * Printer Interface
 */
public interface Printer {
    /**
     * printCurrentBoard function.
     */
     void printCurrentBoard();
    /**
     * printBoard function.
     * @param board - game board
     */
     void printBoard(Board board);
    /**
     * noPossibleMovesForBothPlayers function.
     */
     void noPossibleMovesForBothPlayers();
    /**
     * noPossibleMovesForCurrentPlayer function.
     */
     void noPossibleMovesForCurrentPlayer();
    /**
     * waitForOtherPlayerMove function.
     */
     void waitForOtherPlayerMove();
    /**
     * announceWinner function
     * @param board - board of game
     */
     void announceWinner(Board board);
    /**
     * announceWhoPlayNow function.
     * @param currPlayer - curr player type
     */
     void announceWhoPlayNow(Player currPlayer);
    /**
     * announceWhoMadeAMove function
     * @param row - row in board
     * @param col - col in board
     * @param opponentType - black or white
     */
     void announceWhoMadeAMove(int row, int col, Board.Cell opponentType);
    /**
     * printPossibleMoves function
     * @param options - optional moves
     */
     void printPossibleMoves(List<Pair<Integer, Integer>> options);
    /**
     * enterYourMove function
     */
     String enterYourMove();
    /**
     * illegalMoveInput function
     */
     void illegalMoveInput();
    /**
     * illegalMoveInputFormat function
     */
     void illegalMoveInputFormat();
    /**
     * mainMenu function
     * @return
     */
     String mainMenu();
    /**
     * invalidInputMainMenu function.
     */
     void invalidInputMainMenu();
    /**
     * errorConnectingToServerAndExitMsg function.
     * @param msg - message
     */
}
