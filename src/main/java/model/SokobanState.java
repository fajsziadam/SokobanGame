package model;

import puzzle.State;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A SokobanState osztály a játék aktuális állapotát reprezentálja,
 * beleértve a játéktérképet és a játékos pozícióját.
 */
public class SokobanState implements State<Directions> {

    private Map mapGrid;
    private Player player;

    private final Set<Tile> validBallLocations;

    /**
     * Konstruktor, amely létrehoz egy új SokobanState objektumot
     * a megadott térképpel és játékossal
     *
     * @param map a játéktérkép
     * @param player a játékos
     */
    public SokobanState(Map map, Player player) {
        this.mapGrid = map;
        this.player = player;

        this.validBallLocations = new HashSet<>();

        validBallLocations.add(new Tile(2, 2));
        validBallLocations.add(new Tile(2, 3));
        validBallLocations.add(new Tile(3, 2));
        validBallLocations.add(new Tile(3, 3));
        validBallLocations.add(new Tile(3, 4));
        validBallLocations.add(new Tile(3, 5));
        validBallLocations.add(new Tile(3, 6));
        validBallLocations.add(new Tile(4, 6));
        validBallLocations.add(new Tile(4, 5));
        validBallLocations.add(new Tile(5, 5));
        validBallLocations.add(new Tile(6, 5));
        validBallLocations.add(new Tile(7, 5));
        validBallLocations.add(new Tile(7, 4));
        validBallLocations.add(new Tile(7, 3));
    }

