package cn.zzditto.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {
    private GameUtil() {
    }

    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            // 尝试多种方式加载资源
            URL u = GameUtil.class.getClassLoader().getResource(path);
            if (u == null) {
                // 尝试使用 ClassLoader.getSystemResource
                u = ClassLoader.getSystemResource(path);
            }
            if (u == null) {
                // 尝试使用 Class.getResource
                u = GameUtil.class.getResource("/" + path);
            }
            if (u == null) {
                // 尝试直接从文件系统加载
                java.io.File file = new java.io.File(path);
                if (file.exists()) {
                    u = file.toURI().toURL();
                }
            }
            
            if (u != null) {
                bi = ImageIO.read(u);
            } else {
                System.err.println("无法加载资源: " + path);
                // 创建一个占位图片
                bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
                java.awt.Graphics2D g2d = bi.createGraphics();
                g2d.setColor(java.awt.Color.RED);
                g2d.fillRect(0, 0, 50, 50);
                g2d.setColor(java.awt.Color.WHITE);
                g2d.drawString("?", 20, 30);
                g2d.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}