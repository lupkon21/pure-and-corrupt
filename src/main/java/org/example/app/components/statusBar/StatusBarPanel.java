package org.example.app.components.statusBar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.app.constants.MapConstants;
import org.example.app.constants.StatusBarConstants;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@ToString
public class StatusBarPanel extends JPanel {

    private HealthBar healthBar;
    private ItemsBar itemsBar;

    public StatusBarPanel(Integer status) {
        this.setLayout(null);
        healthBar = new HealthBar(status);
        healthBar.setBounds(MapConstants.GRID_CELL_SIZE * 7, MapConstants.GRID_CELL_SIZE, (int) healthBar.getPreferredSize().getWidth(), (int) healthBar.getPreferredSize().getHeight());
        itemsBar = new ItemsBar();
        itemsBar.setBounds(MapConstants.GRID_CELL_SIZE * 23, 0, (int) itemsBar.getPreferredSize().getWidth(), (int) itemsBar.getPreferredSize().getHeight());
        this.add(healthBar);
        this.add(itemsBar);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(StatusBarConstants.SIZE_X,StatusBarConstants.SIZE_Y);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0, StatusBarConstants.SIZE_X,StatusBarConstants.SIZE_Y);
        itemsBar.repaint();
        healthBar.repaint();
    }
}
