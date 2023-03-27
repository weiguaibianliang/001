package com.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ShirtProcess extends JPanel {

    private static final long serialVersionUID = 1L;

    private List<String> steps = new ArrayList<>();

    public ShirtProcess() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // 绘制前面板
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 绘制领子
        g2d.setColor(Color.WHITE);
        g2d.fillRect(getWidth() / 2 - 20, getHeight() / 2 - 20, 50, 50);

        // 绘制袖子
        g2d.setColor(Color.WHITE);
        g2d.fillRect(getWidth() / 2 - 20, getHeight() / 2 - 20, 100, 100);

//        // 绘制门襟
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(getWidth() / 2 - 20, getHeight() / 2 - 20, 2
    }
}