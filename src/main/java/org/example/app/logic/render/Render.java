package org.example.app.logic.render;

import org.example.app.components.Frame;
import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Player;


public class Render {
    private static Frame frame;
    public static void renderNextMap() {
        if(frame.getMapPanel().getId() >= 5) return;

        Map map = frame.getMapPanel().getMap();
        Player playerLastState = map.getComponents().getDynamic().getPlayer();

        frame.getMapPanel().loadMap(map.getId() + 1);

        Player player = frame.getMapPanel().getMap().getComponents().getDynamic().getPlayer();
        player.setHp(playerLastState.getHp());
        player.setAttackDamage(playerLastState.getAttackDamage());
        player.setAttackCooldown(playerLastState.getAttackCooldown());
        player.setDefendCooldown(playerLastState.getDefendCooldown());
        player.setDefendTime(playerLastState.getDefendTime());
        player.setItems(playerLastState.getItems());
        player.setActivatableItems(playerLastState.getActivatableItems());

        frame.repaint();
        frame.getContentPane().repaint();
        frame.initializeTimers();
    }

    public static void setFrame(Frame frame) {
        Render.frame = frame;
    }
}
