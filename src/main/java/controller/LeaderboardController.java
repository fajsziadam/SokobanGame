package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GameDatabase;
import org.tinylog.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 * A LeaderboardController osztály a ranglistát kezelő osztály,
 *  amely a játékosok nevét és lépésszámát jeleníti meg egy táblázatban.
 */
public class LeaderboardController {

    @FXML
    private AnchorPane sceneLeaderboard;
    @FXML
    private TableView<GameDatabase> leaderboard;
    @FXML
    private TableColumn<GameDatabase, String> name;
    @FXML
    private TableColumn<GameDatabase, Integer> steps;

    public void onBackToTheMenu(ActionEvent event)throws IOException {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        new SceneSwitch(sceneLeaderboard, "/menu.fxml");
    }

    public void onExitGame(ActionEvent event) {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Az initialize() metódus, beállítja az oszlopok értékeit és betölti a táblázatba adatokat.
     */
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        steps.setCellValueFactory(new PropertyValueFactory<>("steps"));
        loadLeaderboardData();

        leaderboard.getSortOrder().add(steps);
        steps.setSortType(TableColumn.SortType.ASCENDING);
        leaderboard.sort();
    }

    /**
     * Betölti a ranglista adatait a JSON fájlból.
     */
    private void loadLeaderboardData() {
        try {
            Gson gson = new GsonBuilder().create();
            Reader reader = new FileReader("leaderboard.json");
            GameDatabase[] entries = gson.fromJson(reader, GameDatabase[].class);
            reader.close();
            ObservableList<GameDatabase> data = FXCollections.observableArrayList(Arrays.asList(entries));
            leaderboard.setItems(data);
        } catch (FileNotFoundException e) {
            System.out.println("File not founded.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}