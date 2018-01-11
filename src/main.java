/**
 * Created by אורי on 08/01/2018.
 */
public class main {
    public static void main(String[] args) {
        Board board = new Board(8);
        board.initialize();
        IGame game = new TwoPlayersOneComputerGame(board, IGame.PlayersType.Humans, new ConsolePrinter());
        game.run();
    }
}
