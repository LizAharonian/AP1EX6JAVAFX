import javafx.util.Pair;

import java.util.List;
import java.util.Scanner;

/**
 * Created by אורי on 08/01/2018.
 */
public class ConsolePrinter implements Printer {
    @Override
    public void printCurrentBoard() {
        System.out.println("Current board:");
    }

    @Override
    public void printBoard(Board board) {
        int size = board.getSize();
        System.out.print(" |");
        for (int i = 0 ; i <  size; i++) {
            if (i < 9) {
                System.out.print(" ");
            }
            System.out.print((i + 1) + " |");
        }
        System.out.println();
        for (int i = 0 ; i <  2 + 4 * size ; i++) {
            System.out.print("-");
        }
        for (int i = 0 ; i <  size ; i++) {
            System.out.print("\n" + (i + 1) + "|");
            for (int j = 0; j < size; j++) {
                System.out.print(" ");
                switch(board.getCell(i,j)) {
                    case Empty:
                        System.out.print(" ");
                        break;
                    case White:
                        System.out.print("O");
                        break;
                    case Black:
                        System.out.print("X");
                        break;
                }
                System.out.print(" |");
            }
            System.out.println();
            for (int k = 0 ; k <  2 + 4 * size ; k++) {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    @Override
    public void noPossibleMovesForBothPlayers() {
        System.out.println("No Possible moves for both players.");

    }

    @Override
    public void noPossibleMovesForCurrentPlayer() {
        System.out.println("No possible moves. Play passes back to the other player. ");
        System.out.println("Press any key to continue.");
        String keyToContinue;
        Scanner sc = new Scanner(System.in);
        keyToContinue = sc.nextLine();
    }

    @Override
    public void waitForOtherPlayerMove() {
        System.out.println("Waiting to other player's move...");
    }

    @Override
    public void announceWinner(Board board) {
        IGame.Status winningStatus = IGame.checkWinner(board);
        if (winningStatus == IGame.Status.BlackWins) {
            System.out.println("Congratulations X! You Won!.");
        } else if (winningStatus == IGame.Status.WhiteWins) {
            System.out.println("Congratulations O! You Won!.");
        } else {
            System.out.println("It's a tie.");
        }
    }

    @Override
    public void announceWhoPlayNow(Player currPlayer) {
        if (currPlayer.getType() == Board.Cell.Black) {
            System.out.println("X: It's your move.");
        } else {
            System.out.println("O: It's your move.");
        }
    }

    @Override
    public void announceWhoMadeAMove(int row, int col, Board.Cell opponentType) {
        if (opponentType ==  Board.Cell.Black) {
            System.out.println("X played (" + (row + 1) + "," + (col + 1) + ")");
        } else {
            System.out.println("O played (" + (row + 1) + "," + (col + 1) + ")");
        }
    }

    @Override
    public void printPossibleMoves(List<Pair<Integer, Integer>> options) {
        int i = 0;
        System.out.print("Your possible moves: ");
        System.out.print("(" + (options.get(i).getKey() + 1) + "," + (options.get(i).getValue() + 1) + ")");
        while (i + 1 < options.size()) {
            i++;
            System.out.print(",(" + (options.get(i).getKey() + 1) + "," + (options.get(i).getValue() + 1) + ")");
        }
    }

    @Override
    public String enterYourMove() {
        System.out.println("\n" + "Please enter your move row,col:");
        String move;
        Scanner sc = new Scanner(System.in);
        move = sc.nextLine();
        return move;
    }

    @Override
    public void illegalMoveInput() {
        System.out.println("The move you chose is illegal. Try again.");
    }

    @Override
    public void illegalMoveInputFormat() {
        System.out.println("Wrong input format. Should be 'row,col'. Let's try again.");
    }

    @Override
    public String mainMenu() {
        System.out.println("Hello!" + "\n" + "Please choose one of the following options:");
        System.out.println("1. a human local player.");
        System.out.println("2. an AI player.");
        System.out.println("3. a remote player.");
        IGame.PlayersType playersType;
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        return input;
    }

    @Override
    public void invalidInputMainMenu() {
        System.out.println("Please choose correct option.");
    }
}
