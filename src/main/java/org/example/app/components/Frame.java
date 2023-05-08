package org.example.app.components;

import org.example.app.components.map.MapPanel;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.components.pauseMenu.PauseMenuPanel;
import org.example.app.components.statusBar.StatusBarPanel;
import org.example.app.logic.combat.CombatAction;
import org.example.app.logic.movement.Direction;
import org.example.app.constants.MapConstants;
import org.example.app.logic.render.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener, ActionListener {
    private MapPanel mapPanel;
    private final StatusBarPanel statusBarPanel;
    private final PauseMenuPanel pauseMenuPanel;
    private Timer enemyMovementTimer;
    private Timer statusRefreshTimer;
    private long playerLastMovementTime;

    public Frame() {
        super("Pure and Corrupt");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        mapPanel = new MapPanel();
        statusBarPanel = new StatusBarPanel(mapPanel.getMap().getComponents().getDynamic().getPlayer().getHp() / 10);
        pauseMenuPanel = new PauseMenuPanel();
        this.add(mapPanel, BorderLayout.NORTH);
        this.add(statusBarPanel, BorderLayout.SOUTH);

        this.addKeyListener(this);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        initializeTimers();
        Render.setFrame(this);
        super.setVisible(true);
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
            case 10:
                resumeGame();
                break;
        }
    }

    private void movePlayer(Direction direction) {
        if(isGamePausedOrOver()) return;
        Player player = this.mapPanel.getMap().getComponents().getDynamic().getPlayer();
        if(System.currentTimeMillis() - playerLastMovementTime >= MapConstants.PLAYER_MOVEMENT_TIMER) {
            playerLastMovementTime = System.currentTimeMillis();
            player.move(direction);
        }
        mapPanel.repaint();
    }

    private void combatActionPlayer(CombatAction action) {
        if(isGamePausedOrOver()) return;
        Player player = this.mapPanel.getMap().getComponents().getDynamic().getPlayer();
        if(action.equals(CombatAction.DEFAULT_ATTACK)) {
            player.attack();
        }
    }

    private void pauseGame() {
        if(isGamePausedOrOver()) return;

        mapPanel.getMap().setGamePaused(true);
        stopTimers();
        this.remove(mapPanel);
        this.remove(statusBarPanel);
        this.add(pauseMenuPanel, BorderLayout.CENTER);
        this.pack();
        this.repaint();
    }

    private void resumeGame() {
        if(!mapPanel.getMap().isGamePaused()) return;

        mapPanel.getMap().setGamePaused(false);
        startTimers();
        this.add(mapPanel, BorderLayout.NORTH);
        this.add(statusBarPanel, BorderLayout.SOUTH);
        this.remove(pauseMenuPanel);
        this.pack();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(isGamePausedOrOver()) {
            stopTimers();
            statusBarPanel.getHealthBar().setStatus(0);
            statusBarPanel.getHealthBar().repaint();
        } else if(actionEvent.getSource().equals(enemyMovementTimer)) {
            mapPanel.getMap().getComponents().getDynamic().moveEnemies();
            mapPanel.repaint();
        } else if(actionEvent.getSource().equals(statusRefreshTimer)) {
            statusBarPanel.getHealthBar().setStatus(mapPanel.getMap().getComponents().getDynamic().getPlayer().getHp() / 10);
            statusBarPanel.getHealthBar().repaint();
        }
    }

    private boolean isGamePausedOrOver() {
        return mapPanel.getMap().isGameOver() || mapPanel.getMap().isGamePaused();
    }

    public void initializeTimers() {
        enemyMovementTimer = new Timer(MapConstants.ENEMY_MOVEMENT_TIMER,this);
        statusRefreshTimer = new Timer(1000, this);
        enemyMovementTimer.start();
        statusRefreshTimer.start();

        playerLastMovementTime = System.currentTimeMillis();
    }

    public void stopTimers() {
        enemyMovementTimer.stop();
        statusRefreshTimer.stop();
        mapPanel.getMap().getComponents().getDynamic().stopAttackEnemies();
    }

    public void startTimers() {
        enemyMovementTimer.start();
        statusRefreshTimer.start();
        mapPanel.getMap().getComponents().getDynamic().startAttackEnemies();
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }
}
