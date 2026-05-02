package cn.zzditto.game;

import java.awt.*;

public class Bullet extends GameObject {
    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 10;
        this.speed = Constant.BULLET_SPEED;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(c);
    }
}