package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.List;
import static ReversiFiles.IGame.Status.NoPossibleMovesForBothPlayers;

/**
 * TwoPlayersOneComputerGame class.
 * manages the game.
 */
public class TwoPlayersOneComputerGame implements IGame {


    //members
    private GameLogic gameLogic;
    private Board board;
    private Player currPlayer;
    private Player otherPlayer;
    private Status status;

    /**
     * TwoPlayersOneComputerGame ctr
     * @param board - game board
     * @param player1 - color of player 1
     * @param player2 - color of player 2
     * @param currPlayer - color of curr player
     */
    public TwoPlayersOneComputerGame(Board board, Color player1, Color player2, Color currPlayer){
        this.board = board;
        this.gameLogic = new StandartGameLogic(this.board);
        this.currPlayer = new HumanPlayer(currPlayer);
        if (player1 != currPlayer) {
            this.otherPlayer = new HumanPlayer(player1);
        } else {
            this.otherPlayer = new HumanPlayer(player2);
        }
        status = Status.Playing;
    }

    /**
     * setStatus func.
     * @param status - game status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return this.status;
    }

    /**
     * runs a Reversi Game.
     */
    public void run(){
      throw new NotImplementedException();
    }

    /**
     * playOneTurn func.
     * @param chosenMove - chosen player move
     * @return game status
     */
    public Status playOneTurn(Pair<Integer,Integer> chosenMove){

        if (this.status == Status.GameOver) {
            return this.status;
        } else {
            List<Pair<Integer,Integer>> moves = gameLogic.possibleMoves(currPlayer.getType(), otherPlayer.getType());
            if (moves.size()==0) {
                // No possible moves for both players. ReversiFiles.TwoPlayersOneComputerGame Over.
                if (this.status == Status.NoPossibleMoves || this.status == NoPossibleMovesForBothPlayers) {
                    this.status = NoPossibleMovesForBothPlayers;
                    return this.status;
                }
                switchCurrPlayer();
                this.status = Status.NoPossibleMoves;
                return this.status;
            } else {
                this.status = Status.Playing;
                try {
                    if (this.currPlayer.isValidMove(moves, chosenMove.getKey(), chosenMove.getValue())) {

                        this.gameLogic.makeMove(chosenMove.getKey(), chosenMove.getValue(), currPlayer.getType(), otherPlayer.getType());
                        switchCurrPlayer();
                        if (this.board.getNumOfEmptyCells() == 0) {
                            this.status = Status.GameOver;
                        }
                    } else {
                        this.status = Status.NotValidMove;
                    }

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return this.status;
    }

    /**
     * getScoresPlayer func.
     * @param color - color of the player
     * @return scores
     */
    public int getScoresPlayer(Color color) {
        int cunter = 0;
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (this.board.getCell(i, j) == color) {
                    cunter++;
                }
            }
        }
        return cunter;
    }

    /**
     * Swap between current player to other player.
     */
    public void switchCurrPlayer() {
        Player temp = currPlayer;
        this.currPlayer = otherPlayer;
        this.otherPlayer = temp;

    }

    /**
     * getGameLogic func.
     * @return the game logic.
     */
    public GameLogic getGameLogic(){
        return this.gameLogic;
    }

    /**
     * getOpponent func
     * @return color of the opponent
     */
    public Color getOpponent(){
        return this.otherPlayer.getType();
    }

    /**
     * getCurr func.
     * @return color of curr player
     */
    public Color getCurr(){
        return this.currPlayer.getType();
    }




}