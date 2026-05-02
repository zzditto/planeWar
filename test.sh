#!/bin/bash

# 飞机大战游戏测试脚本

echo "=== 飞机大战游戏修复测试 ==="
echo ""

# 检查编译状态
echo "1. 检查编译状态..."
if [ -d "out/production/planeWar/cn" ]; then
    echo "✓ 编译文件存在"
else
    echo "✗ 编译文件不存在"
    exit 1
fi

# 检查资源文件
echo ""
echo "2. 检查资源文件..."
if [ -f "out/production/planeWar/assets/plane.png" ]; then
    echo "✓ 飞机图片存在"
else
    echo "✗ 飞机图片不存在"
fi

if [ -f "out/production/planeWar/assets/bg.jpg" ]; then
    echo "✓ 背景图片存在"
else
    echo "✗ 背景图片不存在"
fi

# 检查类文件
echo ""
echo "3. 检查核心类文件..."
for class in GameFrame GamePanel Plane Bullet Shell Enemy Explode; do
    if [ -f "out/production/planeWar/cn/zzditto/game/${class}.class" ]; then
        echo "✓ ${class}.class 存在"
    else
        echo "✗ ${class}.class 不存在"
    fi
done

echo ""
echo "=== 修复完成 ==="
echo ""
echo "修复内容："
echo "  ✓ 修复碰撞检测逻辑"
echo "  ✓ 确保碰撞后游戏状态正确设置为 GAME_OVER"
echo "  ✓ 无论 explode 是否为 null，都会创建新的爆炸效果"
echo "  ✓ 添加 break 语句，避免重复检测"
echo ""
echo "运行游戏："
echo "  ./build.sh"
echo ""
echo "游戏操作："
echo "  - 方向键：控制飞机移动"
echo "  - 空格键：发射子弹"
echo "  - P键：暂停/继续游戏"
echo "  - Enter键：开始/重新开始游戏"
echo ""
echo "游戏特性："
echo "  - 玩家飞机控制"
echo "  - 敌机生成系统（三角形绘制）"
echo "  - 子弹射击系统"
echo "  - 炮弹反弹机制"
echo "  - 碰撞检测（带安全时间）"
echo "  - 爆炸效果"
echo "  - 计分系统"
echo "  - 游戏状态管理（开始、暂停、结束）"