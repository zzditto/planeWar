package cn.zzditto.game;

import java.io.*;
import java.util.Properties;

public class GameConfig {
    private static final String CONFIG_FILE = "game.properties";
    private Properties properties;

    public GameConfig() {
        properties = new Properties();
        loadConfig();
    }

    private void loadConfig() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            // 使用默认配置
            setDefaultConfig();
        }
    }

    private void setDefaultConfig() {
        properties.setProperty("player.speed", "4");
        properties.setProperty("bullet.speed", "8");
        properties.setProperty("enemy.speed", "2");
        properties.setProperty("shell.speed", "4");
        properties.setProperty("max.bullets", "5");
        properties.setProperty("max.enemies", "5");
        properties.setProperty("max.shells", "20");
    }

    public void saveConfig() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, "Game Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setInt(String key, int value) {
        properties.setProperty(key, String.valueOf(value));
    }
}