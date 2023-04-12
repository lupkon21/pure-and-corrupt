package org.example.app.components.frame.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.*;
import org.example.app.components.map.movement.CollisionDetection;
import org.example.app.constants.MapConstants;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Getter
@Setter
@ToString
public class MapPanel extends JPanel {
    private Map map;

    public MapPanel() {
        this(1);
    }

    public MapPanel(Integer id) {
        loadMap(id);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // vykresleni podlahy
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0, MapConstants.MAP_PANEL_SIZE_X, MapConstants.MAP_PANEL_SIZE_Y);
        if(map != null) map.paint((Graphics2D) g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MapConstants.MAP_PANEL_SIZE_X,MapConstants.MAP_PANEL_SIZE_Y);
    }

    public void loadMap(Integer id) {
        String jsonString = "";

        try {
            jsonString = new String(Files.readAllBytes(Paths.get(MapConstants.JSON_PATH + id +".json")));
        } catch (IOException e) {
            System.out.println("Cannot load .json file for map id=" + id);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        try {
            map = objectMapper.readValue(jsonString, Map.class);
        } catch(JsonProcessingException e) {
            System.out.println("Cannot parse .json file for map id=" + id);
        }

        CollisionDetection.setComponents(map.getComponents().toArray());
    }
}
