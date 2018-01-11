package ReversiFiles; /**
 * Created by אורי on 08/01/2018.
 */
/**
 * Describse a Reversi game board.
 */
public class Board {
    public enum Cell {Black, White, Empty};
    private Cell[][] board;
    private int size ;
    /**
     * ReversiFiles.Board constructor.
     * creates a string sizeXsize matrix.
     */
    public Board(int size) {
        this.size = size;
        this.board = new Cell[size][size];
        this.initialize();
    }
    /**
     * copy constructor.
     * @param board - board to copy from.
     */
    public Board(final Board boardToCopy) {
        this.size = boardToCopy.size;
        this.board = new Cell[size][size];
        //copy each element
        for (int i=0; i<boardToCopy.size; i++) {
            for (int j = 0; j<boardToCopy.size; j++){
                this.board[i][j] = boardToCopy.board[i][j];
            }
        }
    }

    

    /**
     * intializes the game board to the start mode.
     * @param size size of the board (sizeXsize)
     */
    void initialize() {
        // initializes every cell with a space.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = Cell.Empty;
            }
        }
        // initializes the start mode of the white player and black player.
        // O represents the white player and X represents the black player.
        int startPos = size / 2 - 1;
        this.board[startPos][startPos] = Cell.White;
        this.board[startPos][startPos + 1] = Cell.Black;
        this.board[startPos + 1][startPos] = Cell.Black;
        this.board[startPos + 1][startPos + 1] = Cell.White;
    }
    /**
     * returns the value in a given position in the board.
     * @param row Row index
     * @param col Column index
     * @return Cell enum - value of the the board in the given index.
     */
    public Cell getCell(int row, int col)  {
        return this.board[row][col];
    }
    /**
     * change White to Black or Black to White in given index.
     * @param row
     * @param col
     */
    void flipCell(int row, int col) {
        if (this.board[row][col] == Cell.Black) {
            this.board[row][col] = Cell.White;
        } else if (this.board[row][col] == Cell.White) {
            this.board[row][col] = Cell.Black;
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
    public void setCell(int row, int col, Cell player) {
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
                if (this.board[i][j] == Cell.Empty) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
