package ReversiFiles;

import javafx.util.Pair;

import java.util.List;

/**
 * ReversiFiles.Player Interface.
 * ReversiFiles.Player can be human or computer.
 */
abstract public class Player {
    //members
    protected Board.Cell type;
    protected GameLogic gameLogic;

    /**
     * ReversiFiles.Player c'tor
     * @param type ReversiFiles.Board::Cell - Can be Black or White.
     */
    Player(Board.Cell type) {
        this.type = type;
    }

    /**
     * ReversiFiles.Player c'tor (For AIPlayer)
     * @param type ReversiFiles.Board::Cell - Can be Black or White.
     * @param gameLogic ReversiFiles.GameLogic
     */
    public Player(Board.Cell type, GameLogic gameLogic) {
        this.type = type;
        this.gameLogic = gameLogic;
    }

    /**
     * returns the player type.
     * @return ReversiFiles.Board::Cell (Black/White)
     */
    Board.Cell getType() {
        return this.type;
    }

    /**
     * Sets the type of the player
     * @param newType ReversiFiles.Board:Cell
     */
    void setType(Board.Cell newType) {
        this.type = newType;
    }

    /**
     * display to the player all his possible moves and gets an input of a wished move.
     * @param options vector of possible moves.
     * @param board ReversiFiles.Board
     * @param currentCellType ReversiFiles.Board::Cell (White/Black)
     * @param opponentCellType ReversiFiles.Board::Cell (White/Black)
     * @return pair<int, int> of cell in the board - the desired move of the player.
     */
    abstract Pair<Integer, Integer> getInput(List< Pair<Integer,Integer> > options, final Board board,
                                             Board.Cell currentCellType, Board.Cell opponentCellType) throws Exception;
}
