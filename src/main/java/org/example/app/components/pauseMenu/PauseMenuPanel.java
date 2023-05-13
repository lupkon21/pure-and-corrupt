package org.example.app.components.pauseMenu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.app.constants.MapConstants;
import org.example.app.constants.PauseMenuConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Getter
@Setter
@ToString
public class PauseMenuPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        g2.setFont(new Font("Impact", Font.BOLD, 100));
        g2.drawString("Pure & Corrupt", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 3));
        g2.setFont(new Font("Impact", Font.BOLD, 200));
        g2.drawString("PAUSED", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 8));

        g2.setFont(new Font("Impact", Font.PLAIN, 48));
        g2.drawString("Controls:", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 10));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("WASD - move", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 11));
        g2.drawString("SHIFT - attack", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 12));
        g2.drawString("Q, E - activate items", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 13));
        g2.drawString("SPACE - block", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 14));
        g2.drawString("ESC - pause", (MapConstants.GRID_CELL_SIZE * 3), (MapConstants.GRID_CELL_SIZE * 15));

        g2.setColor(Color.RED);
        int LORE_OFFSET = 12;
        g2.setFont(new Font("Impact", Font.PLAIN, 48));
        g2.drawString("Lore:", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 10));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString("Hell is real. And so is Heaven, but people seem like they've forgotten that. The world was taken over by sin.", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 11));
        g2.drawString("That fueled the forces of Hell to the point where they became much stronger than Heaven and humans ever could imagine.", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 12));
        g2.drawString("Hell's corruption became so powerful, that the very ground beneath places important to the Church trembled until it ", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 13));
        g2.drawString("broke and sank underground, closer to Hell itself. Demons, Chaos spawn, Corrupted folk, all invaded the lands of humans ", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 14));
        g2.drawString("and spread fear and destruction. Humans fled right back to what they abandoned.", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 15));
        g2.drawString("The Highest Church, worshiping the merciful Heaven. Church was the only thing standing between them and total destruction. ", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 16));
        g2.drawString("The Church and their soldiers. Paladins. Such as you. The best of the best. The most pure and uncorrupted of them all.", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 17));
        g2.drawString("And the Church chose you specifically to go down where it all started. Through the sunken ground, right to the center of Hell and end it all for good.", (MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 18));
        g2.drawString("What they forgot to tell you are the bodies of your comrades that have fallen on the very same mission.\n",(MapConstants.GRID_CELL_SIZE * LORE_OFFSET), (MapConstants.GRID_CELL_SIZE * 19));

        BufferedImage lifeCrystal = loadImageFromFile("src/main/resources/assets/3/1.png");
        g2.drawImage(lifeCrystal, (MapConstants.GRID_CELL_SIZE * 2), (MapConstants.GRID_CELL_SIZE * 20), null);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Life crystal:", (MapConstants.GRID_CELL_SIZE * 4), (MapConstants.GRID_CELL_SIZE * 21));
        g2.setFont(new Font("Impact", Font.PLAIN, 16));
        g2.drawString(" when inserted to the pummel of the purest weapon, it drains the life force of the foes that fall under the might of your pure weapon.", (MapConstants.GRID_CELL_SIZE * 10), (MapConstants.GRID_CELL_SIZE * 21));

        BufferedImage mightyTooth = loadImageFromFile("src/main/resources/assets/3/4.png");
        g2.drawImage(mightyTooth, (MapConstants.GRID_CELL_SIZE * 2), (MapConstants.GRID_CELL_SIZE * 21), null);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Mighty tooth:", (MapConstants.GRID_CELL_SIZE * 4), (MapConstants.GRID_CELL_SIZE * 22));
        g2.setFont(new Font("Impact", Font.PLAIN, 16));
        g2.drawString(" the moment it touches you, it becomes a part of your armor, of your weapon and of you, thus making you more dangerous and more lethal.", (MapConstants.GRID_CELL_SIZE * 11), (MapConstants.GRID_CELL_SIZE * 22));

        BufferedImage thrornParry = loadImageFromFile("src/main/resources/assets/3/5.png");
        g2.drawImage(thrornParry, (MapConstants.GRID_CELL_SIZE * 2), (MapConstants.GRID_CELL_SIZE * 22), null);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Thorn parry:", (MapConstants.GRID_CELL_SIZE * 4), (MapConstants.GRID_CELL_SIZE * 23));
        g2.setFont(new Font("Impact", Font.PLAIN, 16));
        g2.drawString(" your shield is grown through with thorny vines. You feel the shield strengthen and new blood coarsing through your veins.", (MapConstants.GRID_CELL_SIZE * 11), (MapConstants.GRID_CELL_SIZE * 23));

        BufferedImage corruptedBoots = loadImageFromFile("src/main/resources/assets/3/6.png");
        g2.drawImage(corruptedBoots, (MapConstants.GRID_CELL_SIZE * 2), (MapConstants.GRID_CELL_SIZE * 23), null);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Corrupted boots:", (MapConstants.GRID_CELL_SIZE * 4), (MapConstants.GRID_CELL_SIZE * 24));
        g2.setFont(new Font("Impact", Font.PLAIN, 16));
        g2.drawString(" a pair of very fancy plated shoes. They are covered in many toes. They make you feel faster... and grossed out. They won't come off.", (MapConstants.GRID_CELL_SIZE * 11), (MapConstants.GRID_CELL_SIZE * 24));

        BufferedImage fleshCross = loadImageFromFile("src/main/resources/assets/3/7.png");
        g2.drawImage(fleshCross, (MapConstants.GRID_CELL_SIZE * 2), (MapConstants.GRID_CELL_SIZE * 24), null);
        g2.setFont(new Font("Impact", Font.PLAIN, 32));
        g2.drawString("Flesh Cross:", (MapConstants.GRID_CELL_SIZE * 4), (MapConstants.GRID_CELL_SIZE * 25));
        g2.setFont(new Font("Impact", Font.PLAIN, 16));
        g2.drawString(" a vile artifact. Fills you with foreign blood and seals all of your wounds with the flesh of unknown origin.", (MapConstants.GRID_CELL_SIZE * 11), (MapConstants.GRID_CELL_SIZE * 25));

        g2.setFont(new Font("Impact", Font.PLAIN, 25));
        g2.setColor(Color.RED);
        g2.drawString("Tip: You have to kill all the enemies to progress to the next level.", (MapConstants.GRID_CELL_SIZE * 11), (MapConstants.GRID_CELL_SIZE * 27));

        g2.setColor(Color.RED);
        g2.drawRect((MapConstants.GRID_CELL_SIZE * 30), (MapConstants.GRID_CELL_SIZE),(MapConstants.GRID_CELL_SIZE * 9),(MapConstants.GRID_CELL_SIZE * 2));
        g2.setFont(new Font("Impact", Font.BOLD, 48));
        g2.drawString("QUIT GAME",(int) (MapConstants.GRID_CELL_SIZE * 31.5), (int) (MapConstants.GRID_CELL_SIZE * 2.5));
        g2.drawRect((MapConstants.GRID_CELL_SIZE * 30), (MapConstants.GRID_CELL_SIZE * 4),(MapConstants.GRID_CELL_SIZE * 9), (MapConstants.GRID_CELL_SIZE * 2));
        g2.drawString("RESUME",(int) (MapConstants.GRID_CELL_SIZE * 32.25), (int) (MapConstants.GRID_CELL_SIZE * 5.5));

    }

    private static BufferedImage loadImageFromFile(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
    }
}
