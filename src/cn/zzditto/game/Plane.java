package cn.zzditto.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {

    boolean left,up,right,down;
    boolean live=true;
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
        }else{

        }
    }

    public Plane(Image img, int x, int y) {
        this.img=img;
        this.x=x;
        this.y=y;
        this.speed=4;
        this.width=img.getWidth(null);
        this.hight=img.getHeight(null);
    }

    /*按键  方法*/
    public void addDeriction(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            left=true;
            break;
            case KeyEvent.VK_UP:
                up=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
        }
    }

    /*松开  方法*/
    public void reduceDeriction(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_UP:
                up=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
        }
    }

}
