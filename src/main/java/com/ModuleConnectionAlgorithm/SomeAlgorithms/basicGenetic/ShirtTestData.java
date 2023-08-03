package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic;

import java.math.BigDecimal;

/**
 * 怎样确定遗传算法的参数
 * 1、经验法：根据以往的经验和试验结果，逐步调整参数，以达到最佳效果。
 * 2、网格搜索法：遍历所有可能的参数组合，选取表现最好的一组参数。计算量大
 * 3、遗传算法自适应参数调整：在遗传算法中引入自适应策略，通过对遗传算法中的参数进行动态调整，以适应不同问题的求解要求。
 * 4、统计分析法：通过实验数据进行分析，结合统计学方法，确定最佳参数。
 */

public class ShirtTestData {

    //衬衫部件数量
    static final int NUM_OF_SHIRT_PARTS = 7;

    //初始种群大小
    static final int POPULATION_SIZE = 50;

    //最大迭代次数（100-1000）
    static final int MAX_GENERATIONS = 200;

    //交叉概率
    static final BigDecimal CROSSOVER_RATE_L = BigDecimal.valueOf(0.6),CROSSOVER_RATE_H = BigDecimal.valueOf(0.9);

    //变异概率
    static final BigDecimal MUTATION_RATE_L = BigDecimal.valueOf(0.001),MUTATION_RATE_H = BigDecimal.valueOf(0.1);

    // 衬衫部件的名称
    static final String[] SHIRT_PARTS = {"right_front", "left_front_pocket", "back_shoulder_hem", "placket", "collar",
             "right_sleeve", "left_sleeve"};

    RestrictiveCondition getTest(){
        RestrictiveCondition g = new RestrictiveCondition(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(1, 6);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        return g;
    };



    // 连接关系矩阵
    //0-未连接；1-连接
    static final int[][] CONNECTIONS = {
            {0, 0, 1, 1, 1, 1,0}, // 右前片
            {0, 0, 1, 1, 1, 0,1}, // 左前片-胸袋
            {1, 1, 0, 0, 1, 1,1}, // 后片-过肩-下摆
            {1, 1, 0, 0, 0, 0,0}, // 门襟
            {1, 1, 1, 0, 0, 0,0}, // 领
            {1, 0, 1, 0, 0, 0,0}, // 左袖
            {0, 1, 1, 0, 0, 0,0}, // 右袖
    };

    //缝制设备变换次数数据源
     static final int[][] NUMBER_CHANGE = {
            {0, 0, 0, 1, 1, 0, 0}, // 右前片
            {0, 0, 1, 1, 1, 0, 1}, // 左前片-胸袋
            {1, 1, 0, 0, 1, 1, 1}, // 后片-过肩-下摆
            {1, 1, 0, 0, 0, 0, 0}, // 门襟
            {1, 1, 1, 0, 0, 0, 0}, // 领
            {1, 0, 1, 0, 0, 0, 0}, // 左袖
            {0, 1, 1, 0, 0, 0, 0}, // 右袖

    };

    //缝制组合连续性数据源
     static final int[][] CONTINUITY = {
            {1, 0, 1, 1, 1, 1, 1}, // 右前片
            {0, 1, 1, 1, 1, 1, 1}, // 左前片-胸袋
            {1, 1, 1, 1, 1, 1, 1}, // 后片-过肩-下摆
            {1, 1, 1, 1, 1, 1, 1}, // 门襟
            {1, 1, 1, 1, 1, 1, 1}, // 领
            {1, 1, 1, 1, 1, 1, 0}, // 左袖
            {1, 1, 1, 1, 1, 0, 1}, // 右袖

    };

    //缝制组合稳定性数据源
     static final int[][] STABILITY = {
            {0, 0, 0, 0, 0, 0, 0}, // 右前片
            {0, 0, 0, 1, 0, 0, 0}, // 左前片-胸袋
            {0, 0, 0, 0, 0, 1, 1}, // 后片-过肩-下摆
            {0, 0, 0, 0, 1, 1, 1}, // 门襟
            {0, 0, 0, 0, 0, 0, 0}, // 领
            {0, 0, 0, 0, 1, 0, 0}, // 左袖
            {0, 0, 0, 0, 1, 0, 0}, // 右袖

    };

    //缝制组合精度数据源
     static final int[][] ACCURACY = {
            {0, 2, 3, 4, 3, 1, 1}, // 右前片
            {2, 0, 1, 2, 1, 1, 2}, // 左前片-胸袋
            {3, 1, 0, 1, 3, 2, 1}, // 后片-过肩-下摆
            {4, 2, 1, 0, 2, 3, 2}, // 门襟
            {2, 1, 3, 2, 0, 1, 3}, // 领
            {1, 1, 2, 3, 1, 0, 2}, // 左袖
            {1, 2, 1, 2, 3, 2, 0}, // 右袖

    };






}
