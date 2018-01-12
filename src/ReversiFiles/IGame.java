package ReversiFiles;

import javafx.scene.paint.*;
import javafx.util.Pair;


/**
 * Created by lizah on 08/01/2018.
 */
public interface IGame {

    enum Status {Playing, NoPossibleMoves, GameOver, Player1Winns, Player2Winns, Tie,NoPossibleMovesForBothPlayers, NotValidMove}

    ;

    enum PlayersType {Humans}

    ;

    /**
     * run function
     * runs the game
     */
    public void run();

    /**
     * checkWinner function
     *
     * @param board - game of board
     * @return status
     */
    static Status checkWinner(Board board, Color player1, Color player2) {
        int numOfWhites = 0;
        int numOfBlacks = 0;
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == player1) {
                    numOfBlacks++;
                } else if (board.getCell(i, j) == player2) {
                    numOfWhites++;
                }
            }
        }
        if (numOfBlacks > numOfWhites) {
            return Status.Player1Winns;
        } else if (numOfWhites > numOfBlacks) {
            return Status.Player2Winns;
        } else {
            return Status.Tie;
        }
    }

    GameLogic getGameLogic();

    Color getOpponent();

    Color getCurr();

    void switchCurrPlayer();

    void setStatus(Status status);

    Status getStatus();

    Status playOneTurn(Pair<Integer,Integer> chosenMove);
    int getScoresPlayer(Color color);


}
