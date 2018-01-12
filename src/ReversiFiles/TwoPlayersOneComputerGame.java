package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by lizah on 08/01/2018.
 */
public class TwoPlayersOneComputerGame implements IGame {


    //members
    private GameLogic gameLogic;
    private Board board;
    private Printer printer;
    private Player currPlayer;
    private Player otherPlayer;
    private Status status;
    /**
     * ReversiFiles.TwoPlayersOneComputerGame ctor.
     *
     * @param board ReversiFiles.Board (reference)
     */
    TwoPlayersOneComputerGame(Board board, PlayersType playersType, Printer printer){

        this.board = board;
        this.printer = printer;
        this.gameLogic = new StandartGameLogic(this.board);
        switch (playersType) {
            case Humans:
               /* currPlayer = new HumanPlayer(Color.ORANGE, printer);
                otherPlayer = new HumanPlayer(Color.BLACK, printer);*/
                break;
        }
        status = Status.Playing;
    }

    /**
     * runs a Reversi Game.
     */
    public void run(){
       /* this.printer.printCurrentBoard();
        this.printer.printBoard(this.board);
        while (this.status != Status.GameOver) {
            this.printer.announceWhoPlayNow(currPlayer);
            List<Pair<Integer,Integer>> moves = gameLogic.possibleMoves(currPlayer.getType(), otherPlayer.getType());
            if (moves.size()==0) {
                // No possible moves for both players. ReversiFiles.TwoPlayersOneComputerGame Over.
                if (this.status == Status.NoPossibleMoves) {
                    this.printer.noPossibleMovesForBothPlayers();
                    this.status = Status.GameOver;
                    break;
                }
                this.printer.noPossibleMovesForCurrentPlayer();
                switchCurrPlayer();
                this.status = Status.NoPossibleMoves;
                continue;
            } else {
                this.status = Status.Playing;
                try {


                    Pair<Integer, Integer> chosenMove = currPlayer.getInput(moves, this.board, currPlayer.getType(), otherPlayer.getType());

                this.gameLogic.makeMove(chosenMove.getKey(), chosenMove.getValue(), currPlayer.getType(), otherPlayer.getType());
                this.printer.announceWhoMadeAMove(chosenMove.getKey(), chosenMove.getValue(), currPlayer.getType());
                switchCurrPlayer();
                this.printer.printCurrentBoard();
                this.printer.printBoard(this.board);
                if (this.board.getNumOfEmptyCells() == 0) {
                    this.status = Status.GameOver;
                }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        this.printer.announceWinner(this.board);*/
    }


    /**
     * Reversi game version:
     * two players on one machine.
     * players can be both humans or human against AIplayer.
     */
//class ReversiFiles.TwoPlayersOneComputerGame : public ReversiFiles.IGame {



    /**
     * Swap between current player to other player.
     */
    private void switchCurrPlayer() {
        Player temp = currPlayer;
        this.currPlayer = otherPlayer;
        this.otherPlayer = temp;

    }


}