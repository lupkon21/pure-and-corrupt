package org.example.app.components.map.dynamic;

import lombok.*;
import org.example.app.components.root.Paintable;
import org.example.app.constants.MapConstants;

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
        objective.paint(g2);
        for(Item item : items) {
            item.paint(g2);
        }
        for(Enemy enemy : enemies) {
            enemy.paint(g2);
        }
        player.paint(g2);

        // TODO: remove, just for check
        paintPatrolPoints(g2);
    }

    private void paintPatrolPoints(Graphics2D g2) {
        g2.setColor(new Color(255,107,0));
        for(PatrolPoint patrolPoint : patrolPoints) {
            g2.fillRect(patrolPoint.getX(), patrolPoint.getY(), MapConstants.GRID_CELL_SIZE, MapConstants.GRID_CELL_SIZE);
        }
    }

    public void moveEnemies() {
        for(Enemy enemy : enemies) {
            enemy.move();
        }
    }
}