    /**
     * Visszaadja, hogy a játék meg van-e oldva.
     *
     * @return true, ha ha a játék meg van oldva, egyébként false.
     */
    @Override
    public boolean isSolved() {
        for (Tile ball : mapGrid.getBalls()) {
            if (!mapGrid.isGoal(ball.getX(), ball.getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ellenőrzi, hogy egy adott lépés lehetséges-e.
     *
     * @param direction a lépés iránya.
     * @return true, ha a lépés lehetséges, egyébként false.
     */
    @Override
    public boolean isLegalMove(Directions direction) {
        switch (direction) {
            case UP:
                return canMoveUp();
            case RIGHT:
                return canMoveRight();
            case DOWN:
                return canMoveDown();
            case LEFT:
                return canMoveLeft();
            default:
                return false;
        }
    }

    /**
     * Ellenőrzi, hogy lehetséges-e labdát tolni egy adott irányba.
     *
     * @param direction a tolás iránya
     * @return true, ha a labda tolása lehetséges, egyébként false.
     */
    public boolean canPushBall(Directions direction) {
        switch (direction) {
            case UP:
                return canPushBallUp();
            case RIGHT:
                return canPushBallRight();
            case DOWN:
                return canPushBallDown();
            case LEFT:
                return canPushBallLeft() ;
            default:
                return false;
        }
    }

    private boolean canPushBallUp() {
        return mapGrid.isBall(player.getX(), player.getY() - 1) &&
                !mapGrid.isWall(player.getX(), player.getY() - 2) &&
                !mapGrid.isBall(player.getX(), player.getY() - 2);
                //&& validBallLocations.contains(new Tile(player.getX(), player.getY() - 2));
    }

    private boolean canPushBallRight() {
        return mapGrid.isBall(player.getX() + 1, player.getY()) &&
                !mapGrid.isWall(player.getX() + 2, player.getY()) &&
                !mapGrid.isBall(player.getX() + 2, player.getY());
                //&& validBallLocations.contains(new Tile(player.getX() + 2, player.getY()));
    }

    private boolean canPushBallDown() {
        return mapGrid.isBall(player.getX(), player.getY() + 1) &&
                !mapGrid.isWall(player.getX(), player.getY() + 2) &&
                !mapGrid.isBall(player.getX(), player.getY() + 2);
                //&& validBallLocations.contains(new Tile(player.getX(), player.getY() + 2));
    }

    private boolean canPushBallLeft() {
        return mapGrid.isBall(player.getX() - 1, player.getY()) &&
                !mapGrid.isWall(player.getX() - 2, player.getY()) &&
                !mapGrid.isBall(player.getX() - 2, player.getY());
                //&& validBallLocations.contains(new Tile(player.getX() - 2, player.getY()));
    }

    private boolean canMoveUp() {
        return  !mapGrid.isWall(player.getX(), player.getY() - 1) &&
                !mapGrid.isBall(player.getX(), player.getY() - 1);
    }

    private boolean canMoveRight() {
        return !mapGrid.isWall(player.getX() + 1, player.getY()) &&
                !mapGrid.isBall(player.getX() + 1, player.getY());
    }

    private boolean canMoveDown() {
        return !mapGrid.isWall(player.getX(), player.getY() + 1) &&
                !mapGrid.isBall(player.getX(), player.getY() + 1);
    }

    private boolean canMoveLeft() {
        return !mapGrid.isWall(player.getX() - 1, player.getY()) &&
                !mapGrid.isBall(player.getX() - 1, player.getY());
    }

    /**
     * Végrehajt egy lépést a játékban a megadott irányba.
     *  Ha a lépés lehetséges, akkor a játékos mozgatása történik.
     *  Ha a lépés során egy labda tolható, akkor azt is végrehajtja.
     *
     * @param direction a lépés iránya.
     */
    public void makeMove(Directions direction) {
        System.out.println("Making move: " + direction);
        switch (direction) {
            case UP -> moveUp();
            case RIGHT -> moveRight();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
        }
        System.out.println("New state: " + this);
    }

    public void moveUp() {
        if (isLegalMove(Directions.UP)) {
            player.setY(player.getY() - 1);
            mapGrid.getPlayer().setY(player.getY());
        } else if (canPushBall(Directions.UP)) {
            mapGrid.changeBallLocation(player.getX(), player.getY() - 1, Directions.UP);
            player.setY(player.getY() - 1);
            mapGrid.getPlayer().setY(player.getY());
        }
    }

    public void moveDown() {
        if (isLegalMove(Directions.DOWN)) {
            player.setY(player.getY() + 1);
            mapGrid.getPlayer().setY(player.getY());
        } else if (canPushBall(Directions.DOWN)) {
            mapGrid.changeBallLocation(player.getX(), player.getY() + 1, Directions.DOWN);
            player.setY(player.getY() + 1);
            mapGrid.getPlayer().setY(player.getY());
        }
    }

    public void moveLeft() {
        if (isLegalMove(Directions.LEFT)) {
            player.setX(player.getX() - 1);
            mapGrid.getPlayer().setX(player.getX());
        } else if (canPushBall(Directions.LEFT)) {
            mapGrid.changeBallLocation(player.getX() - 1, player.getY(), Directions.LEFT);
            player.setX(player.getX() - 1);
            mapGrid.getPlayer().setX(player.getX());
        }
    }

    public void moveRight() {
        if (isLegalMove(Directions.RIGHT)) {
            player.setX(player.getX() + 1);
            mapGrid.getPlayer().setX(player.getX());
        } else if (canPushBall(Directions.RIGHT)) {
            mapGrid.changeBallLocation(player.getX() + 1, player.getY(), Directions.RIGHT);
            player.setX(player.getX() + 1);
            mapGrid.getPlayer().setX(player.getX());
        }
    }

    /**
     * Visszaadja az összes lehetséges lépést.
     *
     * @return az összes lehetséges lépés halmaza.
     */
    @Override
    public Set<Directions> getLegalMoves() {
        Set<Directions> legalMoves = EnumSet.noneOf(Directions.class);
        for (Directions direction : Directions.values()) {
            if (isLegalMove(direction)) {
                legalMoves.add(direction);
            }
            else if (canPushBall(direction)) {
                legalMoves.add(direction);
            }
        }
        return legalMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SokobanState that = (SokobanState) o;
        return Objects.equals(mapGrid, that.mapGrid) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapGrid, player);
    }

    /**
     * Létrehoz és visszaad egy új, azonos állapotú klónt ennek az objektumnak.
     * A klónozás során a játéktérkép és a játékos is másolásra kerül.
     *
     * @return az új klónozott {@code SokobanState} objektum.
     */
    @Override
    public SokobanState clone() {
        Map clonedMap = new Map(this.mapGrid);
        Player clonedPlayer = new Player(this.player.getX(), this.player.getY());
        SokobanState clonedState = new SokobanState(clonedMap, clonedPlayer);
        return clonedState;
    }

    /**
     * Visszaadja a játékos objektumot, amely a játékos aktuális pozícióját jelképezi a játékban.
     *
     * @return player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Visszaadja a játéktérkép objektumot, amely a játéktérkép aktuális állapotát reprezentálja.
     *
     * @return mapGrid
     */
    public Map getMap() {
        return mapGrid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /*sb.append("Játékállapot:\n");
        sb.append("Player: ").append(player).append("\n");
        sb.append("Labdák:\n");
        for (Tile ball : mapGrid.getBalls()) {
            sb.append(ball).append("\n");
        }
        sb.append("Térkép:\n").append(mapGrid);*/
        return sb.toString();
    }
}