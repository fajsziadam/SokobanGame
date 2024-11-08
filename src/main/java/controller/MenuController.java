package controller;

import model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * A MenuController osztály felelős a menü kezeléséért és az
 *  ahhoz tartozó események kezeléséért.
 */
public class MenuController {
    @FXML
    private AnchorPane sceneMenu;
    @FXML
    private TextField userName;

    @FXML
    void onStartGame(ActionEvent event) throws IOException {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        String playerName = userName.getText();
        if (playerName.isEmpty()) {
            Logger.warn("Player name is empty");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your Username!");
            alert.showAndWait();
        } else {
            Player.setUserName(playerName);
            new SceneSwitch(sceneMenu, "/game.fxml");
        }
    }

    @FXML
    public void onStartLeaderboard(ActionEvent event)throws IOException {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        new SceneSwitch(sceneMenu, "/leaderboard.fxml");
    }
}