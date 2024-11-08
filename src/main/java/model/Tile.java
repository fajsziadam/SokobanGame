package model;


/**
 *  A Tile osztály egy mezőt reprezentál a játékterületen,
 *   amely egy koordináta párral rendelkezik (x és y).
 */
public class Tile {

    private int x;
    private int y;

    /**
     *  Kontruktor, amely egy új Tile objektum hoz létre a megadott x és y koordinátákkal.
     *
     * @param x X koordináta
     * @param y Y koordináta
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Visszadja az x mező koordinátáját.
     *
     * @return x X koordináta
     */
    public int getX() {
        return x;
    }

    /**
     * Visszadja az y mező koordinátáját.
     *
     * @return y Y koordináta
     */
    public int getY() {
        return y;
    }

    /**
     * Visszaadja a mező szöveges reprezentációját.
     *
     * @return a mező szöveges reprezentációja
     */
    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}