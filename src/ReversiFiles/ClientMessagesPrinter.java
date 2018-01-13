package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.List;

/**
 * ClientMessagesPrinter class.
 * we get our user messages from here
 */
public class ClientMessagesPrinter implements Printer {
    //members
    private Color player1;
    private Color Player2;

    /**
     * ctr.
     * @param player1 - color of player 1
     * @param player2 - color of player 2
     */
    public ClientMessagesPrinter(Color player1, Color player2) {
        this.player1 = player1;
        this.Player2 = player2;
    }
    /**
     * printCurrentBoard function.
     */
    @Override
    public String printCurrentBoard() {
        throw new NotImplementedException();
    }
    /**
     * printBoard function.
     * @param board - game board
     */
    @Override
    public String printBoard(Board board) {
        throw new NotImplementedException();

    }
    /**
     * noPossibleMovesForBothPlayers function.
     */
    @Override
    public String noPossibleMovesForBothPlayers() {
        return "No Possible moves for both players.";

    }
    /**
     * noPossibleMovesForCurrentPlayer function.
     */
    @Override
    public String noPossibleMovesForCurrentPlayer() {
        return "No possible moves. \nPlay passes back to the other player. ";
    }
    /**
     * waitForOtherPlayerMove function.
     */
    @Override
    public String waitForOtherPlayerMove() {
        return "Waiting to other player's move...";
    }
    /**
     * announceWinner function
     * @param board - board of game
     */
    @Override
    public String announceWinner(Board board) {
        IGame.Status winningStatus = IGame.checkWinner(board, player1, Player2);
        if (winningStatus == IGame.Status.Player1Winns) {
            return "Congratulations player 1! You Won!.";
        } else if (winningStatus == IGame.Status.Player2Winns) {
            return "Congratulations player 2! You Won!.";
        } else {
            return "It's a tie.";
        }
    }
    /**
     * announceWhoPlayNow function.
     * @param currPlayer - curr player type
     */
    @Override
    public String announceWhoPlayNow(Player currPlayer) {
        if (currPlayer.getType() == player1) {
            return "player 1: It's your move.";
        } else {
            return "player 2: It's your move.";
        }
    }
    /**
     * announceWhoMadeAMove function
     * @param row - row in board
     * @param col - col in board
     * @param opponentType - black or white
     */
    @Override
    public String announceWhoMadeAMove(int row, int col, Color opponentType) {
        if (opponentType == player1) {
            return "player 1 played (" + (row + 1) + "," + (col + 1) + ")";
        } else {
            return "player 2 played (" + (row + 1) + "," + (col + 1) + ")";
        }
    }
    /**
     * printPossibleMoves function
     * @param options - optional moves
     */
    @Override
    public String printPossibleMoves(List<Pair<Integer, Integer>> options) {
        String string = "";

        if (options.size() != 0) {
            int i = 0;
            // StringBuilder stringBuilder = new StringBuilder();
            string += "Your possible moves: ";
            string += "(" + (options.get(i).getKey() + 1) + "," + (options.get(i).getValue() + 1) + ")";
            while (i + 1 < options.size()) {
                i++;
                string += ",(" + (options.get(i).getKey() + 1) + "," + (options.get(i).getValue() + 1) + ")";
            }
        }
        return string;
    }
    /**
     * enterYourMove function
     */
    @Override
    public String enterYourMove() {
        throw new NotImplementedException();
    }
    /**
     * illegalMoveInput function
     */
    @Override
    public String illegalMoveInput() {
        return "The move you chose is illegal. Try again.";
    }
    /**
     * illegalMoveInputFormat function
     */
    @Override
    public String illegalMoveInputFormat() {
        throw new NotImplementedException();
    }
    /**
     * mainMenu function
     * @return
     */
    @Override
    public String mainMenu() {
        throw new NotImplementedException();
    }
    /**
     * invalidInputMainMenu function.
     */
    @Override
    public String invalidInputMainMenu() {
        throw new NotImplementedException();

    }
}

