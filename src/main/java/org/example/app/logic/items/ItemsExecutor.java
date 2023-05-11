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
            player.getItems().add(item);
        } else if(itemType.equals(ItemType.SWORDBREAK)) {
            player.getItems().add(item);
        } else if(itemType.equals(ItemType.EYEWHIP)) {
            player.getItems().add(item);
        } else if(itemType.equals(ItemType.THORNPARTY)) {
            player.setAttackDamage(ItemsConstants.THORNPARTY_DAMAGE);
        } else if(itemType.equals(ItemType.CORRUPTED_BOOTS)) {
            MapConstants.PLAYER_MOVEMENT_TIMER = ItemsConstants.CORRUPTED_BOOTS_MOVESPEED;
        } else if(itemType.equals(ItemType.MIGHTY_TOOTH)) {
            player.setAttackDamage(ItemsConstants.THORNPARTY_DAMAGE);
        } else if(itemType.equals(ItemType.FLESH_CROSS)) {
            System.out.println(MapConstants.PLAYER_MAX_HP);
            player.setHp(ItemsConstants.FLESH_CROSS_MAX_HP);
        }
    }
}
