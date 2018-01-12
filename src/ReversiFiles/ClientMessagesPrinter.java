package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Scanner;


/*
*
 * Created by lizah on 12/01/2018.

*/

public class ClientMessagesPrinter implements Printer {

    private Color player1;
    private Color Player2;


    public ClientMessagesPrinter(Color player1, Color player2) {
        this.player1 = player1;
        this.Player2 = player2;
    }

    @Override
    public String printCurrentBoard() {
        throw new NotImplementedException();
    }

    @Override
    public String printBoard(Board board) {
        throw new NotImplementedException();

    }

    @Override
    public String noPossibleMovesForBothPlayers() {
        return "No Possible moves for both players.";

    }

    @Override
    public String noPossibleMovesForCurrentPlayer() {
        return "No possible moves. Play passes back to the other player. ";
    }

    @Override
    public String waitForOtherPlayerMove() {
        return "Waiting to other player's move...";
    }

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

    @Override
    public String announceWhoPlayNow(Player currPlayer) {
        if (currPlayer.getType() == player1) {
            return "player 1: It's your move.";
        } else {
            return "player 2: It's your move.";
        }
    }

    @Override
    public String announceWhoMadeAMove(int row, int col, Color opponentType) {
        if (opponentType == player1) {
            return "player 1 played (" + (row + 1) + "," + (col + 1) + ")";
        } else {
            return "player 2 played (" + (row + 1) + "," + (col + 1) + ")";
        }
    }

    @Override
    public String printPossibleMoves(List<Pair<Integer, Integer>> options) {
        int i = 0;
        String string="";
       // StringBuilder stringBuilder = new StringBuilder();
        string +="Your possible moves: ";
        string +="(" + (options.get(i).getKey() + 1) + "," + (options.get(i).getValue() + 1) + ")";
        while (i + 1 < options.size()) {
            i++;
            string+=",(" + (options.get(i).getKey() + 1) + "," + (options.get(i).getValue() + 1) + ")";
        }
        return string;
    }

    @Override
    public String enterYourMove() {
        throw new NotImplementedException();
    }

    @Override
    public String illegalMoveInput() {
        return "The move you chose is illegal. Try again.";
    }

    @Override
    public String illegalMoveInputFormat() {
        throw new NotImplementedException();
    }

    @Override
    public String mainMenu() {
        throw new NotImplementedException();
    }

    @Override
    public String invalidInputMainMenu() {
        throw new NotImplementedException();

    }
}

