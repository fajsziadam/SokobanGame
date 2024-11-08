package controller;

import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A GameController osztály felelős a játék irányításáért,
 *  valamint kezeli a játékos interakcióit.
 */
public class GameController {

    @FXML
    private AnchorPane sceneMap;
    @FXML
    private GridPane map;
    @FXML
    Label userNameLabel;
    @FXML
    Label userStepsLabel;

    private int stepCounter;
    private Map mapGrid;
    private Player player;
    private SokobanState sokobanState;

    public void onBackToTheMenu(ActionEvent event)throws IOException {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        new SceneSwitch(sceneMap, "/menu.fxml");
    }

    public void onStartLeaderboard(ActionEvent event) throws IOException {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        new SceneSwitch(sceneMap, "/leaderboard.fxml");
    }

    public void onResetGame(ActionEvent event) {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        resetGame();
    }

    public void onExitGame(ActionEvent event) {
        Logger.debug("Clicked on: " + ((Button) event.getSource()).getText());
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * GameController osztály inicializálása.
     *
     * Létrehozza a térképet (Map), amely a játékteret reprezentálja,
     * Létrehoz egy játékost (Player),
     * Létrehozza a játékállapotot (SokobanState).
     *
     * @throws FileNotFoundException Ha nem találja a térképfájlt.
     */
    public GameController() throws FileNotFoundException {
        mapGrid = new Map();
        player = new Player(mapGrid.getPlayer().getX(), mapGrid.getPlayer().getY());
        sokobanState = new SokobanState(mapGrid, player);
    }

    private void printName() {
        userNameLabel.setText("Player: "+ Player.getUserName() );
    }

    /**
     * A játék kezdeti inicializálása.
     */
    @FXML
    public void initialize(){

        stepCounter = 0;
        printName();

        for (var e : mapGrid.getWalls()){
            var wall = createMapElement(Elements.WALL);
            map.add(wall, e.getX(), e.getY());
        }
        for (var e : mapGrid.getBalls()){
            var ball = createMapElement(Elements.BALL);
            map.add(ball, e.getX(), e.getY());
        }
        for (var e : mapGrid.getGoals()){
            var goal = createMapElement(Elements.GOAL);
            map.add(goal, e.getX(), e.getY());
        }
        for (var e : mapGrid.getTiles()){
            var tile = createMapElement(Elements.TILE);
            map.add(tile, e.getX(), e.getY());
        }
        var player = createMapElement(Elements.PLAYER);
        map.add(player, mapGrid.getPlayer().getX(), mapGrid.getPlayer().getY());
        Logger.info("Initialized");
    }

    /**
     * Az egyes játékmező elemeket létrehozó függvény.
     *
     * @param element a létrehozandó játékmező elem típusa.
     * @return a létrehozott játékmező elem.
     */
    private StackPane createMapElement(Elements element){
        var square = new StackPane();
        if (element == Elements.WALL) {
            square.getStyleClass().add("wall");
            Image image = new Image("/wall.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(54);
            imageView.setFitHeight(54);
            square.getChildren().add(imageView);

        } else if (element == Elements.TILE) {
            square.getStyleClass().add("tile");
            var piece = new Rectangle(54,54);
            piece.setFill(Color.LIGHTBLUE);
            square.getChildren().add(piece);

        } else if (element == Elements.BALL) {
            square.getStyleClass().add("ball");
            Rectangle background = new Rectangle(54, 54);
            background.setFill(Color.LIGHTBLUE);
            Image image = new Image("/ball.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(54);
            imageView.setFitHeight(54);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(background, imageView);
            square.getChildren().add(stackPane);

        } else if (element == Elements.PLAYER) {
            square.getStyleClass().add("player");
            Rectangle background = new Rectangle(54, 54);
            background.setFill(Color.LIGHTBLUE);
            Image image = new Image("/player.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(54);
            imageView.setFitHeight(54);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(background, imageView);
            square.getChildren().add(stackPane);

        } else if (element == Elements.GOAL) {
            square.getStyleClass().add("goal");
            StackPane stackPane = new StackPane();
            var outerRectangle = new Rectangle(54, 54);
            outerRectangle.setFill(Color.TRANSPARENT);
            outerRectangle.setStroke(Color.GREEN);
            outerRectangle.setStrokeWidth(2);
            var innerRectangle = new Rectangle(50, 50);
            innerRectangle.setFill(Color.LIGHTBLUE);
            stackPane.getChildren().addAll(outerRectangle, innerRectangle);
            square.getChildren().add(stackPane);
        }
        return square;
    }

    /**
     * Az elemek közötti váltást végző függvény.
     */
    private void switchElement(){
        if(!mapGrid.isGoal(player.getX(), player.getY())){
            var tile = createMapElement(Elements.TILE);
            map.add(tile, player.getX(), player.getY());
        }
        if(mapGrid.isGoal(player.getX(), player.getY())){
            var tile = createMapElement(Elements.GOAL);
            map.add(tile, player.getX(), player.getY());
        }

    }

    /**
     * A billentyűzet eseményeit kezelő függvény.
     *
     * @param keyEvent a billentyűzet eseménye.
     * @throws IOException kivétel keletkezhet, ha hiba történik az eseménykezeléssel kapcsolatban
     */
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) throws IOException {
        KeyCode x = keyEvent.getCode();
        Logger.debug(x.getName() + " is pressed");
        switch (x) {
            case W -> {
                if (sokobanState.isLegalMove(Directions.UP)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    switchElement();
                    map.add(p1, player.getX(), player.getY() - 1);
                    sokobanState.makeMove(Directions.UP);
                    stepCounter += 1;
                    Logger.debug("Moved Up");
                }else if (sokobanState.canPushBall(Directions.UP)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    var ball = createMapElement(Elements.BALL);
                    switchElement();
                    map.add(p1, player.getX(), player.getY() - 1);
                    map.add(ball, player.getX(), player.getY() - 2);
                    sokobanState.makeMove(Directions.UP);
                    stepCounter += 1;
                    Logger.debug("Moved ball Up");
                }
                System.out.println("Player is at (" + player.getX() + ", " + player.getY() + ")");
            }
            case A -> {
                if (sokobanState.isLegalMove(Directions.LEFT)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    switchElement();
                    map.add(p1, player.getX() - 1, player.getY());
                    sokobanState.makeMove(Directions.LEFT);
                    stepCounter += 1;
                    Logger.debug("Moved Left");
                }else if (sokobanState.canPushBall(Directions.LEFT)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    var ball = createMapElement(Elements.BALL);
                    switchElement();
                    map.add(p1, player.getX() - 1, player.getY());
                    map.add(ball, player.getX() - 2, player.getY());
                    sokobanState.makeMove(Directions.LEFT);
                    stepCounter += 1;
                    Logger.debug("Moved ball Left");
                }
                System.out.println("Player is at (" + player.getX() + ", " + player.getY() + ")");
            }
            case S -> {
                if (sokobanState.isLegalMove(Directions.DOWN)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    switchElement();
                    map.add(p1, player.getX(), player.getY() + 1);
                    sokobanState.makeMove(Directions.DOWN);
                    stepCounter += 1;
                    Logger.debug("Moved Down");
                }else if (sokobanState.canPushBall(Directions.DOWN)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    var ball = createMapElement(Elements.BALL);
                    switchElement();
                    map.add(p1, player.getX(), player.getY() + 1);
                    map.add(ball, player.getX(), player.getY() + 2);
                    sokobanState.makeMove(Directions.DOWN);
                    stepCounter += 1;
                    Logger.debug("Moved ball Down");
                }
                System.out.println("Player is at (" + player.getX() + ", " + player.getY() + ")");
            }
            case D -> {
                if (sokobanState.isLegalMove(Directions.RIGHT)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    switchElement();
                    map.add(p1, player.getX() + 1, player.getY());
                    sokobanState.makeMove(Directions.RIGHT);
                    stepCounter += 1;
                    Logger.debug("Moved Right");
                }else if(sokobanState.canPushBall(Directions.RIGHT)) {
                    var p1 = createMapElement(Elements.PLAYER);
                    var ball = createMapElement(Elements.BALL);
                    switchElement();
                    map.add(p1, player.getX() + 1, player.getY());
                    map.add(ball, player.getX() + 2, player.getY());
                    sokobanState.makeMove(Directions.RIGHT);
                    stepCounter += 1;
                    Logger.debug("Moved ball Right");
                }
                System.out.println("Player is at (" + player.getX() + ", " + player.getY() + ")");
            }
        }
        userStepsLabel.setText("Steps: " + stepCounter);
        if (sokobanState.isSolved()) {
            GameDatabaseManager.saveGameData(Player.getUserName(), stepCounter);
            Logger.info("Congratulations! You won the game!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText(null);
            alert.setContentText("You won the game!");
            alert.getButtonTypes().remove(ButtonType.OK);

            ButtonType resetGameButton = new ButtonType("Reset Game");
            alert.getButtonTypes().add(resetGameButton);

            ButtonType leaderboardButton = new ButtonType("Leaderboard");
            alert.getButtonTypes().add(leaderboardButton);
            alert.showAndWait().ifPresent(response -> {
                if (response == resetGameButton) {
                    resetGame();
                } else if (response == leaderboardButton) {
                    try {
                        new SceneSwitch(sceneMap, "/leaderboard.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * A játék visszaállítását végző függvény.
     */
    @FXML
    private void resetGame() {
        try {
            stepCounter = 0;
            userStepsLabel.setText("Steps: " + stepCounter);
            map.getChildren().clear();
            mapGrid = new Map();
            player = new Player(mapGrid.getPlayer().getX(), mapGrid.getPlayer().getY());
            sokobanState = new SokobanState(mapGrid, player);
            initialize();
            Logger.debug("Reset game");
        } catch (FileNotFoundException e) {
            Logger.error("map data file not found");
            throw new RuntimeException(e);
        }
    }
}