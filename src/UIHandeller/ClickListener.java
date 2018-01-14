package UIHandeller;

import javafx.scene.control.Alert;

/**
 * Click Listner class.
 * Responsible to all mouse- events of BoardGame cells.
 */
public class ClickListener {
    // members
    BoardGameController boardGameController;

    /**
     * ClickListener c'tor.
     * @param boardGameController BoardGameController.
     */
    public ClickListener(BoardGameController boardGameController) {
        this.boardGameController = boardGameController;
    }

    /**
     * Calls handleClick method of BoardGameController with the suitable parameters.
     * @param i row index of grid cell
     * @param j column index of grid cell
     */
    public void clickEvent(int i,int j) {
        this.boardGameController.handleClick(i,j);
    }
}
