package org.example.app.components.map;

import lombok.*;
import org.example.app.components.map.game.Walls;
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

    }
}
