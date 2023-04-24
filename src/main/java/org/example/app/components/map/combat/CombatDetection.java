package org.example.app.components.map.combat;

import org.example.app.components.map.components.dynamic.Player;
import org.example.app.components.map.movement.CollisionDetection;
import org.example.app.components.map.movement.Direction;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;

import java.util.ArrayList;
import java.util.Objects;

public class CombatDetection {
    private static ArrayList<PaintableComponent> enemies;
    private static Player player;

    public static ArrayList<PaintableComponent> getEnemies() {
        return enemies;
    }

    public static void setEnemies(ArrayList<PaintableComponent> enemies) {
        CombatDetection.enemies = enemies;
    }

    public static PaintableComponent getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        CombatDetection.player = player;
    }

    /*
    public static boolean isCombat(Player p) {
        Point player = new Point(p.getX(), p.getY());
        for(PaintableComponent e : enemies) {
            Point enemy = new Point(e.getX(), e.getY());

            if(CollisionDetection.isCollisionCustom(moveCoordinates(enemy, Direction.NORTH), player)) {
                System.out.println("Combat");
            } else if(CollisionDetection.isCollisionCustom(moveCoordinates(enemy, Direction.EAST), player)) {
                System.out.println("Combat");
            } else if(CollisionDetection.isCollisionCustom(moveCoordinates(enemy, Direction.SOUTH), player)) {
                System.out.println("Combat");
            } else if(CollisionDetection.isCollisionCustom(moveCoordinates(enemy, Direction.WEST), player)) {
                System.out.println("Combat");
            }
        }
        return false;
    }*/

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
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.NORTHEAST), p)) {
            isCombat = true;
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.SOUTHEAST), p)) {
            isCombat = true;
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.SOUTHWEST), p)) {
            isCombat = true;
        } else if(CollisionDetection.isCollisionCustom(moveCoordinates(e, Direction.NORTHWEST), p)) {
            isCombat = true;
        }

        // TODO: Just for check
        if(isCombat) System.out.println("Combat");
        player.setCombatActive(isCombat);
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
