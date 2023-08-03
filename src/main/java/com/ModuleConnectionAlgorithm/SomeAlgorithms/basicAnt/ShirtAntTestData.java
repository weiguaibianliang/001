package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicAnt;

import com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic.RestrictiveCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 怎样确定改进蚁群算法的参数
 * 1、经验法：根据以往的经验和试验结果，逐步调整参数，以达到最佳效果。
 * 2、网格搜索法：遍历所有可能的参数组合，选取表现最好的一组参数。计算量大
 * 3、蚁群算法自适应参数调整：在蚁群算法中引入自适应策略，通过对蚁群算法中的参数进行动态调整，以适应不同问题的求解要求。
 * 4、统计分析法：通过实验数据进行分析，结合统计学方法，确定最佳参数。
 */

public class ShirtAntTestData {

    //衬衫部件数量
    static final int NUM_OF_SHIRT_PARTS = 7;

    //蚂蚁数目大小
    static final int ANT_POPULATION_SIZE = 10;

    //最大迭代次数（100-1000）
    static final int MAX_GENERATIONS = 200;

    //信息启发因子
    static final double alpha = 1.0;

    //期望值启发因子
    static final double beta = 4.0;

    //信息素局部挥发系数
    static final double localVolFactor = 0.25;

    //初始信息素局部挥发系数
    static final double initialLocalVolFactor = 0.20;

    //信息素全局挥发系数
    static final double totalVolFactor = 0.30;

    //信息素增强系数（用于调节信息素增强量）
    static final double pheromoneEnhanceFactor = 1.0;

    //启发信息矩阵
    private double[][] heuristicMatrix;

    //信息素浓度矩阵
    private double[][] pheromoneMatrix;




    // 衬衫部件的名称
    static final String[] SHIRT_PARTS = {"right_front", "left_front_pocket", "back_shoulder_hem", "placket", "collar",
            "right_sleeve", "left_sleeve"};


    //根据有向无环图获取和基础件有联接关系的部件集合，并放入allowed中
    public Map<Integer, List<Integer>> getAllowedMap() {
        //建立allowed集合
        Map<Integer, List<Integer>> allowedMap = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        allowedMap.put(0, list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        allowedMap.put(1, list2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(4);
        list3.add(5);
        list3.add(6);
        allowedMap.put(2, list3);
        List<Integer> list4 = new ArrayList<>();
        list4.add(2);
        list4.add(4);
        list4.add(5);
        list4.add(6);
        allowedMap.put(3, list4);
        List<Integer> list5 = new ArrayList<>();
        list5.add(3);
        list5.add(5);
        list5.add(6);
        allowedMap.put(4, list5);
        List<Integer> list6 = new ArrayList<>();
        list6.add(3);
        list6.add(4);
        list6.add(6);
        allowedMap.put(5, list6);
        List<Integer> list7 = new ArrayList<>();
        list7.add(3);
        list7.add(4);
        list7.add(5);
        allowedMap.put(6, list7);

        return allowedMap;

    }

    ;


    // 连接关系矩阵
    //0-未连接；1-连接
    static final int[][] CONNECTIONS = {
            {0, 0, 1, 1, 1, 1, 0}, // 右前片
            {0, 0, 1, 1, 1, 0, 1}, // 左前片-胸袋
            {1, 1, 0, 0, 1, 1, 1}, // 后片-过肩-下摆
            {1, 1, 0, 0, 0, 0, 0}, // 门襟
            {1, 1, 1, 0, 0, 0, 0}, // 领
            {1, 0, 1, 0, 0, 0, 0}, // 左袖
            {0, 1, 1, 0, 0, 0, 0}, // 右袖
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


    //得到启发信息矩阵
    public double[][] getHeuristicMatrix() {
        double[][] heuristicMatrix = new double[NUM_OF_SHIRT_PARTS][NUM_OF_SHIRT_PARTS];
        AntModuleIndividual individual = new AntModuleIndividual();
        for (int i = 0; i < NUM_OF_SHIRT_PARTS; i++) {
            for (int j = 0; j < NUM_OF_SHIRT_PARTS; j++) {
                heuristicMatrix[i][j] = 1.0 / (individual.getNUMBER_CHANGE_SEW_EQUIPMENT()*NUMBER_CHANGE[i][j] + individual.getSEW_COMBINATION_ACCURACY()
                        *ACCURACY[i][j] + individual.getSEW_COMBINATION_STABILITY()*STABILITY[i][j] + individual.getSEW_COMBINATION_CONTINUITY()
                        *CONTINUITY[i][j]);
            }
        }
        return heuristicMatrix;
    }

    //得到信息素浓度矩阵
    public double[][] getInitialPheromone(){
        // 初始化信息素矩阵和启发信息矩阵
        double[][] pheromoneMatrix = new double[NUM_OF_SHIRT_PARTS][NUM_OF_SHIRT_PARTS];
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS; i++) {
            for (int j = 0; j < ShirtAntTestData.NUM_OF_SHIRT_PARTS; j++) {
                pheromoneMatrix[i][j] = 1.0; // 初始信息素浓度设为1
            }
        }
        return pheromoneMatrix;
    }


}
