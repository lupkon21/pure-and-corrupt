package org.example.app.logic.combat;

import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Enemy;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.logic.movement.CollisionDetection;
import org.example.app.logic.movement.CoordinatesHandler;
import org.example.app.logic.movement.Direction;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.logic.movement.Point;

import java.util.ArrayList;

public class CombatDetection {
    private static Player player;
    private static ArrayList<Enemy> enemies;

    public static void initialize(Map map) {
        enemies = map.getComponents().getDynamic().getEnemies();
        player = map.getComponents().getDynamic().getPlayer();
    }

    public static boolean isCombatEnemy(PaintableComponent enemy) {
        Point e = new Point(enemy.getX(), enemy.getY());
        Point p = new Point(player.getX(), player.getY());
        boolean isCombat = false;


        if(CollisionDetection.isCollisionCustom(CoordinatesHandler.moveCoordinates(e, Direction.NORTH), p)) {
            isCombat = true;
            if(player.getDirection().equals(Direction.SOUTH)) Combat.activatePlayerCombat((Enemy) enemy);
        } else if(CollisionDetection.isCollisionCustom(CoordinatesHandler.moveCoordinates(e, Direction.EAST), p)) {
            isCombat = true;
            if(player.getDirection().equals(Direction.WEST)) Combat.activatePlayerCombat((Enemy) enemy);
        } else if(CollisionDetection.isCollisionCustom(CoordinatesHandler.moveCoordinates(e, Direction.SOUTH), p)) {
            isCombat = true;
            if(player.getDirection().equals(Direction.NORTH)) Combat.activatePlayerCombat((Enemy) enemy);
        } else if(CollisionDetection.isCollisionCustom(CoordinatesHandler.moveCoordinates(e, Direction.WEST), p)) {
            isCombat = true;
            if(player.getDirection().equals(Direction.EAST)) Combat.activatePlayerCombat((Enemy) enemy);
        }

        return isCombat;
    }

    public static Enemy isCombatPlayer() {
        for(Enemy enemy : enemies) {
            Point e = new Point(enemy.getX(), enemy.getY());
            Point p = new Point(player.getX(), player.getY());

            boolean isCombat = CollisionDetection.isCollisionCustom(p,e);
            if(!isCombat) {
                p = CoordinatesHandler.moveCoordinates(p,player.getDirection());
            }

            if(isCombat || CollisionDetection.isCollisionCustom(p,e)) {
                return enemy;
            }
        }
        return null;
    }
}
