package org.example.app.logic.combat;

import lombok.*;
import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Enemy;

import org.example.app.components.map.components.dynamic.Player;
import org.example.app.logic.movement.CollisionDetection;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Combat {
    private static Enemy enemy;
    private static Player player;
    private static Map map;

    public static void setEnemy(Enemy enemy) {
        Combat.enemy = enemy;
    }
    public static void initialize(Map map) {
        Combat.map = map;
        Combat.player = map.getComponents().getDynamic().getPlayer();
    }

    public static void playerDefaultAttack() {
        if(enemy != null) {
            enemy.setHp(enemy.getHp() - 10);
            if(enemy.getHp() <= 0) enemyDeath(enemy);
        }
    }

    public static void playerItemAttack() {

    }

    public static void enemyAttack() {
        if(player != null) {
            player.setHp(player.getHp() - 10);
            if(player.getHp() <= 0) playerDeath();
        }
    }

    public static void enemyDeath(Enemy enemy) {
        map.getComponents().getDynamic().getEnemies().remove(enemy);
        CollisionDetection.initialize(map);
        CombatDetection.initialize(map);
        Combat.enemy = null;
    }

    public static void playerDeath() {
        map.getComponents().getDynamic().setPlayer(null);
        map.setGameOver(true);
    }

    public static void activatePlayerCombat(Enemy enemy) {
        if(map.getComponents().getDynamic().getPlayer() == null) return;
        map.getComponents().getDynamic().getPlayer().setCombatActive(true);
        enemy.setCombatActive(true);
        Combat.setEnemy(enemy);
    }

    public static void deactivatePlayerCombat() {
        map.getComponents().getDynamic().getPlayer().setCombatActive(false);
    }
}
