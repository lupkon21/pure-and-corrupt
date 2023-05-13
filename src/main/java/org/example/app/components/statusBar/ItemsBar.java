package org.example.app.components.statusBar;

import lombok.*;
import org.example.app.components.map.components.dynamic.Item;
import org.example.app.constants.ItemsConstants;
import org.example.app.constants.MapConstants;
import org.example.app.constants.StatusBarConstants;
import org.example.app.logic.combat.Combat;
import org.example.app.logic.items.ItemType;
import org.example.app.logic.items.ItemsExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
@ToString
public class ItemsBar extends JPanel {
    private ArrayList<Item> activatableItems;
    private ArrayList<Long> timers;
    private String label;

    public ItemsBar() {
        activatableItems = ItemsExecutor.findActivatableItems();
        timers = new ArrayList<>();
        timers.add(0, Combat.getLastPlayerItem1Time());
        timers.add(1, Combat.getLastPlayerItem2Time());
        label = "Active items:";
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(StatusBarConstants.ITEMS_BAR_SIZE_X, StatusBarConstants.ITEMS_BAR_SIZE_Y);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        activatableItems = ItemsExecutor.findActivatableItems();
        timers.set(0, Combat.getLastPlayerItem1Time());
        timers.set(1, Combat.getLastPlayerItem2Time());

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0, StatusBarConstants.ITEMS_BAR_SIZE_X, StatusBarConstants.ITEMS_BAR_SIZE_Y);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.drawString(label,0, (int) (2 * MapConstants.GRID_CELL_SIZE - 0.33 * MapConstants.GRID_CELL_SIZE));

        for(int i = 0; i < activatableItems.size(); i++) {
            Item item = activatableItems.get(i);
            int x = (i + 5) * (MapConstants.GRID_CELL_SIZE) + i * MapConstants.GRID_CELL_SIZE;
            g2.drawImage(item.getAsset(), x, MapConstants.GRID_CELL_SIZE, null);
            g2.drawString(" " + (calculateTime(item,i) == 0 ? " " : calculateTime(item,i) + " s"), x, (int) (2.66 * MapConstants.GRID_CELL_SIZE));
            g2.drawString(i == 0 ? "  Q" : "  E", x, (int) (MapConstants.GRID_CELL_SIZE * 0.75));
        }
    }

    private int calculateTime(Item item, Integer index) {
        int cooldown = 0;
        if(item.getItemType().equals(ItemType.SWORDBREAK)) cooldown = ItemsConstants.SWORDBREAK_COOLDOWN;
        else if (item.getItemType().equals(ItemType.EYEWHIP)) cooldown = ItemsConstants.EYEWHIP_COOLDOWN;

        int time = (int) ((cooldown - (System.currentTimeMillis() - timers.get(index))) / 1000);
        return Math.max(time, 0);
    }
}
