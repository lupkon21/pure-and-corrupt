package org.example.app.graphics.map;

import lombok.*;
import org.example.app.graphics.map.components.Components;
import org.example.app.graphics.map.components.Grid;
import org.example.app.graphics.map.components.root.Paintable;

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

    @Override
    public void paint(Graphics2D g2) {
        components.paint(g2);
    }

}
