package org.example.app.logic.render;

import org.example.app.components.map.Map;
import org.example.app.constants.MapConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
    
    public static BufferedImage loadAsset(String path) {
        BufferedImage asset = null;
        try {
            asset = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Image for not found on path=" + path);
        }
        return asset;
    }
}