package org.example.app.logic.render;

import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Enemy;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.constants.MapConstants;
import org.example.app.logic.stats.StatsEnemy;
import org.example.app.logic.stats.StatsPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {
    public static Map loadMap(Integer id) {
        String jsonString = Loader.loadJSON((MapConstants.MAP_PATH + id));
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

    public static void loadStats(Player player) {
        String jsonString = Loader.loadJSON(MapConstants.STATS_PATH + "player");
        StatsPlayer stats =  Parser.parsePlayerStats(jsonString);
        stats.mapStatsToPlayer(player);
    }

    public static void loadStats(Enemy enemy) {
        String jsonString = Loader.loadJSON(MapConstants.STATS_PATH + "enemies/enemy_" + enemy.getIdType());
        StatsEnemy stats = Parser.parseEnemyStats(jsonString);
        stats.mapStatsToEnemy(enemy);
    }
}
