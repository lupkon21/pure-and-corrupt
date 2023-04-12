package org.example.app.components.map;

import lombok.*;
import org.example.app.components.map.dynamic.Dynamic;
import org.example.app.components.map.walls.Walls;
import org.example.app.components.root.Paintable;
import org.example.app.components.root.PaintableComponent;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Components implements Paintable {
    private Walls walls;
    private Dynamic dynamic;

    @Override
    public void paint(Graphics2D g2) {
        walls.paint(g2);
        dynamic.paint(g2);
    }

    public ArrayList<PaintableComponent> toArray() {
        ArrayList<PaintableComponent> components = new ArrayList<>();

        components.addAll(walls.getBorder().getNorth());
        components.addAll(walls.getBorder().getEast());
        components.addAll(walls.getBorder().getSouth());
        components.addAll(walls.getBorder().getWest());
        components.addAll(walls.getInternal());

        components.addAll(dynamic.getEnemies());
        components.addAll(dynamic.getItems());
        components.add(dynamic.getPlayer());
        components.add(dynamic.getObjective());

        return components;
    }
}
