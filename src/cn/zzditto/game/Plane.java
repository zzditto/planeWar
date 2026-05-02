package cn.zzditto.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {
    boolean left, up, right, down;
    boolean live = true;

    public void drawSelf(Graphics g) {
        if (live) {
            g.drawImage(img, (int) x, (int) y, null);
            if (left) {
                x -= speed;
            }
            if (up) {
                y -= speed;
            }
            if (right) {
                x += speed;
            }
            if (down) {
                y += speed;
            }
            // 边界检测
            if (x < 0) x = 0;
            if (y < 30) y = 30;
            if (x > Constant.GAMEFRAME_WIDTH - width) x = Constant.GAMEFRAME_WIDTH - width;
            if (y > Constant.GAMEFRAME_HEIGHT - height) y = Constant.GAMEFRAME_HEIGHT - height;
        }
    }

    public Plane(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = Constant.PLAYER_SPEED;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    public void reduceDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}