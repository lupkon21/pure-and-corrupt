package org.example.app.components.frame;

import org.example.app.components.frame.map.MapPanel;

import javax.swing.*;

public class Frame extends JFrame {
    private final MapPanel mapPanel;

    public Frame() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mapPanel = new MapPanel();
        this.add(mapPanel);

        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        super.setVisible(true);
    }

    public void hideMapPanel() {
        this.remove(mapPanel);
    }
}
