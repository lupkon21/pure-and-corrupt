package org.example.app.logic.movement;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Enemy;
import org.example.app.components.map.components.dynamic.Item;
import org.example.app.components.map.components.dynamic.Objective;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.components.map.components.root.DefaultComponent;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.logic.render.Render;

import java.util.ArrayList;

@AllArgsConstructor
@ToString
public class CollisionDetection {
    private static ArrayList<PaintableComponent> playerCollisionComponents;
    private static ArrayList<DefaultComponent> enemyCollisionComponents;
    private static ArrayList<PaintableComponent> items;
    private static Player player;
    private static Map map;

    public static void initialize(Map map) {
        enemyCollisionComponents = map.getComponents().enemyCollisionComponentsToArray();
        playerCollisionComponents = map.getComponents().playerCollisionComponentsToArray();
        items = new ArrayList<>();
        items.addAll(map.getComponents().getDynamic().getItems());
        items.add(map.getComponents().getDynamic().getObjective());
        player = map.getComponents().getDynamic().getPlayer();
        CollisionDetection.map = map;
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

    public static void checkItemCollision() {
        for(PaintableComponent i : items) {
            if(player.getX().equals(i.getX()) && player.getY().equals(i.getY())) {
                if(i instanceof Objective) {
                    Render.renderNextMap();
                } else if(i instanceof Item) {
                    map.getComponents().getDynamic().getItems().remove(i);
                    items.remove(i);
                    ((Item) i).execute();
                    return;
                }
            }
        }
    }
}
