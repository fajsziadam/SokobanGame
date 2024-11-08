package model;
import com.google.gson.*;
import org.tinylog.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Map osztály reprezentálja a játékteret.
 */
public class Map {

    private final List<Tile> tiles;
    private Player player;
    private final List<Tile> walls;
    private final List<Tile> goals;
    private final List<Tile> balls;

    public Map(Map original) {
        this.tiles = new ArrayList<>(original.tiles);
        this.walls = new ArrayList<>(original.walls);
        this.goals = new ArrayList<>(original.goals);
        this.balls = new ArrayList<>(original.balls);
        this.player = new Player(original.player.getX(), original.player.getY());
    }

    public Map() throws FileNotFoundException {
        tiles = new ArrayList<>();
        walls = new ArrayList<>();
        goals = new ArrayList<>();
        balls = new ArrayList<>();

        InputStream is = null;
        try {
            is = Map.class.getResourceAsStream("/map.json");
        } catch (Exception e){
            Logger.error("Error reading map file", e);
        }

        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(is)));
        JsonObject fileObject = jsonElement.getAsJsonObject();
        JsonArray jsonMap = fileObject.get("map").getAsJsonArray();
        for (JsonElement mapElement: jsonMap){
            JsonObject mapJsonObject = mapElement.getAsJsonObject();
            String type = mapJsonObject.get("type").getAsString();
            int x = mapJsonObject.get("x").getAsInt();
            int y = mapJsonObject.get("y").getAsInt();
            switch (type){
                case "tile" -> this.tiles.add(new Tile(x, y));
                case "wall" -> this.walls.add(new Tile(x,y));
                case "ball" -> this.balls.add(new Tile(x,y));
                case "goal" -> this.goals.add(new Tile(x,y));
                case "player" -> this.player = new Player(x,y);
            }
        }
    }

    @Override
    public String toString() {
        return "MapInitializer{" +
                "\ntiles=" + tiles.toString() +
                ", \nplayer=" + player.getLocation() +
                ", \nwalls=" + walls.toString()  +
                ", \ngoals=" + goals.toString()  +
                ", \nballs=" + balls.toString()  +
                '}';
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Tile> getWalls() {
        return walls;
    }

    public List<Tile> getGoals() {
        return goals;
    }

    public List<Tile> getBalls() {
        return balls;
    }

    public void changeBallLocation(int x, int y, Directions direction) {
        Tile ball = getBall(x, y);
        if (ball != null) {
            balls.remove(ball);
            switch (direction) {
                case UP:
                    balls.add(new Tile(x, y - 1));
                    break;
                case RIGHT:
                    balls.add(new Tile(x + 1, y));
                    break;
                case DOWN:
                    balls.add(new Tile(x, y + 1));
                    break;
                case LEFT:
                    balls.add(new Tile(x - 1, y));
                    break;
            }
        }
    }

    private Tile getBall(int x, int y) {
        for (Tile ball : balls) {
            if (ball.getX() == x && ball.getY() == y) {
                return ball;
            }
        }
        return null;
    }

    public boolean isGoal(int x, int y){
        for (var e : this.goals){
            if(e.getX() == x && e.getY() == y){
                return true;
            }
        }
        return false;
    }

    public boolean isWall(int x, int y){
        for (var e : this.walls){
            if(e.getX() == x && e.getY() == y){
                return true;
            }
        }
        return false;
    }

    public boolean isTile(int x, int y){
        for (var e : this.tiles){
            if(e.getX() == x && e.getY() == y){
                return true;
            }
        }
        return false;
    }

    public boolean isPlayer(int x, int y) {
        return player.getX() == x && player.getY() == y;
    }

    public boolean isBall(int x, int y){
        for (var e : this.balls){
            if(e.getX() == x && e.getY() == y){
                return true;
            }
        }
        return false;
    }
/*
public boolean isFinished() {
        for (Tile goal : this.goals) {
            if (getPlayer().getX() == goal.getX() && getPlayer().getY() == goal.getY()) {
                return true;
            }
        }
    return false;
}*/
}