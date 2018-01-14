package UIHandeller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * SettingsController class.
 * manages our setting win
 */
public class SettingsController implements Initializable {
    //members
    @FXML
    private ColorPicker cp1;
    @FXML
    private ColorPicker cp2;
    @FXML
    private ChoiceBox whoStarts;
    @FXML
    private ChoiceBox size;
    @FXML
    private Button btnDone;
    @FXML
    private Button btnBack;

    /**
     * btnDoneClick func.
     * saves the settings
     */
    @FXML
    protected void btnDoneClick() {
        Color c1 = cp1.getValue();
        String hexWebC1 = String.format( "#%02X%02X%02X",
                (int)( c1.getRed() * 255 ),
                (int)( c1.getGreen() * 255 ),
                (int)( c1.getBlue() * 255 ) );
        Color c2 = cp2.getValue();
        String hexWebC2 = String.format( "#%02X%02X%02X",
                (int)( c2.getRed() * 255 ),
                (int)( c2.getGreen() * 255 ),
                (int)( c2.getBlue() * 255 ) );
        String startPlayer = (String)whoStarts.getValue();
        String boardSize = (String)size.getValue();
        List<String> settingsCategories = new ArrayList<String>();
        // color of player 1
        settingsCategories.add(hexWebC1);
        // color of player 2
        settingsCategories.add(hexWebC2);
        // start player
        settingsCategories.add(startPlayer);
        // board size
        settingsCategories.add(boardSize);

        // constructing the string buffer in following format: "setting_1,setting_2,...,setting_n"
        int settingsCategoriesSize = settingsCategories.size();
        StringBuffer inputBuffer = new StringBuffer();
        for (int i = 0; i < settingsCategoriesSize - 1; i++) {
            inputBuffer.append(settingsCategories.get(i));
            inputBuffer.append(",");
        }
        inputBuffer.append(settingsCategories.get(settingsCategoriesSize - 1));

        try {
            FileOutputStream fileOut = new FileOutputStream("settings.txt");
            // write buffer to settings file
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem writing file.");
        }
        // go back to Menu scene
        try {
            Stage stage = (Stage) btnDone.getScene().getWindow();
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex) {

        }
    }

    /**
     * btnBackClick func.
     * goes back to menu
     */
    @FXML
    protected void btnBackClick() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex) {

        }
    }

    /**
     * initialize func.
     * @param location - url obj
     * @param resources - ResourceBundle obj
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        whoStarts.setItems(FXCollections.observableArrayList("Player 1", "Player 2"));
        ObservableList possibleSizes = FXCollections.observableArrayList();
        for (int i = 4; i <= 20; i++) {
            possibleSizes.add(Integer.toString(i));
        }
        size.setItems(possibleSizes);
        try {
            // input the file content to the StringBuffer "input"
            File f = new File("settings.txt");
            String path = f.getAbsolutePath();
            BufferedReader file = new BufferedReader((new FileReader(path)));
            String line;
            //StringBuffer inputBuffer = new StringBuffer();
            line = file.readLine();
            if (line == null) {
                throw new Exception("Empty File");
            }
            String settingsCategories[] = line.split(",");
            // four settings categories: Color of player 1, Color of player 2, Start player and Board size
            if (settingsCategories.length != 4) {
                throw new Exception("Missing Settings");
            }
            // sets settings from the settings file
            cp1.setValue(Color.web(settingsCategories[0]));
            cp2.setValue(Color.web(settingsCategories[1]));
            whoStarts.setValue(settingsCategories[2]);
            size.setValue(settingsCategories[3]);
            file.close();
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}