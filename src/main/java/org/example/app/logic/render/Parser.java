package org.example.app.logic.render;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.app.components.map.Map;
import org.example.app.logic.stats.StatsPlayer;

public class Parser {

    public static Map parseMapJSON(String json) {
        Map map = new Map();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        try {
            map = objectMapper.readValue(json, Map.class);
        } catch(JsonProcessingException e) {
            System.out.println("Cannot parse .json file for current map");
        }
        return map;
    }

    public static StatsPlayer parsePlayerStats(String json) {
        StatsPlayer statsPlayer = new StatsPlayer();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        try {
            statsPlayer = objectMapper.readValue(json, StatsPlayer.class);
        } catch(JsonProcessingException e) {
            System.out.println("Cannot parse .json file for player stats");
        }
        return statsPlayer;
    }
}
