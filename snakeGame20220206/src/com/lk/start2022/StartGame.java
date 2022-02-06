package com.lk.start2022;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    //程序入口
    public static void main(String[] args) {
        //创建一个窗体
        JFrame jf = new JFrame();
        //设置标题
        jf.setTitle("贪吃蛇小游戏-lk2022");
        //设置窗体
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - 640) / 2,
                600, 640);
        //关闭窗口的同时关闭程序
        jf.setDefaultCloseOperation(3);
        //创建面板
        GamePanel gp = new GamePanel();
        //将面板放入窗体
        jf.add(gp);
        //


        //设置窗体显现,代码一般放最后
        jf.setVisible(true);
    }
}
