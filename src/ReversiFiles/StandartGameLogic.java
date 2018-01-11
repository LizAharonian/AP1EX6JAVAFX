package ReversiFiles;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizah on 08/01/2018.
 */
public class StandartGameLogic implements GameLogic {

//members
    private Board board;

    StandartGameLogic(Board board){
        this.board = board;
    }
    
    
    public List<Pair<Integer,Integer>> possibleMoves(Board.Cell player, Board.Cell opponent) {
        List<Pair<Integer,Integer>> moves = new ArrayList<Pair<Integer,Integer>>();
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // checks for every Empty cell if it is a possible move for the player.
                if (this.board.getCell(i, j) == Board.Cell.Empty) {
                    boolean ans = checkCell(i, j, player, opponent);
                    // cell is a possible move
                    if (ans) {
                        // adds cell indices to moves array.
                        Pair<Integer,Integer> pair = new Pair<>(i,j);
                        moves.add(pair);
                    }
                }
            }
        }
        return moves;
    }

    boolean checkCell(int row, int col,Board.Cell player,
                                      Board.Cell opponent) {
        int size = board.getSize();
        // empty cell has an opponent neighbor, up left direction
        if (row - 1 >= 0 && col -1 >=0 &&
                this.board.getCell(row - 1, col - 1) == opponent) {
            if (scanDirection(row - 1, col - 1, -1, -1, player)) {
                return true;

            }
        }
        // empty cell has an opponent neighbor, up direction
        if (row - 1 >= 0 && this.board.getCell(row - 1, col) == opponent) {
            if (scanDirection(row - 1, col, -1, 0, player)) {
                return true;
            }
        }
        // empty cell has an opponent neighbor, up right direction
        if (row - 1 >= 0 && col + 1 <= size - 1 &&
                this.board.getCell(row - 1, col + 1) == opponent) {
            if (scanDirection(row - 1, col + 1, -1, 1, player)) {
                return true;
            }
        }
        // empty cell has an opponent neighbor, left direction
        if (col - 1 >= 0 && this.board.getCell(row, col - 1) == opponent) {
            if (scanDirection(row, col - 1, 0, -1, player)) {
                return true;
            }
        }
        // empty cell has an opponent neighbor, right direction
        if (col + 1 <= size - 1 && this.board.getCell(row, col + 1) == opponent) {
            if (scanDirection(row, col + 1, 0, 1, player)) {
                return true;
            }
        }
        // empty cell has an opponent neighbor, down left right direction
        if (row + 1 <= size - 1 && col - 1 >= 0 &&
                this.board.getCell(row + 1, col - 1) == opponent) {
            if (scanDirection(row + 1, col - 1, 1, -1, player)) {
                return true;
            }
        }
        // empty cell has an opponent neighbor, down direction
        if (row + 1 <= size - 1 && this.board.getCell(row + 1, col) == opponent) {
            if (scanDirection(row + 1, col, 1, 0, player)) {
                return true;
            }
        }
        // empty cell has an opponent neighbor, down right direction
        if (row + 1 <= size - 1 && col + 1 <= size - 1 &&
                this.board.getCell(row + 1, col + 1) == opponent) {
            if (scanDirection(row + 1, col + 1, 1, 1, player)) {
                return true;
            }
        }
        return false;
    }

    boolean scanDirection(int row, int col, int rowDirection,
                                          int colDirection, Board.Cell player) {
        // a player's cell
        if (this.board.isIndexInBoard(row + rowDirection, col + colDirection) &&
                this.board.getCell(row + rowDirection, col + colDirection) == player) {
            return true;
        }
        // empty cell
        if (this.board.isIndexInBoard(row + rowDirection, col + colDirection) &&
                this.board.getCell(row + rowDirection, col + colDirection) == Board.Cell.Empty) {
            return false;
        }
        // an opponent's cell - keep checking recursively
        if (this.board.isIndexInBoard(row + rowDirection, col + colDirection)) {
            return scanDirection(row + rowDirection, col + colDirection, rowDirection, colDirection, player);
        }
        return false;
    }

    public boolean makeMove(int row, int col, Board.Cell player, Board.Cell opponent) {
        if (this.board.getCell(row, col) != Board.Cell.Empty) {
            return false;
        }
    /* checks all 8 directions around the cell and if the direction is possible for move
     * flip all opponent's cells. */
        if (checkCell(row, col, player, opponent)) {
            this.board.setCell(row, col, player);
            // up left
            if (scanDirection(row - 1, col - 1, -1, -1, player)) {
                flipCells(row - 1, col - 1, -1, -1, opponent);
            }
            // up
            if (scanDirection(row - 1, col, -1, 0, player)) {
                flipCells(row - 1, col, -1, 0, opponent);
            }
            // up right
            if (scanDirection(row - 1, col + 1, -1, 1, player)) {
                flipCells(row - 1, col + 1, -1, 1, opponent);
            }
            // left
            if (scanDirection(row, col - 1, 0, -1, player)) {
                flipCells(row, col - 1, 0, -1, opponent);
            }
            // right
            if (scanDirection(row, col + 1, 0, 1, player)) {
                flipCells(row, col + 1, 0, 1, opponent);
            }
            // down left
            if (scanDirection(row + 1, col - 1, 1, -1, player)) {
                flipCells(row + 1, col - 1, 1, -1, opponent);
            }
            // down
            if (scanDirection(row + 1, col, 1, 0, player)) {
                flipCells(row + 1, col, 1, 0, opponent);
            }
            // down right
            if (scanDirection(row + 1, col + 1, 1, 1, player)) {
                flipCells(row + 1, col + 1, 1, 1, opponent);
            }
            return true;
        }
        return false;
    }

    void flipCells(int row, int col, int rowDorection, int colDirection,
                                      Board.Cell opponent) {
        while (this.board.isIndexInBoard(row, col)) {
            if (this.board.getCell(row, col) == opponent) {
                this.board.flipCell(row, col);
            } else {
                return;
            }
            row += rowDorection;
            col += colDirection;
        }
    }

   
    public int getScores(Board.Cell current, Board.Cell opponent) {
        int currentCunter = 0;
        int opponentCounter = 0;
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (this.board.getCell(i, j) == current) {
                    currentCunter++;
                } else if (this.board.getCell(i, j) == opponent) {
                    opponentCounter++;
                }
            }
        }
        return (currentCunter-opponentCounter);
    }
    
}
