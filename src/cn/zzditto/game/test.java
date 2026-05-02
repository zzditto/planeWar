package cn.zzditto.game;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image img = GameUtil.getImage("imagetest/explodetest/e3.gif");
        g.drawImage(img, 100, 200, null);
    }

    public void testFrame() {
        this.setTitle("测试窗口");
        this.setVisible(true);
        this.setSize(300, 300);
        this.setLocation(300, 300);
    }

    public static void main(String[] args) {
        test t = new test();
        t.testFrame();
    }
}