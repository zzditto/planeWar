package cn.zzditto.game;

import java.awt.*;

/*
* 炮弹
* */
public class Shell extends GameObject{
    double degree;

    public Shell(){
        x=200;
        y=200;
        width=10;
        hight=10;
        speed=4;
        degree=Math.random()*Math.PI*2;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);

        g.fillOval((int)x,(int)y,width,hight);

        /*炮弹沿着任意角度去飞*/
        x += speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        /*炮弹碰到边沿反弹*/
        if(x <0 || x >Constant.GAMEFRAME_WIDTH-width){
            degree = Math.PI-degree;
        }
        if( y< 30 || y> Constant.GAMEFRAME_HEIGHT - hight){
            degree=-degree;
        }

        g.setColor(c);


    }

}
