package org.example.app.components.pauseMenu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.app.constants.MapConstants;
import org.example.app.constants.PauseMenuConstants;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@ToString
public class DeathSceenPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        g2.setFont(new Font("Impact", Font.BOLD, 100));
        g2.drawString("Pure & Corrupt", (MapConstants.GRID_CELL_SIZE * 5), (MapConstants.GRID_CELL_SIZE * 3));
        g2.setFont(new Font("Impact", Font.BOLD, 200));
        g2.drawString("YOU DIED", (MapConstants.GRID_CELL_SIZE * 5), (MapConstants.GRID_CELL_SIZE * 8));

        g2.setFont(new Font("Impact", Font.PLAIN, 48));
        g2.drawString("Credits:", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 10));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Programming - Lukáš Protiva, Martin Šoupa", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 11));
        g2.drawString("Design - Martin Šoupa, Lukáš Protiva", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 12));
        g2.drawString("Příběh - Martin Šoupa", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 13));
        g2.drawString("Grafika - ", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 14));

        g2.setColor(Color.RED);
        g2.drawRect((MapConstants.GRID_CELL_SIZE * 30), (MapConstants.GRID_CELL_SIZE),(MapConstants.GRID_CELL_SIZE * 9),(MapConstants.GRID_CELL_SIZE * 2));
        g2.setFont(new Font("Impact", Font.BOLD, 48));
        g2.drawString("QUIT GAME",(int) (MapConstants.GRID_CELL_SIZE * 31.5), (int) (MapConstants.GRID_CELL_SIZE * 2.5));
        g2.drawRect((MapConstants.GRID_CELL_SIZE * 30), (MapConstants.GRID_CELL_SIZE * 4),(MapConstants.GRID_CELL_SIZE * 9), (MapConstants.GRID_CELL_SIZE * 2));
        g2.drawString("RESTART",(int) (MapConstants.GRID_CELL_SIZE * 32.25), (int) (MapConstants.GRID_CELL_SIZE * 5.5));

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
    }
}
