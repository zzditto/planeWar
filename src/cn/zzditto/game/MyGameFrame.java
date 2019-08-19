package cn.zzditto.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/*
* 后续升级：增加难度选择，简单，普通，王者。选择不同的选项[使用按键数值匹配]，使用不同的数值，speed和shells.length
* 飞机需要边境监测
* */
public class MyGameFrame extends Frame {


    Image planeimg = GameUtil.getImage("imagetest/飞机plane.png");
    Image bg = GameUtil.getImage("imagetest/bg.jpg");

    Plane plane=new Plane(planeimg,550,550);

    /*使用数组定义多个炮弹*/
    Shell[] shells=new Shell[40];

    /*爆炸类*/
    Explode bao;

    /*计时*/
    Date startDate=new Date();
    Date endDate;
    int seconds;


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Color c = g.getColor();
        g.drawImage(bg,0,0,null);

        plane.drawSelf(g);

        /*画炮弹*/
        for (int i=0;i<shells.length;i++){
            shells[i].draw(g);

          Boolean peng =  shells[i].getRect().intersects(plane.getRect());
          if(peng){

              plane.live=false;
              if(bao == null){
                  bao = new Explode(plane.x,plane.y);

                  endDate=new Date();
                   seconds=(int)(endDate.getTime()-startDate.getTime())/1000;
              }
              bao.draw(g);
          }
        }

        if(!plane.live) {
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.drawString("时间：" + seconds + "秒", (int) plane.x, (int) plane.y);
        }
        g.setColor(c);
    }

    /*定义键盘监听内部类*/
    class keyMoniter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDeriction(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.reduceDeriction(e);
        }
    }

    /*内部类，定义多线程，重新加载paint*/
    class paintThread extends Thread{
        @Override
        public void run() {
            while (true){
                repaint();

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


/*初始化窗口*/
    public void lanuchFrame(){

        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(Constant.GAMEFRAME_WIDTH,Constant.GAMEFRAME_HEIGHT);
        this.setLocation(600,300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        /*启动重画paint的线程*/
        new paintThread().start();

        /*键盘监听*/
        addKeyListener(new keyMoniter());

        /*初始化80个炮弹*/
        for (int i=0;i<shells.length;i++){
            shells[i]=new Shell();
        }
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.lanuchFrame();
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAMEFRAME_WIDTH,Constant.GAMEFRAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }


}
