package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

/**
 * A SceneSwitch osztály felelős az aktuális scene cseréjéért egy másik FXML fájlra.
 */
public class SceneSwitch {
    public SceneSwitch(AnchorPane currentScene, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        currentScene.getChildren().removeAll();
        nextAnchorPane.requestFocus();
        currentScene.getScene().setRoot(nextAnchorPane);
    }
}
