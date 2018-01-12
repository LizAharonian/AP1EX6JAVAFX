package ReversiFiles;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

import static ReversiFiles.IGame.Status.NoPossibleMovesForBothPlayers;

/**
 * Created by lizah on 08/01/2018.
 */
public class TwoPlayersOneComputerGame implements IGame {


    //members
    private GameLogic gameLogic;
    private Board board;
    //private Printer printer;
    private Player currPlayer;
    private Player otherPlayer;
    private Status status;
    /**
     * ReversiFiles.TwoPlayersOneComputerGame ctor.
     *
     * @param board ReversiFiles.Board (reference)
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
    public Status playOneTurn(Pair<Integer,Integer> chosenMove){
        //todo:

        //this.printer.printCurrentBoard();
        //this.printer.printBoard(this.board);
//        this.printer.announceWhoPlayNow(currPlayer);

        if (this.status == Status.GameOver) {
            return this.status;
        } else {
            List<Pair<Integer,Integer>> moves = gameLogic.possibleMoves(currPlayer.getType(), otherPlayer.getType());
            if (moves.size()==0) {
                // No possible moves for both players. ReversiFiles.TwoPlayersOneComputerGame Over.
                if (this.status == Status.NoPossibleMoves) {
                    //this.printer.noPossibleMovesForBothPlayers();
                    this.status = NoPossibleMovesForBothPlayers;
                    return this.status;
                }
                //this.printer.noPossibleMovesForCurrentPlayer();

                switchCurrPlayer();
                this.status = Status.NoPossibleMoves;
                return this.status;
            } else {
                this.status = Status.Playing;
                try {


                    //Pair<Integer, Integer> chosenMove = currPlayer.getInput(moves, this.board, currPlayer.getType(), otherPlayer.getType());
                    if (this.currPlayer.isValidMove(moves, chosenMove.getKey(), chosenMove.getValue())) {

                        this.gameLogic.makeMove(chosenMove.getKey(), chosenMove.getValue(), currPlayer.getType(), otherPlayer.getType());
                        //this.printer.announceWhoMadeAMove(chosenMove.getKey(), chosenMove.getValue(), currPlayer.getType());
                        switchCurrPlayer();
                       // this.printer.printCurrentBoard();
                        //this.printer.printBoard(this.board);
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
      //  this.printer.announceWinner(this.board);

    }
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
     * Reversi game version:
     * two players on one machine.
     * players can be both humans or human against AIplayer.
     */
//class ReversiFiles.TwoPlayersOneComputerGame : public ReversiFiles.IGame {



    /**
     * Swap between current player to other player.
     */
    public void switchCurrPlayer() {
        Player temp = currPlayer;
        this.currPlayer = otherPlayer;
        this.otherPlayer = temp;

    }
    public GameLogic getGameLogic(){
        return this.gameLogic;
    }
    public Color getOpponent(){
        return this.otherPlayer.getType();
    }
    public Color getCurr(){
        return this.currPlayer.getType();
    }




}