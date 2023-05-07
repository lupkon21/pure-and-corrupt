package org.example.app.components.pauseMenu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.app.constants.PauseMenuConstants;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@ToString
public class PauseMenuPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PauseMenuConstants.SIZE_X,PauseMenuConstants.SIZE_Y);
    }
}
