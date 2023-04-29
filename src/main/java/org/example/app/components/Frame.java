package org.example.app.components;

import org.example.app.components.map.MapPanel;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.logic.movement.Direction;
import org.example.app.constants.MapConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener, ActionListener {
    private final MapPanel mapPanel;
    private final Timer enemyMovementTimer;
    private long playerLastMovementTime;

    public Frame() {
        super("Pure and Corrupt");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mapPanel = new MapPanel();
        this.add(mapPanel);
        this.addKeyListener(this);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        enemyMovementTimer = new Timer(MapConstants.ENEMY_MOVEMENT_TIMER,this);
        enemyMovementTimer.start();

        playerLastMovementTime = System.currentTimeMillis();
        super.setVisible(true);
    }

    public void hideMapPanel() {
        enemyMovementTimer.stop();
        this.remove(mapPanel);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(System.currentTimeMillis() - playerLastMovementTime >= MapConstants.PLAYER_MOVEMENT_TIMER) {
            movePlayerByKeyCode(keyEvent.getKeyCode());
            playerLastMovementTime = System.currentTimeMillis();
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
            case 16:
                player.attack();
        }
        mapPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(enemyMovementTimer)) {
            mapPanel.getMap().getComponents().getDynamic().moveEnemies();
            mapPanel.repaint();
        }
    }
}
