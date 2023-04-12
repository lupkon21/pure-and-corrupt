package org.example.app.components.map.movement;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.app.components.root.PaintableComponent;

import java.util.ArrayList;

@AllArgsConstructor
@ToString
public class CollisionDetection {
    private static ArrayList<PaintableComponent> components;

    public static ArrayList<PaintableComponent> getComponents() {
        return components;
    }

    public static void setComponents(ArrayList<PaintableComponent> components) {
        CollisionDetection.components = components;
    }

    public static boolean isCollision(PaintableComponent component) {
        components.remove(component);
        for(PaintableComponent c : components) {
            if(component.getX().equals(c.getX()) && component.getY().equals(c.getY())) {
                components.add(component);
                return true;
            }
        }
        components.add(component);
        return false;
    }
}
