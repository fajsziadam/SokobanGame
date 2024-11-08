package view;

import model.Directions;
import model.Map;
import model.Player;
import model.SokobanState;
import org.tinylog.Logger;

import java.io.FileNotFoundException;

/**
 * A GameConsoleTest osztály a konzolos teszteléshez használatos,
 *  amely egy játékost mozgat a pályán különböző irányokba,
 *  majd kiírja, hogy a játék megoldódot-e.
 */

public class GameConsoleTest {
    public static void main(String[] args) {
        try {

            Map map = new Map();
            Player player = new Player(1, 1);
            SokobanState state = new SokobanState(map, player);

            System.out.println(state);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.UP);
            System.out.println(state);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.DOWN);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.LEFT);
            state.makeMove(Directions.UP);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            state.makeMove(Directions.RIGHT);
            System.out.println(state.isSolved());
        } catch (
                FileNotFoundException e) {
            Logger.error("File not found", e);
        }}
}
