package model;

import java.util.Arrays;
import java.util.List;

/**
 * A Player osztály: a játékban szereplő karakter pozícióját reprezentáló osztály.
 */
public class Player {
    private int x;
    private int y;
    private static String userName;

    /**
     * Visszaadja a játékos felhasználónevét.
     *
     * @return userName
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Beállítja a játékos felhasználónevét.
     *
     * @param userName a beállítandó felhasználónév
     */
    public static void setUserName(String userName) {
        Player.userName = userName;
    }

    /**
     *  Kontruktor, amely egy új Player objektum hoz létre a megadott x és y koordinátákkal.
     *
     * @param x X koordináta
     * @param y Y koordináta
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Visszaadja a játékos X koordinátáját a térképen.
     *
     * @return x X koordináta
     */
    public int getX() {
        return x;
    }

    /**
     * Beállítja a játékos X koordinátáját a térképen.
     *
     * @param x X koordináta
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Visszaadja a játékos Y koordinátáját a térképen.
     *
     * @return y Y koordináta
     */
    public int getY() {
        return y;
    }

    /**
     * Beállítja a játékos Y koordinátáját a térképen.
     *
     * @param y Y koordináta
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Visszaadja a játékos aktuális pozícióját a megadott formátumban.
     *
     * @return a játékos aktuális pozíciója.
     */
    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Visszaadja a játékos X és Y koordinátáit egy listában.
     *
     * @return játékos X és Y koordinátái
     */
    public List<Integer> getLocation(){
        return Arrays.asList(this.x, this.y);
    }
}
