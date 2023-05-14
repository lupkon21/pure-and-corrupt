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
public class EndingScreenPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        g2.setFont(new Font("Impact", Font.BOLD, 100));
        g2.drawString("Congratulations,", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 3));
        g2.setFont(new Font("Impact", Font.BOLD, 200));
        g2.drawString("YOU WON!", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 8));

        g2.setFont(new Font("Impact", Font.PLAIN, 48));
        g2.drawString("Credits:", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 10));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Programming - Lukáš Protiva, Martin Šoupa", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 11));
        g2.drawString("Design - Martin Šoupa, Lukáš Protiva", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 12));
        g2.drawString("Story - Martin Šoupa", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 13));
        g2.drawString("Graphics - František Břenek", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 14));

        g2.setColor(Color.RED);
        g2.setFont(new Font("Impact", Font.PLAIN, 48));
        g2.drawString("Story ending:", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 17));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("As the last remains of the Hell's corruption fall under your blade, you feel relief. But for just a moment, before you enter the portal to the next layer of Hell,", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 18));
        g2.drawString("You realize there isn't any more left. You killed them all. Purified all the abominations in the eyes of Heavens. The portal swirls, but it finally steadies and", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 19));
        g2.drawString("you can see yourself in the reflection. Or what has become of you. You look far worse then anything that fell under your Pure yet Corrupted hands. Their strength", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 20));
        g2.drawString("was in numbers. Yours is in raw power, gathered through all the different layers of Hell and grafted onto you. As the realization of power envelops your mind, you", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 21));
        g2.drawString("step into the portal and leave the demons of flesh that crawled out of your corrupted armor behind. You have turned into the new Hell Overlord. You have won. You alone.", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 22));

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
