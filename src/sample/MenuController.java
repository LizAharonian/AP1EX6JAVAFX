package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;
/*import ReversiCode.GameState;
import ReversiCode.GuiManager;
import ReversiCode.GuiPlayer;
import ReversiCode.ReversiDefaultRules;*/
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by lizah on 11/01/2018.
 */
public class MenuController  {

    @FXML
    private Button btnStart;
    @FXML
    private Button btnSettings;
   /* @FXML
    private Label labell;*/



    @FXML
    protected void btnStartClick() {
        // btnStart.setLabel("liz");
        Stage stage = (Stage) btnStart.getScene().getWindow();
        // stage.close();
        // label.setText("lll");
        // btnStart.setText("hh");

        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("BoardGame.fxml"));
            Scene scene = new Scene(root, 520, 400);
            //  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //  Start primaryStage =
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {

        }


    }

    @FXML
    protected void btnSettings() {
        try {
            Stage stage = (Stage) btnStart.getScene().getWindow();

            Pane root = (Pane) FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root, 520, 400);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex) {

        }

    }

}
