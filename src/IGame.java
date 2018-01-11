/**
 * Created by lizah on 08/01/2018.
 */
public interface IGame {

    enum Status {Playing, NoPossibleMoves, GameOver, BlackWins, WhiteWins, Tie};
    enum PlayersType {Humans};

    /**
     * run function
     * runs the game
     */
    public void run();

    /**
     * checkWinner function
     * @param board  - game of board
     * @return status
     */
    static Status checkWinner(Board board){
        int numOfWhites = 0;
        int numOfBlacks = 0;
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == Board.Cell.Black) {
                    numOfBlacks++;
                } else if (board.getCell(i, j) == Board.Cell.White) {
                    numOfWhites++;
                }
            }
        }
        if (numOfBlacks > numOfWhites) {
            return Status.BlackWins;
        } else if (numOfWhites > numOfBlacks) {
            return Status.WhiteWins;
        } else {
            return Status.Tie;
        }
    }
}
