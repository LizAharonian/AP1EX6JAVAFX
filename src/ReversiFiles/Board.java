package ReversiFiles;

import javafx.scene.paint.Color;

/**
 * Describse a Reversi game board.
 */
public class Board {
   // public enum Cell {Black, White, Empty};
    private Color[][] board;
    private int size ;
    private Color player1;
    private Color player2;
    /**
     * ReversiFiles.Board constructor.
     * creates a string sizeXsize matrix.
     */
    public Board(int size, Color player1, Color player2) {
        this.size = size;
        this.board = new Color[size][size];
        this.player1 =player1;
        this.player2 = player2;
        this.initialize();
    }

    /**
     * intializes the game board to the start mode.
     */
    void initialize() {
        // initializes every cell with a space.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = null;
            }
        }
        // initializes the start mode of the white player and black player.
        // O represents the white player and X represents the black player.
        int startPos = size / 2 - 1;
        this.board[startPos][startPos] = player1;
        this.board[startPos][startPos + 1] = player2;
        this.board[startPos + 1][startPos] = player2;
        this.board[startPos + 1][startPos + 1] = player1;
    }
    /**
     * returns the value in a given position in the board.
     * @param row Row index
     * @param col Column index
     * @return Cell enum - value of the the board in the given index.
     */
    public Color getCell(int row, int col)  {
        return this.board[row][col];
    }
    /**
     * change White to Black or Black to White in given index.
     * @param row
     * @param col
     */
    void flipCell(int row, int col) {
        if (this.board[row][col] == player1) {
            this.board[row][col] = player2;
        } else if (this.board[row][col] == player2) {
            this.board[row][col] = player1;
        }
    }
    /**
     * return board size.
     * @return size - int.
     */
    public int getSize() {
        return this.size;

    }
    /**
     * set Cell to a given value.
     * @param row row
     * @param col col
     * @param player White / Black
     */
    public void setCell(int row, int col, Color player) {
        this.board[row][col] = player;
    }
    /**
     * returns true if a given index exists in the borders of the board.
     * @param row row
     * @param col col
     * @return true - legal index. false - else.
     */
    boolean isIndexInBoard(int row, int col) {
        return row >= 0 && col >= 0 && row <= this.size - 1 && col <= this.size -1;

    }
    /**
     * Returns number of empty cells in the board.
     * @return Returns number of empty cells in the board.
     */
    int getNumOfEmptyCells() {
        int counter = 0;
        for(int i=0; i < this.size; i++) {
            for(int j=0; j < this.size; j++) {
                if (this.board[i][j] == null) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
