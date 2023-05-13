package org.example.app.logic.items;

import org.example.app.components.map.Map;
import org.example.app.components.map.components.dynamic.Item;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.constants.ItemsConstants;
import org.example.app.constants.MapConstants;
import org.example.app.logic.combat.CombatAction;

import java.util.ArrayList;

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
                player.getItems().add(item);
            }
        } else if(itemType.equals(ItemType.SWORDBREAK) && !player.hasItem(itemType)) {
            player.getItems().add(item);
        } else if(itemType.equals(ItemType.EYEWHIP) && !player.hasItem(itemType)) {
            player.getItems().add(item);
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

    public static Item findItemByAction(CombatAction action) {
        ArrayList<Item> activatableItems = findActivatableItems();
        if(activatableItems.size() == 0) return null;

        if(action.equals(CombatAction.ITEM_ATTACK_1) && activatableItems.get(0) != null) {
            return activatableItems.get(0);
        }
        if(action.equals(CombatAction.ITEM_ATTACK_2) && activatableItems.get(1) != null) {
            return activatableItems.get(1);
        }

        return null;
    }

    public static ArrayList<Item> findActivatableItems() {
        ArrayList<Item> activatableItems = new ArrayList<>();
        for(Item i : player.getItems()) {
            if(i.isActivatable()) activatableItems.add(i);
        }
        return activatableItems;
    }
}
