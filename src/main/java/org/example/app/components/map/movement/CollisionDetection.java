package org.example.app.components.map.movement;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.app.components.map.dynamic.Enemy;
import org.example.app.components.map.dynamic.Player;
import org.example.app.components.root.DefaultComponent;
import org.example.app.components.root.PaintableComponent;

import java.util.ArrayList;

@AllArgsConstructor
@ToString
public class CollisionDetection {
    private static ArrayList<PaintableComponent> components;
    private static ArrayList<DefaultComponent> enemyCollisionComponents;

    public static ArrayList<PaintableComponent> getComponents() {
        return components;
    }

    public static void setComponents(ArrayList<PaintableComponent> components) {
        CollisionDetection.components = components;
    }

    public static void setEnemyCollisionComponents(ArrayList<DefaultComponent> enemyCollisionComponents) {
        CollisionDetection.enemyCollisionComponents = enemyCollisionComponents;
    }

    public static boolean isCollision(PaintableComponent component) {
        components.remove(component);

        if(component instanceof Enemy) {
            for(DefaultComponent c : enemyCollisionComponents) {
                if(component.getX().equals(c.getX()) && component.getY().equals(c.getY())) {
                    return true;
                }
            }
        } else if(component instanceof Player) {
            for(PaintableComponent c : components) {
                if(component.getX().equals(c.getX()) && component.getY().equals(c.getY())) {
                    components.add(component);
                    return true;
                }
            }
        }

        components.add(component);
        return false;
    }

    public static boolean isCollisionCustom(Point c1, Point c2) {
        return c1.getX().equals(c2.getX()) && c1.getY().equals(c2.getY());
    }
}
