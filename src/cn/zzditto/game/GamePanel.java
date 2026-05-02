package cn.zzditto.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    // 游戏状态
    public enum GameState {
        START, PLAYING, PAUSED, GAME_OVER
    }

    private GameState gameState = GameState.START;
    private Image planeImg = ResourceManager.getImage("imagetest/飞机plane.png");
    private Image bg = ResourceManager.getImage("imagetest/bg.jpg");

    private Plane plane;
    private List<Shell> shells = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Explode explode;

    private Date startDate;
    private Date endDate;
    private int seconds;
    private int score = 0;
    private int highScore = 0;

    private Random random = new Random();
    private int enemySpawnTimer = 0;
    private int shellSpawnTimer = 0;
    private GameLoop gameLoop;
    
    // 游戏逻辑更新控制
    private long lastUpdateTime = 0;
    private static final long UPDATE_INTERVAL = 50; // 50ms 更新一次游戏逻辑

    public GamePanel() {
        setPreferredSize(new Dimension(Constant.GAMEFRAME_WIDTH, Constant.GAMEFRAME_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });

        initGame();
    }

    private void initGame() {
        plane = new Plane(planeImg, 300, 500); // 将飞机放在中间偏下位置
        shells.clear();
        bullets.clear();
        enemies.clear();
        explode = null;
        score = 0;
        seconds = 0;
        startDate = new Date();
        gameState = GameState.PLAYING;
        lastUpdateTime = System.currentTimeMillis();

        // 初始化游戏循环
        if (gameLoop == null) {
            gameLoop = new GameLoop(this);
            gameLoop.start();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();

        switch (gameState) {
            case START:
                if (key == KeyEvent.VK_ENTER) {
                    initGame();
                }
                break;
            case PLAYING:
                plane.addDirection(e);
                if (key == KeyEvent.VK_SPACE) {
                    fireBullet();
                }
                if (key == KeyEvent.VK_P) {
                    gameState = GameState.PAUSED;
                }
                break;
            case PAUSED:
                if (key == KeyEvent.VK_P) {
                    gameState = GameState.PLAYING;
                }
                break;
            case GAME_OVER:
                if (key == KeyEvent.VK_ENTER) {
                    initGame();
                }
                break;
        }
    }

    private void handleKeyRelease(KeyEvent e) {
        if (gameState == GameState.PLAYING) {
            plane.reduceDirection(e);
        }
    }

    private void fireBullet() {
        if (bullets.size() < Constant.MAX_BULLETS) {
            bullets.add(new Bullet(plane.x + plane.width / 2, plane.y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (gameState) {
            case START:
                drawStartScreen(g);
                break;
            case PLAYING:
                // 控制游戏逻辑更新频率
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastUpdateTime >= UPDATE_INTERVAL) {
                    updateGame();
                    lastUpdateTime = currentTime;
                }
                drawGame(g);
                break;
            case PAUSED:
                drawGame(g);
                drawPauseScreen(g);
                break;
            case GAME_OVER:
                drawGame(g);
                drawGameOverScreen(g);
                break;
        }
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.drawString("飞机大战", 200, 200);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g.drawString("按 Enter 开始游戏", 200, 300);
        g.drawString("方向键移动，空格键射击", 200, 350);
        g.drawString("P 键暂停", 200, 380);
    }

    private void drawGame(Graphics g) {
        g.drawImage(bg, 0, 0, null);

        // 绘制炮弹
        for (Shell shell : shells) {
            shell.draw(g);
        }

        // 绘制子弹
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        // 绘制敌机
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // 绘制玩家飞机
        plane.drawSelf(g);

        // 绘制爆炸效果
        if (explode != null) {
            explode.draw(g);
        }

        // 绘制UI
        drawUI(g);
    }

    private void drawUI(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        g.drawString("分数: " + score, 10, 50);
        g.drawString("时间: " + seconds + "秒", 10, 70);
        g.drawString("最高分: " + highScore, 10, 90);
        
        // 显示安全时间
        long gameTime = System.currentTimeMillis() - startDate.getTime();
        if (gameTime < 2000) {
            g.setColor(Color.GREEN);
            g.drawString("安全时间: " + ((2000 - gameTime) / 1000 + 1) + "秒", 10, 110);
        }
    }

    private void drawPauseScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.drawString("游戏暂停", 200, 300);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g.drawString("按 P 继续游戏", 200, 350);
    }

    private void drawGameOverScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.drawString("游戏结束", 200, 250);
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g.drawString("最终分数: " + score, 200, 300);
        g.drawString("生存时间: " + seconds + "秒", 200, 330);
        g.drawString("按 Enter 重新开始", 200, 380);
    }

    private void updateGame() {
        if (gameState != GameState.PLAYING) return;

        // 更新时间
        endDate = new Date();
        seconds = (int) (endDate.getTime() - startDate.getTime()) / 1000;

        // 生成敌机
        enemySpawnTimer++;
        if (enemySpawnTimer >= Constant.ENEMY_SPAWN_INTERVAL) {
            enemySpawnTimer = 0;
            if (enemies.size() < Constant.MAX_ENEMIES) {
                enemies.add(new Enemy(random.nextInt(Constant.GAMEFRAME_WIDTH - 50), -50));
            }
        }

        // 生成炮弹
        shellSpawnTimer++;
        if (shellSpawnTimer >= Constant.SHELL_SPAWN_INTERVAL) {
            shellSpawnTimer = 0;
            if (shells.size() < Constant.MAX_SHELLS) {
                shells.add(new Shell());
            }
        }

        // 更新子弹位置
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            bullet.y -= bullet.speed;
            if (bullet.y < -10) {
                bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);

        // 更新敌机位置
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : enemies) {
            enemy.y += enemy.speed;
            if (enemy.y > Constant.GAMEFRAME_HEIGHT) {
                enemiesToRemove.add(enemy);
            }
        }
        enemies.removeAll(enemiesToRemove);

        // 碰撞检测：子弹 vs 敌机
        List<Bullet> hitBullets = new ArrayList<>();
        List<Enemy> hitEnemies = new ArrayList<>();
        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                if (bullet.getRect().intersects(enemy.getRect())) {
                    hitBullets.add(bullet);
                    hitEnemies.add(enemy);
                    score += 10;
                    // 创建爆炸效果
                    explode = new Explode(enemy.x, enemy.y);
                }
            }
        }
        bullets.removeAll(hitBullets);
        enemies.removeAll(hitEnemies);

        // 碰撞检测：炮弹 vs 玩家（添加安全时间）
        long gameTime = System.currentTimeMillis() - startDate.getTime();
        if (gameTime > 2000) { // 2秒后才开始碰撞检测
            for (Shell shell : shells) {
                if (shell.getRect().intersects(plane.getRect())) {
                    plane.live = false;
                    explode = new Explode(plane.x, plane.y);
                    endDate = new Date();
                    seconds = (int) (endDate.getTime() - startDate.getTime()) / 1000;
                    if (score > highScore) {
                        highScore = score;
                    }
                    gameState = GameState.GAME_OVER;
                    break;
                }
            }
        }

        // 碰撞检测：敌机 vs 玩家（添加安全时间）
        for (Enemy enemy : enemies) {
            if (enemy.getRect().intersects(plane.getRect())) {
                plane.live = false;
                explode = new Explode(plane.x, plane.y);
                endDate = new Date();
                seconds = (int) (endDate.getTime() - startDate.getTime()) / 1000;
                if (score > highScore) {
                    highScore = score;
                }
                gameState = GameState.GAME_OVER;
                break;
            }
        }

        repaint();
    }
}