package org.example.app.components.frame;

import org.example.app.components.frame.map.MapPanel;
import org.example.app.components.map.dynamic.Player;
import org.example.app.components.map.movement.Direction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {
    private final MapPanel mapPanel;

    public Frame() {
        super("Pure and Corrupt");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mapPanel = new MapPanel();
        this.add(mapPanel);

        this.addKeyListener(this);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        super.setVisible(true);
    }



    public void hideMapPanel() {
        this.remove(mapPanel);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        movePlayerByKeyCode(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    private void movePlayerByKeyCode(int keyCode) {
        Player player = this.mapPanel.getMap().getComponents().getDynamic().getPlayer();
        switch (keyCode) {
            case 87:
                player.move(Direction.NORTH);
                break;
            case 68:
                player.move(Direction.EAST);
                break;
            case 83:
                player.move(Direction.SOUTH);
                break;
            case 65:
                player.move(Direction.WEST);
                break;
        }
        mapPanel.repaint();
    }
}
