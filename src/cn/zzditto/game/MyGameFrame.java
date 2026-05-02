package cn.zzditto.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {
    Image planeImg = GameUtil.getImage("imagetest/飞机plane.png");
    Image bg = GameUtil.getImage("imagetest/bg.jpg");

    Plane plane = new Plane(planeImg, 550, 550);

    Shell[] shells = new Shell[40];

    Explode explode;

    Date startDate = new Date();
    Date endDate;
    int seconds;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Color c = g.getColor();
        g.drawImage(bg, 0, 0, null);

        plane.drawSelf(g);

        for (int i = 0; i < shells.length; i++) {
            shells[i].draw(g);

            boolean hit = shells[i].getRect().intersects(plane.getRect());
            if (hit) {
                plane.live = false;
                if (explode == null) {
                    explode = new Explode(plane.x, plane.y);
                    endDate = new Date();
                    seconds = (int) (endDate.getTime() - startDate.getTime()) / 1000;
                }
                explode.draw(g);
            }
        }

        if (!plane.live) {
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.drawString("时间：" + seconds + "秒", (int) plane.x, (int) plane.y);
        }
        g.setColor(c);
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.reduceDirection(e);
        }
    }

    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void launchFrame() {
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(Constant.GAMEFRAME_WIDTH, Constant.GAMEFRAME_HEIGHT);
        this.setLocation(600, 300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start();
        addKeyListener(new KeyMonitor());

        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAMEFRAME_WIDTH, Constant.GAMEFRAME_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}