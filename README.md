# 飞机大战游戏

一个使用Java Swing开发的经典飞机大战游戏。

## 游戏特性

- 玩家飞机控制
- 敌机生成系统
- 子弹射击系统
- 炮弹反弹机制
- 碰撞检测
- 爆炸效果
- 计分系统
- 游戏状态管理（开始、暂停、结束）

## 操作说明

- **方向键**：控制飞机移动
- **空格键**：发射子弹
- **P键**：暂停/继续游戏
- **Enter键**：开始游戏/重新开始

## 游戏规则

1. 避开炮弹和敌机
2. 射击敌机获得分数
3. 存活时间越长，分数越高
4. 碰到炮弹或敌机游戏结束

## 项目结构

```
src/cn/zzditto/game/
├── Constant.java          # 游戏常量配置
├── GameObject.java        # 游戏对象基类
├── Plane.java             # 玩家飞机类
├── Bullet.java            # 子弹类
├── Shell.java             # 炮弹类
├── Enemy.java             # 敌机类
├── Explode.java           # 爆炸效果类
├── GamePanel.java         # 游戏主面板
├── GameFrame.java         # 游戏窗口
├── GameLoop.java          # 游戏循环
├── GameUtil.java          # 工具类
├── ResourceManager.java   # 资源管理器
└── GameConfig.java        # 游戏配置管理
```

## 运行游戏

1. 确保已安装Java JDK 8+
2. 编译项目：`javac src/cn/zzditto/game/*.java`
3. 运行游戏：`java -cp src cn.zzditto.game.GameFrame`

## 开发说明

- 使用Swing进行图形界面开发
- 采用游戏循环机制控制帧率
- 使用碰撞检测算法实现游戏逻辑
- 支持游戏状态管理

## 扩展建议

1. 添加音效系统
2. 实现道具系统
3. 增加关卡难度
4. 添加Boss战
5. 实现存档功能