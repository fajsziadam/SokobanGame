package view;

import model.Directions;
import model.Map;
import model.Player;
import model.SokobanState;
import org.tinylog.Logger;
import puzzle.solver.BreadthFirstSearch;

import java.io.FileNotFoundException;

/** A SokobanSolver osztály felelős a Sokoban játék megoldásáért.
 * A megoldást a szélességi keresés algoritmus (BFS) segítségével oldja meg.
 */
public class SokobanSolver {

    /** Main metódus, amely elindítja a Sokoban játék megoldását.
     *
     * @param args Parancssori argumentumok az alkalmazás számára.
     *
     * A metódus először létrehozza a pálya (Map) és a játékos (Player) objektumokat,
     *  majd a jelenlegi játékállapotot (SokobanState). Ezt követően egy szélességi keresést
     *  alkalmaz a megoldás megtalálására és kiírására.
     */
    public static void main(String[] args) {
        try {
            Map map = new Map();
            Player player = map.getPlayer();
            SokobanState state = new SokobanState(map, player);

            var bfs = new BreadthFirstSearch<Directions>();
            bfs.solveAndPrintSolution(state);

        } catch (FileNotFoundException e) {
            Logger.error("File not found", e);
        }
    }
}