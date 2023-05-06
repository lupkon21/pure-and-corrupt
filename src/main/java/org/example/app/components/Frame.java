package org.example.app.components;

import org.example.app.components.map.MapPanel;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.logic.combat.CombatAction;
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
        actionByKeyCode(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    private void actionByKeyCode(int keyCode) {
        switch (keyCode) {
            case 87:
                movePlayer(Direction.NORTH);
                break;
            case 68:
                movePlayer(Direction.EAST);
                break;
            case 83:
                movePlayer(Direction.SOUTH);
                break;
            case 65:
                movePlayer(Direction.WEST);
                break;
            case 16:
                combatActionPlayer(CombatAction.DEFAULT_ATTACK);
                break;
            case 27:
                pauseGame();
                break;
        }
    }

    private void movePlayer(Direction direction) {
        if(mapPanel.getMap().isGameOver()) return;
        Player player = this.mapPanel.getMap().getComponents().getDynamic().getPlayer();
        if(System.currentTimeMillis() - playerLastMovementTime >= MapConstants.PLAYER_MOVEMENT_TIMER) {
            playerLastMovementTime = System.currentTimeMillis();
            player.move(direction);
        }
        mapPanel.repaint();
    }

    private void combatActionPlayer(CombatAction action) {
        if(mapPanel.getMap().isGameOver()) return;
        Player player = this.mapPanel.getMap().getComponents().getDynamic().getPlayer();
        if(action.equals(CombatAction.DEFAULT_ATTACK)) {
            player.attack();
        }
    }

    private void pauseGame() {
        System.out.println("Pause menu");
    }

    public void gameOver() {
        enemyMovementTimer.stop();
        this.remove(mapPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(mapPanel.getMap().isGameOver()) {
            enemyMovementTimer.stop();
        } else if(actionEvent.getSource().equals(enemyMovementTimer)) {
            mapPanel.getMap().getComponents().getDynamic().moveEnemies();
            mapPanel.repaint();
        }
    }
}
