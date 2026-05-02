package cn.zzditto.game;

public class Constant {
    public static final int GAMEFRAME_WIDTH = 600;
    public static final int GAMEFRAME_HEIGHT = 600;
    
    // 游戏配置 - 降低速度
    public static final int PLAYER_SPEED = 3;
    public static final int BULLET_SPEED = 6;
    public static final int ENEMY_SPEED = 1;
    public static final int SHELL_SPEED = 2;
    
    // 游戏规则 - 减少数量
    public static final int MAX_BULLETS = 3;
    public static final int MAX_ENEMIES = 3;
    public static final int MAX_SHELLS = 10;
    
    // 生成间隔（帧数）- 增加间隔
    public static final int ENEMY_SPAWN_INTERVAL = 120;  // 2秒
    public static final int SHELL_SPAWN_INTERVAL = 180;   // 3秒
}