package com.OfferAlgorithm.FirstWeek.listNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LotteryGUI extends JFrame implements ActionListener {
    private JLabel numLabel, rangeLabel, resultLabel;
    private JTextField numField, rangeField;
    private JButton shakeButton;
    private JTextArea resultArea;

    public LotteryGUI() {
        // 设置窗口标题和大小
        setTitle("摇号小程序");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 设置布局
        setLayout(new BorderLayout());

        // 添加控件
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        numLabel = new JLabel("摇号人数：");
        inputPanel.add(numLabel);
        numField = new JTextField();
        inputPanel.add(numField);
        rangeLabel = new JLabel("摇号范围：");
        inputPanel.add(rangeLabel);
        rangeField = new JTextField();
        inputPanel.add(rangeField);
        add(inputPanel, BorderLayout.NORTH);

        shakeButton = new JButton("摇号");
        shakeButton.addActionListener(this);
        add(shakeButton, BorderLayout.CENTER);

        resultLabel = new JLabel("摇号结果：");
        add(resultLabel, BorderLayout.WEST);
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(resultArea, BorderLayout.SOUTH);

        // 显示窗口
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // 获取用户输入的摇号人数和范围
        int num = Integer.parseInt(numField.getText());
        int range = Integer.parseInt(rangeField.getText());

        // 生成摇号结果
        Random rand = new Random();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            int r = rand.nextInt(range) + 1;
            result.add(r);
        }

        // 输出摇号结果
        resultArea.setText("");
        for (int i = 0; i < result.size(); i++) {
            resultArea.append(result.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        new LotteryGUI();
    }
}
