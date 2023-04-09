package org.example.app.components.map;

import lombok.*;
import org.example.app.components.map.dynamic.Dynamic;
import org.example.app.components.map.walls.Walls;
import org.example.app.components.root.Paintable;

import java.awt.*;

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
}
