package cn.zzditto.game;

import java.awt.*;

/*定义游戏的父类，提供一些都必须的属性*/
public class GameObject {
    Image img;
    double x,y;
    int speed;
    int width,hight;

    /*定义方法，画笔使用这些属性，画出所需的图形*/
    public void drawSelf(Graphics g){
        g.drawImage(img,(int)x,(int)y,null);
    }

    /*无参构造器*/
    public GameObject() {
    }

    /*有参构造器*/
    public GameObject(Image img, double x, double y, int speed, int wight, int hight) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.hight = hight;
    }

    /**/
    public GameObject(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    /*返回矩形*/
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,width,hight);
    }
}
