package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lizah on 11/01/2018.
 */
public class BoardGameController implements Initializable{

    @FXML
    private HBox root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //todo: add reading settiond from file
        BoardGame boardGame = new BoardGame(8);
        boardGame.setPrefWidth(400);
        boardGame.setPrefHeight(400);
        root.getChildren().add(0, boardGame);
        boardGame.draw();
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            boardGame.setPrefWidth(boardNewWidth);
            boardGame.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            boardGame.setPrefHeight(newValue.doubleValue());
            boardGame.draw();
        });
    }


}