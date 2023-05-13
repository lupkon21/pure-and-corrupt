package org.example.app.logic.combat;

import lombok.*;
import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Enemy;

import org.example.app.components.map.components.dynamic.Item;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.constants.ItemsConstants;
import org.example.app.logic.items.ItemType;
import org.example.app.logic.items.ItemsExecutor;
import org.example.app.logic.movement.CollisionDetection;

import javax.swing.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Combat {
    private static Enemy enemy;
    private static Player player;
    private static Map map;
    private static long lastPlayerAttackTime;
    private static long lastPlayerDefendTime;
    private static long lastPlayerItem1Time;
    private static long lastPlayerItem2Time;

    public static void setEnemy(Enemy enemy) {
        Combat.enemy = enemy;
    }
    public static void initialize(Map map) {
        Combat.map = map;
        Combat.player = map.getComponents().getDynamic().getPlayer();
        lastPlayerAttackTime = System.currentTimeMillis();
        lastPlayerDefendTime = System.currentTimeMillis();
    }

    public static void playerDefaultAttack() {
        if(enemy != null && checkCooldown(lastPlayerAttackTime, player.getAttackCooldown())&&!player.isDefendActive()) {
            enemy.setHp(enemy.getHp() - player.getAttackDamage());
            lastPlayerAttackTime = System.currentTimeMillis();
            if(enemy.getHp() <= 0) enemyDeath(enemy);
        }
    }
    public static void playerDefend(){
        if(enemy != null && checkCooldown(lastPlayerDefendTime, player.getDefendCooldown())) {
            System.out.println(player.getDefendTime());
            player.setDefendActive(true);
            lastPlayerDefendTime = System.currentTimeMillis();
            Timer timer = new Timer(player.getDefendTime(), argument -> {
                player.setDefendActive(false);
                System.out.println(player.isDefendActive());
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public static void playerItemAttack(CombatAction action) {
        if(!player.isCombatActive() || player.isDefendActive()) return;
        Item item = ItemsExecutor.findItemByAction(action);
        if(item == null) return;

        if(item.getItemType().equals(ItemType.SWORDBREAK)) {
            enemy.setHp(enemy.getHp() - ItemsConstants.SWORDBREAK_DAMAGE);
        } else if(item.getItemType().equals(ItemType.EYEWHIP)) {
            enemy.setHp(enemy.getHp() - ItemsConstants.EYEWHIP_DAMAGE);
        }

        if(enemy.getHp() <= 0) enemyDeath(enemy);
    }

    public static void enemyAttack(Enemy enemy) {
        if(player != null&&!player.isDefendActive()) {
            player.setHp(player.getHp() - enemy.getAttackDamage());
            if(player.getHp() <= 0) playerDeath();
        }
    }

    public static void enemyDeath(Enemy enemy) {
        enemy.setCombatActive(false);
        player.setCombatActive(false);
        map.getComponents().getDynamic().getEnemies().remove(enemy);
        if(hasPlayerItem(ItemType.LIFECRYSTAL)) {
            player.setHp(player.getHp() + ItemsConstants.LIFECRYSTAL_EFFECTIVNESS);
        }
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

    public static boolean hasPlayerItem(ItemType itemType) {
        return player.hasItem(itemType);
    }
}
