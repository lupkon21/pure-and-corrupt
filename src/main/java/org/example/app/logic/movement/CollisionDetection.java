package org.example.app.logic.movement;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.app.components.map.Map;
import org.example.app.logic.combat.Point;
import org.example.app.components.map.components.dynamic.Enemy;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.components.map.components.root.DefaultComponent;
import org.example.app.components.map.components.root.PaintableComponent;

import java.util.ArrayList;

@AllArgsConstructor
@ToString
public class CollisionDetection {
    private static ArrayList<PaintableComponent> playerCollisionComponents;
    private static ArrayList<DefaultComponent> enemyCollisionComponents;

    public static void initialize(Map map) {
        enemyCollisionComponents = map.getComponents().enemyCollisionComponentsToArray();
        playerCollisionComponents = map.getComponents().playerCollisionComponentsToArray();
    }

    public static boolean isCollision(PaintableComponent component) {
        if(component instanceof Enemy) {
            for(DefaultComponent c : enemyCollisionComponents) {
                if(component.getX().equals(c.getX()) && component.getY().equals(c.getY())) {
                    return true;
                }
            }
        } else if(component instanceof Player) {
            for(PaintableComponent c : playerCollisionComponents) {
                if(component.getX().equals(c.getX()) && component.getY().equals(c.getY())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCollisionCustom(Point c1, Point c2) {
        return c1.getX().equals(c2.getX()) && c1.getY().equals(c2.getY());
    }
}
