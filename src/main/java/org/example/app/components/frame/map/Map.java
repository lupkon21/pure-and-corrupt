package org.example.app.components.frame.map;

import lombok.*;
import org.example.app.components.map.Components;
import org.example.app.components.map.Grid;
import org.example.app.components.root.Paintable;

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