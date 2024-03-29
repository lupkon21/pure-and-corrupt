package org.example.app.components.map.components;

import lombok.*;
import org.example.app.components.map.components.dynamic.Dynamic;
import org.example.app.components.map.components.walls.Walls;
import org.example.app.components.map.components.root.DefaultComponent;
import org.example.app.components.map.components.root.Paintable;
import org.example.app.components.map.components.root.PaintableComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public ArrayList<PaintableComponent> playerCollisionComponentsToArray() {
        ArrayList<PaintableComponent> components = new ArrayList<>();
        components.addAll(walls.getBorder().getNorth());
        components.addAll(walls.getBorder().getEast());
        components.addAll(walls.getBorder().getSouth());
        components.addAll(walls.getBorder().getWest());
        components.addAll(walls.getInternal());
        components.addAll(dynamic.getEnemies());
        return components;
    }

    public ArrayList<DefaultComponent> enemyCollisionComponentsToArray() {
        ArrayList<DefaultComponent> components = dynamic.getPatrolPoints().stream().map(c -> (DefaultComponent) c).collect(Collectors.toCollection(ArrayList::new));
        components.add(dynamic.getPlayer());
        return components;
    }
}
