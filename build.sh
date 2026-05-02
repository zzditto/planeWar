#!/bin/bash

# 飞机大战游戏构建脚本

echo "正在编译飞机大战游戏..."

# 创建输出目录
mkdir -p out/production/planeWar

# 清理旧的编译文件
rm -rf out/production/planeWar/cn
rm -rf out/production/planeWar/imagetest

# 编译所有Java文件
javac -d out/production/planeWar src/cn/zzditto/game/*.java

if [ $? -eq 0 ]; then
    echo "编译成功！"
    
    # 复制资源文件到输出目录
    echo "正在复制资源文件..."
    cp -r src/imagetest out/production/planeWar/
    
    # 检查资源文件是否复制成功
    if [ -d "out/production/planeWar/imagetest" ]; then
        echo "资源文件复制成功！"
    else
        echo "警告：资源文件复制失败！"
    fi
    
    echo "正在启动游戏..."
    echo "操作说明："
    echo "  - 方向键：控制飞机移动"
    echo "  - 空格键：发射子弹"
    echo "  - P键：暂停/继续游戏"
    echo "  - Enter键：开始/重新开始游戏"
    echo ""
    
    java -cp out/production/planeWar cn.zzditto.game.GameFrame
else
    echo "编译失败！"
    exit 1
fi