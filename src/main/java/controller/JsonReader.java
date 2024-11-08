package controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A JsonReader osztály felelős a pálya JSON fájlból történő beolvasásáért.
 */
public class JsonReader {

    /**
     * A JSON fájlok olvasását végző függvény.
     *
     * @param args a JSON fájl elérési útvonala.
     */
    public static void main(String[] args) {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/map.json")));
            JSONObject jsonObject = new JSONObject(json);
            JSONArray mapArray = jsonObject.getJSONArray("map");

            for (int i = 0; i < mapArray.length(); i++) {
                JSONObject mapObject = mapArray.getJSONObject(i);
                String type = mapObject.getString("type");
                int x = mapObject.getInt("x");
                int y = mapObject.getInt("y");

                System.out.println("Type: " + type + ", X: " + x + ", Y: " + y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}