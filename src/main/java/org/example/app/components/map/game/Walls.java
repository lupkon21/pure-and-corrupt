package org.example.app.components.map.game;

import lombok.*;
import org.example.app.components.map.Border;
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

    }

}
