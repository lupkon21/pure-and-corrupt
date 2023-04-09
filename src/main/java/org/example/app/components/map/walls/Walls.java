package org.example.app.components.map.walls;

import lombok.*;
import org.example.app.components.root.Paintable;
import org.example.app.components.root.PaintableComponent;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Walls implements Paintable {

    private Border border;
    private ArrayList<PaintableComponent> internal;

    @Override
    public void paint(Graphics2D g2) {
        border.paint(g2);
        for(PaintableComponent component : internal) {
            component.paint(g2);
        }
    }
}
