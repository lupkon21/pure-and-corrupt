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
public class Border implements Paintable {

    private ArrayList<PaintableComponent> north;
    private ArrayList<PaintableComponent> east;
    private ArrayList<PaintableComponent> south;
    private ArrayList<PaintableComponent> west;

    @Override
    public void paint(Graphics2D g2) {
        for(PaintableComponent component : north) {
            component.paint(g2);
        }
        for(PaintableComponent component : east) {
            component.paint(g2);
        }
        for(PaintableComponent component : south) {
            component.paint(g2);
        }
        for(PaintableComponent component : west) {
            component.paint(g2);
        }
    }
}
