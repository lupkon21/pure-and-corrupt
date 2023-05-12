package org.example.app.logic.items;

import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Item;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.constants.ItemsConstants;
import org.example.app.constants.MapConstants;

public class ItemsExecutor {
    private static Player player;

    public static void initialize(Map map) {
        player = map.getComponents().getDynamic().getPlayer();
    }

    public static void execute(Item item) {
        ItemType itemType = item.getItemType();
        System.out.println(itemType);

        if(itemType.equals(ItemType.LIFECRYSTAL)) {
            if(player.hasItem(ItemType.LIFECRYSTAL)) {
                ItemsConstants.LIFECRYSTAL_EFFECTIVNESS += ItemsConstants.LIFECRYSTAL_EFFECTIVNESS_INITIAL;
            } else {
                player.getItems().set(0,item);
            }
        } else if(itemType.equals(ItemType.SWORDBREAK)) {
            player.getItems().set(1,item);
        } else if(itemType.equals(ItemType.EYEWHIP)) {
            player.getItems().set(2,item);
        } else if(itemType.equals(ItemType.THORNPARRY)) {
            player.setDefendTime(player.getDefendTime() + ItemsConstants.THORNPARRY_DEFEND_TIME);
        } else if(itemType.equals(ItemType.CORRUPTED_BOOTS)) {
            MapConstants.PLAYER_MOVEMENT_TIMER = ItemsConstants.CORRUPTED_BOOTS_MOVESPEED;
        } else if(itemType.equals(ItemType.MIGHTY_TOOTH)) {
            player.setAttackDamage(player.getAttackDamage() + ItemsConstants.MIGHTY_TOOTH_DAMAGE_AMPLIFICATION);
        } else if(itemType.equals(ItemType.FLESH_CROSS)) {
            player.setHp(ItemsConstants.FLESH_CROSS_MAX_HP);
        }
    }
}
