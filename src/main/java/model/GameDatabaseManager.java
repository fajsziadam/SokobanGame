package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A GameDatabaseManager osztály felelős a játék adatainak kezeléséért,
 *  mint például a játékos neve(playerName) és lépésszáma(stepCount).
 */
public class GameDatabaseManager {

    /**
     * Elmenti a játék adatait.(játékos nevét és lépésszámát)
     *
     * @param playerName a játékos neve
     * @param stepCount a játékos lépésszáma
     * @throws IOException kivétel dobódik, ha nem sikerül elmenteni a játék adatait
     */
    public static void saveGameData(String playerName, int stepCount) throws IOException {

        GameDatabase newGameData = new GameDatabase(playerName, stepCount);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileName = "leaderboard.json";
        File file = new File(fileName);

        List<GameDatabase> leaderboard = new ArrayList<>();
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {
                Type listType = new TypeToken<List<GameDatabase>>() {}.getType();
                leaderboard = gson.fromJson(fileReader, listType);
            } catch (IOException e) {
                Logger.error("Failed to read existing game data: " + e.getMessage());
                throw e;
            }
        }
        leaderboard.add(newGameData);

        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(leaderboard, fileWriter);
            Logger.info("Game data saved successfully.");
        } catch (IOException e) {
            Logger.error("Failed to save game data: " + e.getMessage());
            throw e;
        }
    }
}
