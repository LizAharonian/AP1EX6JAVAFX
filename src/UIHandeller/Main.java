package UIHandeller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {
    private final int width = 600;
    private final int height = 400;
    /**
     * start func - runs the UI.
     * @param primaryStage - Stage obj.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,width,height);
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * main func - runs the program.
     * @param args - command line args
     */
    public static void main(String[] args) {
        launch(args);
    }
}