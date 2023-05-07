package org.example.app.logic.stats;

import lombok.*;
import org.example.app.components.map.components.dynamic.Enemy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class StatsEnemy {
    private Integer hp;
    private Integer attackDamage;
    private Integer attackCooldown;

    public void mapStatsToEnemy(Enemy enemy) {
        enemy.setHp(hp);
        enemy.setAttackDamage(attackDamage);
        enemy.setAttackCooldown(attackCooldown);
    }
}
