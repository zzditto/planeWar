package cn.zzditto.game;

import java.awt.*;

public class Enemy extends GameObject {
    private static Image enemyImg;
    private boolean useImage = false;
    
    static {
        // 尝试加载敌机图片
        enemyImg = ResourceManager.getImage("assets/enemy.png");
    }
    
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = Constant.ENEMY_SPEED;
        
        if (enemyImg != null) {
            this.width = enemyImg.getWidth(null);
            this.height = enemyImg.getHeight(null);
            this.useImage = true;
        } else {
            this.width = 40;
            this.height = 40;
        }
    }

    public void draw(Graphics g) {
        if (useImage && enemyImg != null) {
            g.drawImage(enemyImg, (int) x, (int) y, null);
        } else {
            // 绘制敌机形状（简单的三角形）
            Color c = g.getColor();
            g.setColor(Color.GREEN);
            int[] xPoints = {(int) x, (int) x + width / 2, (int) x + width};
            int[] yPoints = {(int) y + height, (int) y, (int) y + height};
            g.fillPolygon(xPoints, yPoints, 3);
            g.setColor(c);
        }
        y += speed;
    }
}