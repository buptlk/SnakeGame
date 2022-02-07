package com.lk.start2022;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel {
    /*
    继承Jpanel成为一个面板
     */
    //定义两个数组，分别存储贪吃蛇的坐标
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    //定义蛇的长度
    int length;
    //定义蛇头方向
    int direction;
    int[] dirX;
    int[] dirY;
    //定义食物坐标
    int foodX;
    int foodY;
    //游戏状态（暂停/开始）
    boolean isStart;
    //定时器
    Timer timer;
    //gameover
    boolean isGameOver;
    //得分
    int score;
    //定义init方法，用于初始化
    public void init(){
        //初始化蛇的长度
        length = 3;
        //蛇头坐标
        snakeX[0] = 200;
        snakeY[0] = 300;
        //第一节身体坐标(每一节20x20)
        snakeX[1] = 180;
        snakeY[1] = 300;
        //第二节身体坐标
        snakeX[2] = 160;
        snakeY[2] = 300;
        //蛇头方向
        direction = 2; //代表dirx diry都取索引2的位置，此处为向右
        dirX = new int[]{-1, 0, 1, 0};
        dirY = new int[]{0, -1, 0, 1};
        //食物
        foodX = 400;
        foodY = 100;
        //游戏状态
        isStart = false;
        //gameover
        isGameOver = false;
        //得分
        score = 0;
    }
    public GamePanel(){
        //初始化蛇
        init();
        //将焦点定位到面板上
        this.setFocusable(true);
        //加入监听
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int n = e.getKeyCode();
                System.out.println(n);
                //点击空格
                if(n == KeyEvent.VK_SPACE){
                    isStart = !isStart;
                    repaint();//重绘，会调用paintComponent
                }
                if(n - 37 >=0 && n - 37 <= 3){
                    //换方向
                    direction = n - 37;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        //初始化定时器
        timer = new Timer(100, new ActionListener() {
            /*
            ActionListener事件监听
            每100ms监听
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart && !isGameOver){    //游戏开始状态
                    //动身体
                    for(int i = length - 1; i > 0; i --){
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    //动头
                    snakeX[0] = (snakeX[0] + 20 * dirX[direction] + 600) % 600;
                    snakeY[0] = (snakeY[0] + 20 * dirY[direction]);
                    if(snakeY[0] < 40) snakeY[0] = 580;
                    else if(snakeY[0] >= 600) snakeY[0] = 40;

                    //吃食物
                    if(foodX == snakeX[0] && foodY == snakeY[0]){
                        length ++;
                        foodX = new Random().nextInt(30) * 20;
                        foodY = (new Random().nextInt(28) + 2) * 20;
                        score += 10;
                    }
                    //自口
                    for (int i = 1; i < length; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            //gameOver!
                            isGameOver = true;
                            break;
                        }
                    }
                    repaint();
                }
            }
        });
        //启动定时器
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //填充背景颜色，rgb
        this.setBackground(new Color(165, 213, 168));
        //画头部图片
        Images.headImg.paintIcon(this,g, 0, 0);
        //调节画笔颜色
        g.setColor(new Color(92, 168, 171));
        //画一个矩形
        g.fillRect(0,40,600,560);
        //画蛇
        //画head
        Images.snakeHeadImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        //画身体
        for(int i = 1; i < length; i ++){
            if(i == length - 1) Images.snakeBodyImg.paintIcon(this,g, snakeX[i - 1], snakeY[i - 1]);
            else Images.snakeBodyImg.paintIcon(this,g, snakeX[i], snakeY[i]);
        }
        //画食物
        Images.foodImg.paintIcon(this, g, foodX, foodY);
        //写得分,不论游戏是否暂停，都要有积分
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("微软雅黑",Font.BOLD, 20));
        //内容，坐标
        g.drawString("SCORE: " + score, 460, 20);
        //游戏暂停时，提示语
        if(!isStart){
            //画一个文字
            g.setColor(new Color(197, 65, 65));
            //文字参数，加粗字体字号
            g.setFont(new Font("微软雅黑",Font.BOLD, 32));
            //内容，坐标
            g.drawString("PRESS SPACE KEY TO CONTINUE", 40, 250);
        }
        if(isGameOver){
            //画一个文字
            g.setColor(new Color(197, 65, 65));
            //文字参数，加粗字体字号
            g.setFont(new Font("微软雅黑",Font.BOLD, 24));
            //画文字
            g.drawString("GAME OVER!", 200, 250);
            g.drawString("PRESS SPACE KEY TO RESTART", 100, 280);
            //画爆炸
            Images.boomImg.paintIcon(this, g, snakeX[0] - 5, snakeY[0]- 5);
            init();
        }

    }
}