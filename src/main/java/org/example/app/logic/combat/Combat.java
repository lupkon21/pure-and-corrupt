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
        player = map.getComponents().getDynamic().getPlayer();
        Combat.map = map;
    }

    public static void playerDefaultAttack() {
        if(enemy == null) return;
        enemy.setHp(enemy.getHp() - 10);
        if(enemy.getHp() <= 0) enemyDeath(enemy);
    }

    public static void playerItemAttack() {

    }

    public static void enemyAttack() {

    }

    public static void enemyDeath(Enemy enemy) {
        map.getComponents().getDynamic().getEnemies().remove(enemy);
        CollisionDetection.initialize(map);
        CombatDetection.initialize(map);
        Combat.enemy = null;
    }
}
