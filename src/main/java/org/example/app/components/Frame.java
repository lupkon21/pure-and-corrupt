package org.example.app.components;

import org.example.app.components.map.MapPanel;
import org.example.app.components.map.components.dynamic.Player;
import org.example.app.components.pauseMenu.DeathScreenPanel;
import org.example.app.components.pauseMenu.PauseMenuPanel;
import org.example.app.components.statusBar.StatusBarPanel;
import org.example.app.logic.combat.Combat;
import org.example.app.logic.combat.CombatAction;
import org.example.app.logic.movement.Direction;
import org.example.app.constants.MapConstants;
import org.example.app.logic.render.Loader;
import org.example.app.logic.render.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame implements KeyListener, ActionListener, MouseListener {
    private final MapPanel mapPanel;
    private final StatusBarPanel statusBarPanel;
    private final DeathScreenPanel deathScreenPanel;
    private final PauseMenuPanel pauseMenuPanel;
    private Timer enemyMovementTimer;
    private Timer statusRefreshTimer;
    private long playerLastMovementTime;

    public Frame() {
        super("Pure and Corrupt");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        mapPanel = new MapPanel();
        statusBarPanel = new StatusBarPanel(mapPanel.getMap().getComponents().getDynamic().getPlayer().getHp());
        deathScreenPanel = new DeathScreenPanel();
        pauseMenuPanel = new PauseMenuPanel();
        this.add(mapPanel, BorderLayout.NORTH);
        this.add(statusBarPanel, BorderLayout.SOUTH);
        this.setIconImage(Loader.loadAsset(MapConstants.ASSET_PATH + "icon/icon.png"));

        this.addKeyListener(this);
        this.addMouseListener(this);
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
            case 32:
                combatActionPlayer(CombatAction.DEFEND);
                break;
            case 27:
                if(isGamePausedOrOver()){
                    resumeGame();
                    break;
                }
                pauseGame();
                break;
            case 10:
                resumeGame();
                break;
            case 81:
                combatActionPlayer(CombatAction.ITEM_ATTACK_1);
                break;
            case 69:
                combatActionPlayer(CombatAction.ITEM_ATTACK_2);
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
        if(action.equals(CombatAction.DEFEND)) {
            player.defend();
        }
        if(action.equals(CombatAction.ITEM_ATTACK_1) || action.equals(CombatAction.ITEM_ATTACK_2)) {
            Combat.playerItemAttack(action);
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

    private void endGame() {
        stopTimers();
        this.remove(mapPanel);
        this.remove(statusBarPanel);
        this.add(deathScreenPanel, BorderLayout.CENTER);
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
        if(isGamePaused()) {
            stopTimers();
            statusBarPanel.getHealthBar().repaint();
            statusBarPanel.getItemsBar().repaint();
        } else if(isGameOver()){
            endGame();
        }
        else if(actionEvent.getSource().equals(enemyMovementTimer)) {
            mapPanel.getMap().getComponents().getDynamic().moveEnemies();
            mapPanel.repaint();
        } else if(actionEvent.getSource().equals(statusRefreshTimer)) {
            statusBarPanel.getHealthBar().setStatus(mapPanel.getMap().getComponents().getDynamic().getPlayer().getHp());
            statusBarPanel.getHealthBar().repaint();
            statusBarPanel.getItemsBar().repaint();
        }
    }

    private boolean isGamePausedOrOver() {
        return isGameOver() || isGamePaused();
    }

    private boolean isGamePaused() {
        return mapPanel.getMap().isGamePaused();
    }

    private boolean isGameOver() {
        return mapPanel.getMap().isGameOver();
    }

    public void initializeTimers() {
        enemyMovementTimer = new Timer(MapConstants.ENEMY_MOVEMENT_TIMER,this);
        statusRefreshTimer = new Timer(200, this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if(isGamePausedOrOver()){
            if(x>(MapConstants.GRID_CELL_SIZE * 30)&&x<(MapConstants.GRID_CELL_SIZE * 39)&&y>(MapConstants.GRID_CELL_SIZE*2)&&y<(MapConstants.GRID_CELL_SIZE*4)){
                System.exit(0);
            }
            if(isGamePaused() && x>(MapConstants.GRID_CELL_SIZE * 30)&&x<(MapConstants.GRID_CELL_SIZE * 39)&&y>(MapConstants.GRID_CELL_SIZE * 5)&&y<(MapConstants.GRID_CELL_SIZE*7)){
                resumeGame();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
