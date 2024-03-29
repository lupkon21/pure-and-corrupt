package org.example.app.components.map;

import lombok.*;
import org.example.app.components.map.components.Components;
import org.example.app.components.map.components.Grid;
import org.example.app.components.map.components.root.Paintable;
import org.example.app.constants.MapConstants;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Map implements Paintable {
    private Integer id;
    private String name;
    private Grid grid;
    private Components components;
    private boolean isGameOver = false;
    private boolean isGamePaused = false;
    private boolean isGameFinished = false;

    @Override
    public void paint(Graphics2D g2) {
        paintFloor(g2);
        components.paint(g2);
    }

    private void paintFloor(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0, MapConstants.MAP_PANEL_SIZE_X, MapConstants.MAP_PANEL_SIZE_Y);
    }
}
