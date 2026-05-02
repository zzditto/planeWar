package cn.zzditto.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {
    private Timer timer;
    private GamePanel gamePanel;
    private static final int FPS = 20; // 帧率
    private static final int DELAY = 1000 / FPS;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        timer = new Timer(DELAY, this);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void pause() {
        timer.stop();
    }

    public void resume() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.repaint();
    }
}