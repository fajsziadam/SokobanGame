package model;

/**
 * A GameDatabase osztály reprezentálja a játék adatait.(játékos nevét és lépésszámát).
 */
public class GameDatabase {

    private String name;
    private Integer steps;

    /**
     * Konstruktor létrehozza a GameDatabase objektumot a megadott névvel és lépésszámmal.
     *
     * @param name a játékos neve
     * @param steps a játékos lépésszáma
     */
    public GameDatabase(String name, Integer steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }
}