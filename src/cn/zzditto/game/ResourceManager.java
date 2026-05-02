package cn.zzditto.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static Map<String, Image> imageCache = new HashMap<>();
    
    public static Image getImage(String path) {
        if (!imageCache.containsKey(path)) {
            imageCache.put(path, GameUtil.getImage(path));
        }
        return imageCache.get(path);
    }
    
    public static void clearCache() {
        imageCache.clear();
    }
}