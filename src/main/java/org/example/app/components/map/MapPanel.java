package org.example.app.components.map;

import lombok.*;
import org.example.app.logic.combat.Combat;
import org.example.app.logic.movement.CollisionDetection;
import org.example.app.logic.combat.CombatDetection;
import org.example.app.constants.MapConstants;
import org.example.app.logic.render.Loader;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@ToString
public class MapPanel extends JPanel {
    private Map map;
    private Integer id;

    public MapPanel() {
        this(1);
    }

    public MapPanel(Integer id) {
        loadMap(id);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(map != null) map.paint(g2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MapConstants.MAP_PANEL_SIZE_X,MapConstants.MAP_PANEL_SIZE_Y);
    }

    private void initializeLogic(Map map) {
        CollisionDetection.initialize(map);
        CombatDetection.initialize(map);
        Combat.initialize(map);
    }

    public void loadMap(Integer id) {
        this.id = id;
        map = Loader.loadMap(id);
        initializeLogic(map);
    }
}
