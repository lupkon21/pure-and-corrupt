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
    private static long lastPlayerAttackTime;

    public static void setEnemy(Enemy enemy) {
        Combat.enemy = enemy;
    }
    public static void initialize(Map map) {
        Combat.map = map;
        Combat.player = map.getComponents().getDynamic().getPlayer();
        lastPlayerAttackTime = System.currentTimeMillis();
    }

    public static void playerDefaultAttack() {
        if(enemy != null && checkCooldown(lastPlayerAttackTime, player.getAttackCooldown())) {
            enemy.setHp(enemy.getHp() - player.getAttackDamage());
            lastPlayerAttackTime = System.currentTimeMillis();
            if(enemy.getHp() <= 0) enemyDeath(enemy);
        }
    }
    public static void playerDefend(){

    }

    public static void playerItemAttack() {

    }

    public static void enemyAttack(Enemy enemy) {
        if(player != null) {
            player.setHp(player.getHp() - enemy.getAttackDamage());
            if(player.getHp() <= 0) playerDeath();
        }
    }

    public static void enemyDeath(Enemy enemy) {
        enemy.setCombatActive(false);
        map.getComponents().getDynamic().getEnemies().remove(enemy);
        Combat.initialize(map);
        CollisionDetection.initialize(map);
        CombatDetection.initialize(map);
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

    public static boolean checkCooldown(long lastTime, Integer cooldownTime) {
        return System.currentTimeMillis() - lastTime >= cooldownTime;
    }
}
