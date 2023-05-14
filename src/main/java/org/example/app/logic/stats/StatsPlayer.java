package org.example.app.logic.stats;

import lombok.*;
import org.example.app.components.map.components.dynamic.Player;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class StatsPlayer {
    private Integer hp;
    private Integer attackDamage;
    private Integer attackCooldown;
    private Integer defendCooldown;
    private Integer defendTime;

    public void mapStatsToPlayer(Player player) {
        player.setHp(hp);
        player.setAttackDamage(attackDamage);
        player.setAttackCooldown(attackCooldown);
        player.setDefendCooldown(defendCooldown);
        player.setDefendTime(defendTime);
    }
}
