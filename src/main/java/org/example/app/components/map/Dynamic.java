package org.example.app.components.map;

import lombok.*;
import org.example.app.components.map.game.*;
import org.example.app.components.root.Paintable;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dynamic implements Paintable {
    private Objective objective;
    private Player player;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;
    private ArrayList<PatrolPoint> patrolPoints;

    @Override
    public void paint(Graphics2D g2) {

    }
}
