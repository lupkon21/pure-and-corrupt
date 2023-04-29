package org.example.app.logic.render;

import org.example.app.components.map.Map;
import org.example.app.constants.MapConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {
    public static Map loadMap(Integer id) {
        String jsonString = Loader.loadJSON((MapConstants.JSON_PATH + id));
        return Parser.parseMapJSON(jsonString);
    }

    public static String loadJSON(String path) {
        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(path + ".json")));
        } catch (IOException e) {
            System.out.println("Cannot load .json file with path=" + path);
        }
        return jsonString;
    }
}
