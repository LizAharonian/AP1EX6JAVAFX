package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

/**
 * ReversiFiles.Printer Interface
 */
public interface Printer {
    /**
     * printCurrentBoard function.
     */
     String printCurrentBoard();
    /**
     * printBoard function.
     * @param board - game board
     */
     String printBoard(Board board);
    /**
     * noPossibleMovesForBothPlayers function.
     */
     String noPossibleMovesForBothPlayers();
    /**
     * noPossibleMovesForCurrentPlayer function.
     */
     String noPossibleMovesForCurrentPlayer();
    /**
     * waitForOtherPlayerMove function.
     */
     String waitForOtherPlayerMove();
    /**
     * announceWinner function
     * @param board - board of game
     */
     String announceWinner(Board board);
    /**
     * announceWhoPlayNow function.
     * @param currPlayer - curr player type
     */
     String announceWhoPlayNow(Player currPlayer);
    /**
     * announceWhoMadeAMove function
     * @param row - row in board
     * @param col - col in board
     * @param opponentType - black or white
     */
     String announceWhoMadeAMove(int row, int col, Color opponentType);
    /**
     * printPossibleMoves function
     * @param options - optional moves
     */
     String printPossibleMoves(List<Pair<Integer, Integer>> options);
    /**
     * enterYourMove function
     */
     String enterYourMove();
    /**
     * illegalMoveInput function
     */
     String illegalMoveInput();
    /**
     * illegalMoveInputFormat function
     */
     String illegalMoveInputFormat();
    /**
     * mainMenu function
     * @return
     */
     String mainMenu();
    /**
     * invalidInputMainMenu function.
     */
     String invalidInputMainMenu();
    /**
     * errorConnectingToServerAndExitMsg function.
     * @param msg - message
     */
}
