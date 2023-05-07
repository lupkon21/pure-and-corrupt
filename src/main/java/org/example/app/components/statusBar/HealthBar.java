package org.example.app.components.statusBar;

import lombok.*;
import org.example.app.constants.MapConstants;
import org.example.app.constants.StatusBarConstants;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@ToString
public class HealthBar extends JPanel {
    private Integer status;
    private String label;

    public HealthBar(Integer status) {
        this.status = status;
        label = "Your HP:";
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(StatusBarConstants.HEALTH_BAR_SIZE_X, MapConstants.GRID_CELL_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0, StatusBarConstants.HEALTH_BAR_SIZE_X, MapConstants.GRID_CELL_SIZE);

        g2.setColor(Color.ORANGE);
        g2.fillRect(MapConstants.GRID_CELL_SIZE * 4 - StatusBarConstants.HEALTH_BAR_SPACE,0, StatusBarConstants.HEALTH_BAR_SIZE_X - (MapConstants.GRID_CELL_SIZE * 4 - StatusBarConstants.HEALTH_BAR_SPACE), MapConstants.GRID_CELL_SIZE);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString(label,MapConstants.GRID_CELL_SIZE / 2, (int) (MapConstants.GRID_CELL_SIZE * 0.66));

        g2.setColor(Color.red);
        for(int i = 0; i < status; i++) {
            g2.fillRect(MapConstants.GRID_CELL_SIZE * (i + 4) - (i * StatusBarConstants.HEALTH_BAR_SPACE), StatusBarConstants.HEALTH_BAR_SPACE, MapConstants.GRID_CELL_SIZE - StatusBarConstants.HEALTH_BAR_SPACE * 2, MapConstants.GRID_CELL_SIZE - StatusBarConstants.HEALTH_BAR_SPACE * 2);
        }
    }
}
