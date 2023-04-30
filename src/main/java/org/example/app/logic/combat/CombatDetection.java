package org.example.app.logic.combat;

import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Enemy;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.logic.movement.CollisionDetection;
import org.example.app.logic.movement.Direction;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;

import java.util.Objects;

public class CombatDetection {
    private static Player player;

    public static void initialize(Map map) {
        player = map.getComponents().getDynamic().getPlayer();
    }

    public static boolean isCombatEnemy(PaintableComponent enemy) {
        Point e = new Point(enemy.getX(), enemy.getY());
        Point p = new Point(player.getX(), player.getY());
        boolean isCombat = false;

        if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.NORTH), p)) {
            isCombat = true;
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.EAST), p)) {
            isCombat = true;
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.SOUTH), p)) {
            isCombat = true;
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.WEST), p)) {
            isCombat = true;
        }

        if(isCombat) {
            Combat.setEnemy((Enemy) enemy);
            player.setCombatActive(true);
        }
        return isCombat;
    }


    private static Point moveCoordinates(Point point, Direction direction) {
        Point p = new Point(point.getX(), point.getY());

        if (Objects.requireNonNull(direction) == Direction.NORTH) {
            p.setY(point.getY() - MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.EAST) {
            p.setX(point.getX() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.SOUTH) {
            p.setY(point.getY() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.WEST) {
            p.setX(point.getX() - MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.NORTHEAST) {
            p.setY(point.getY() - MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.SOUTHEAST) {
            p.setY(point.getY() + MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.SOUTHWEST) {
            p.setY(point.getY() + MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() - MapConstants.GRID_CELL_SIZE);
        }  else if (Objects.requireNonNull(direction) == Direction.NORTHWEST) {
            p.setY(point.getY() - MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() - MapConstants.GRID_CELL_SIZE);
        }

        return p;
    }
}
