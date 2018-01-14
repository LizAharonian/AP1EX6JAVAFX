package UIHandeller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * MenuController class.
 * manages the menu UI
 */
public class MenuController  {

    //members
    private final int settingsWindowWidth = 600;
    private final int settingsWindowHeight = 400;
    private final int gameWindowWidth = 700;
    private final int getGameWindowHeight = 400;

    @FXML
    private Button btnStart;

    /**
     * btnStartClick func.
     * handels event of start game button click
     */
    @FXML
    protected void btnStartClick() {
        Stage stage = (Stage) btnStart.getScene().getWindow();

        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("BoardGame.fxml"));
            Scene scene = new Scene(root, gameWindowWidth, getGameWindowHeight);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {

        }
    }

    /**
     * btnSettings func.
     * handels event of settings button click
     */
    @FXML
    protected void btnSettings() {
        try {
            Stage stage = (Stage) btnStart.getScene().getWindow();

            Pane root = (Pane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Scene scene = new Scene(root, settingsWindowWidth, settingsWindowHeight);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex) {

        }

    }

    @FXML
    /**
     * Exit from the program window.
     */
    protected void btnExit() {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
    }

}
