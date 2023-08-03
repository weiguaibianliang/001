package com.ModuleConnectionAlgorithm.TopologicalOrderAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DAGPanel extends JPanel {
    private static final int PADDING = 50; // 图形的外边距
    private static final int NODE_SIZE = 40; // 顶点的大小

    private static final int ARROW_SIZE = 10; // 箭头的大小

    private static final long serialVersionUID = 7646277676606421125L;

    private final int[][] edges; // 边的列表

    //顶点的数值
    private final String[] nodes;

    public DAGPanel(int[][] edges, String[] nodes) {
        this.edges = edges;
        this.nodes = nodes;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int fontSize = 20;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g2d.setFont(f);

        // 绘制顶点
        int[] degree = new int[edges.length + 1];
        Arrays.fill(degree, 0);
        for (int[] edge : edges) {
            degree[edge[1]]++; // 计算每个顶点的入度
        }
        List<Integer> sources = new ArrayList<>();
        for (int i = 0; i < edges.length + 1; i++) {
            int x = PADDING + i * (NODE_SIZE + PADDING -40);
            int y = PADDING + degree[i] * (NODE_SIZE + PADDING + 40);
            if (degree[i] == 0) {
                sources.add(i); // 记录所有源顶点
            }
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(x, y, NODE_SIZE, NODE_SIZE);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x, y, NODE_SIZE, NODE_SIZE);
            g2d.drawString(nodes[i], x + NODE_SIZE / 2, y + NODE_SIZE / 2);
        }

        // 绘制边
        for (int[] edge : edges) {
            int x1 = PADDING + edge[0] * (NODE_SIZE + PADDING - 40 ) + NODE_SIZE / 2;
            int y1 = PADDING + degree[edge[0]] * (NODE_SIZE + PADDING + 40) + NODE_SIZE / 2;
            int x2 = PADDING + edge[1] * (NODE_SIZE + PADDING - 40) + NODE_SIZE / 2;
            int y2 = PADDING + degree[edge[1]] * (NODE_SIZE + PADDING + 40) + NODE_SIZE / 2;
            g2d.drawLine(x1, y1, x2, y2);
            drawArrow(g2d, x1, y1, x2, y2);
        }

        // 标记源顶点
        g2d.setColor(Color.BLUE);
        for (int source : sources) {
            int x = PADDING + source * (NODE_SIZE + PADDING) + NODE_SIZE / 2;
            int y = PADDING + degree[source] * (NODE_SIZE + PADDING) + NODE_SIZE + PADDING;
            g2d.drawString("S", x, y);
        }
    }

    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + Math.toRadians(45);
        Path2D arrow = new Path2D.Double();
        arrow.moveTo(x2, y2);
        for (int j = 0; j < 2; j++) {
            x = x2 - DAGPanel.ARROW_SIZE * Math.cos(rho);
            y = y2 - DAGPanel.ARROW_SIZE * Math.sin(rho);
            arrow.lineTo(x, y);
            rho = theta - Math.toRadians(45);
        }
        arrow.closePath();
        g2d.draw(arrow);
    }

    public static void main(String[] args) {
//        //输入节点的标签
//        Scanner scanner = new Scanner(System.in);
//        //输入数组的长度
//        System.out.print("输入数组的长度为：");
//        int n = scanner.nextInt();
//        //定义一个数组
//        String[] nodes = new String[n];
//        //输入数组的元素
//        System.out.print("输入数组的元素：");
//        for (int i = 0; i < n; i++){
//            nodes[i] = scanner.next();
//        }
//        System.out.print("输入边的列表:");
//        //定义边的列表的二维数组
//        int m = scanner.nextInt();
//        int[][] edges = new int[m][2];
//        for (int i = 0; i < n;i++){
//            //紧跟顶点的个数
//            System.out.print("输入紧跟顶点的个数：");
//            int a = scanner.nextInt();
//            if(a!= 0){
//                //输入紧跟顶点的数值
//                System.out.print("输入顶点的数值：");
//                int[] intNode = new int[a];
//                for (int j = 0; j < a; j++){
//                    intNode[j] = scanner.nextInt();
//                }
//                //输入数组的起始点
//                System.out.print("输入数组的起始点：");
//                int k = scanner.nextInt();
//                for (m = k; m < k+intNode.length; m++){
//                    edges[m] = new int[]{i, intNode[m-k]};
//                }
//
//            }
//
//        }
        int[][] edges = {
                {0, 1},
                {0, 2},
                {0, 3},
                {0, 5},
//                {1, 2},
                {2, 4},
                {2, 6},
//               {2, 7}
        };
        String[] nodes = new String[]{"A","B","C","D","E","F","G"};
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new DAGPanel(edges ,nodes));
        frame.setVisible(true);
    }
}