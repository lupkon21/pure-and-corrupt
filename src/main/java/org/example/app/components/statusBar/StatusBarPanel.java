package org.example.app.components.statusBar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.app.constants.StatusBarConstants;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@ToString
public class StatusBarPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(StatusBarConstants.SIZE_X,StatusBarConstants.SIZE_Y);
    }
}
