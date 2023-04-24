package com.ModuleConnectionAlgorithm;

/**
 * 怎样确定遗传算法的参数
 * 1、经验法：根据以往的经验和试验结果，逐步调整参数，以达到最佳效果。
 * 2、网格搜索法：遍历所有可能的参数组合，选取表现最好的一组参数。计算量大
 * 3、遗传算法自适应参数调整：在遗传算法中引入自适应策略，通过对遗传算法中的参数进行动态调整，以适应不同问题的求解要求。
 * 4、统计分析法：通过实验数据进行分析，结合统计学方法，确定最佳参数。
 */

public class ShirtTestData {

    //衬衫部件数量
    static final int NUM_OF_SHIRT_PARTS = 10;

    //初始种群大小
    static final int POPULATION_SIZE = 100;

    //最大迭代次数
    static final int MAX_GENERATIONS = 2000;

    //交叉概率
    static final double CROSSOVER_RATE_L = 0.6,CROSSOVER_RATE_H = 0.9;

    //变异概率
    static final double MUTATION_RATE_L =0.001,MUTATION_RATE_H =0.1;

    // 衬衫部件的名称
    static final String[] SHIRT_PARTS = {"front", "shoulder", "back", "placket", "collar",
            "pocket", "sleeve", "cuff slit strip", "cuff", "hem"};

    // 连接关系矩阵
    //0-未连接；1-连接
    private static final int[][] CONNECTIONS = {
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 1}, // 前片
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 0}, // 过肩
            {1, 1, 0, 0, 1, 0, 1, 0, 0, 1}, // 后片
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 门襟
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, // 领
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 胸袋
            {1, 1, 1, 0, 0, 0, 0, 1, 1, 0}, // 袖身
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0}, // 袖衩条
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0}, // 袖克夫
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0}  // 下摆
    };

    /**
     * 基因编码 ——染色体，分为两段
     * 1、分为两部分：第一部分采用部件个数的序号编码，第二部分采用部件是否显示的二进制编码
     * 2、第一部分：0-1-2-3-4-5-6-7-8-9
     * 3、第二部分;1-1-1-1-1-1-1-1-1-1 其中0-代表部件不显示，1-代表部件显示。
     *
     */
    //基因编码是动态变化的  还与缝制方式有关





}
