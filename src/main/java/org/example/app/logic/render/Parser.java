package org.example.app.logic.render;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.app.components.map.Map;

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
}
