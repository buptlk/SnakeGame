package com.lk.start2022;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Images {
    /*
    此类的目的是将图片封装成对象
     */
    //将图片路径封装成一个url对象
    public static  URL headURL = Images.class.getResource("/image/head.png");
    //将图片封装成程序中的一个对象
    public static ImageIcon headImg = new ImageIcon(headURL);
    //将图片路径封装成一个url对象
    public static  URL snakeHeadURL = Images.class.getResource("/image/snakeHead.png");
    //将图片封装成程序中的一个对象
    public static ImageIcon snakeHeadImg = new ImageIcon(snakeHeadURL);
    //将图片路径封装成一个url对象
    public static  URL snakeBodyURL = Images.class.getResource("/image/snakeBody.png");
    //将图片封装成程序中的一个对象
    public static ImageIcon snakeBodyImg = new ImageIcon(snakeBodyURL);
    //将图片路径封装成一个url对象
    public static  URL foodURL = Images.class.getResource("/image/food.png");
    //将图片封装成程序中的一个对象
    public static ImageIcon foodImg = new ImageIcon(foodURL);
    //将图片路径封装成一个url对象
    public static  URL boomURL = Images.class.getResource("/image/boom.png");
    //将图片封装成程序中的一个对象
    public static ImageIcon boomImg = new ImageIcon(boomURL);
    //填充路径后复制粘贴即可

}
