package org.example.app.graphics;

import org.example.app.graphics.map.MapPanel;
import org.example.app.graphics.map.components.dynamic.Player;
import org.example.app.logic.movement.Direction;
import org.example.app.constants.MapConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener, ActionListener {
    private final MapPanel mapPanel;
    private final Timer timer;
    private long startTime;

    public Frame() {
        super("Pure and Corrupt");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mapPanel = new MapPanel();
        this.add(mapPanel);

        timer = new Timer(130,this);

        this.addKeyListener(this);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        startTime = System.currentTimeMillis();
        timer.start();
        super.setVisible(true);
    }



    public void hideMapPanel() {
        timer.stop();
        this.remove(mapPanel);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(System.currentTimeMillis() - startTime >= MapConstants.PLAYER_MOVEMENT_TIMER) {
            movePlayerByKeyCode(keyEvent.getKeyCode());
            startTime = System.currentTimeMillis();
        }
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(timer)) {
            mapPanel.getMap().getComponents().getDynamic().moveEnemies();
            mapPanel.repaint();
        }
    }
}
