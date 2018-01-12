package ReversiFiles;

import javafx.scene.paint.*;
import javafx.util.Pair;

/**
 * IGame interface.
 */
public interface IGame {
    //enum
    enum Status {Playing, NoPossibleMoves, GameOver, Player1Winns, Player2Winns, Tie,NoPossibleMovesForBothPlayers, NotValidMove};

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

    /**
     * getGameLogic func.
     * @return the game logic.
     */
    GameLogic getGameLogic();
    /**
     * getOpponent func
     * @return color of the opponent
     */
    Color getOpponent();
    /**
     * getCurr func.
     * @return color of curr player
     */
    Color getCurr();
    /**
     * Swap between current player to other player.
     */
    void switchCurrPlayer();
    /**
     * playOneTurn func.
     * @param chosenMove - chosen player move
     * @return game status
     */
    Status playOneTurn(Pair<Integer,Integer> chosenMove);
    /**
     * getScoresPlayer func.
     * @param color - color of the player
     * @return scores
     */
    int getScoresPlayer(Color color);


}
