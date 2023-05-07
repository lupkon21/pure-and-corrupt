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

    public StatusBarPanel() {
        this.setLayout(null);
        healthBar = new HealthBar(10);
        healthBar.setBounds(MapConstants.GRID_CELL_SIZE * 4, MapConstants.GRID_CELL_SIZE, (int) healthBar.getPreferredSize().getWidth(), (int) healthBar.getPreferredSize().getHeight());
        this.add(healthBar);
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
        healthBar.repaint();
    }
}
