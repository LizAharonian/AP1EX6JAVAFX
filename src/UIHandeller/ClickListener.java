package UIHandeller;

import javafx.scene.control.Alert;

/**
 * Created by lizah on 14/01/2018.
 */
public class ClickListener {

    BoardGameController boardGameController;
    public ClickListener(BoardGameController boardGameController) {
        this.boardGameController = boardGameController;
    }

    public void clickEvent(int i,int j) {
        this.boardGameController.handleClick(i,j);
    }
}
