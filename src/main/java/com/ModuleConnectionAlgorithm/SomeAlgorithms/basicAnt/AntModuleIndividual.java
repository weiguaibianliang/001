package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicAnt;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Data
public class AntModuleIndividual {

    //蚂蚁走完完整的缝制组合序列
    private int[] genes;

    //蚂蚁走完完整组合的部件数目
    private List<Integer> list;

    //个体适应度  适应度函数值越小越好
    private BigDecimal fitness;


    //个体之间的评价指标的权重
    //缝制设备变换次数
    private  final double NUMBER_CHANGE_SEW_EQUIPMENT = 0.2657;

    //缝制组合连续性
    private  final double SEW_COMBINATION_CONTINUITY = 0.1657;

    //缝制组合稳定性
    private final double SEW_COMBINATION_STABILITY = 0.2657;

    //缝制组合精度
    private  final double SEW_COMBINATION_ACCURACY = 0.3029;







    AntModuleIndividual() {
        //初始化
        this.genes = new int[ShirtAntTestData.NUM_OF_SHIRT_PARTS];
        this.fitness = BigDecimal.valueOf(0.0f);
        this.list = new ArrayList<>();
    }

    //传统遗传算法在初始种群时没有考虑约束条件，随机生成后再进行约束条件的选择。
    public void createByRandomGenes() {
        //初始基因为0-NUM_OF_SHIRT_PARTS序列
        for (int i = 0; i < genes.length; i++) {
            genes[i] = i;
        }
        //获取随机种子
        Random rand = new Random();
        for (int j = 0; j < genes.length; j++) {
            //rand.nextInt（int n）用于获取一个介于0（包括）和此参数（n）中传递的数字之间的随机数。
            int num = j + rand.nextInt(genes.length - j);
            //交换
            int tmp;
            tmp = genes[num];
            genes[num] = genes[j];
            genes[j] = tmp;
        }
    }






    void calFitness() {
        // 在这里根据自定义的评估标准计算适应度
        this.fitness = BigDecimal.valueOf(NUMBER_CHANGE_SEW_EQUIPMENT).
                multiply(calculateNumberChangeSewEquipment()).add(BigDecimal.valueOf(SEW_COMBINATION_CONTINUITY).
                        multiply(calculateSewCombinationContinuity())).add(BigDecimal.valueOf(SEW_COMBINATION_STABILITY)
                        .multiply(calculateSewCombinationStability())).add(BigDecimal.valueOf(SEW_COMBINATION_ACCURACY).
                        multiply(calculateSewCombinationAccuracy()));
    }


    private BigDecimal calculateNumberChangeSewEquipment() {
        //总的缝制设备变换次数
        int totalNumberChange = 0;
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS - 1; i++) {
            //当前个体所选部件
            int curPart = this.genes[i];
            //当前个体所选部件的下一个部件
            int nextPart = this.genes[i+1];
            totalNumberChange += ShirtAntTestData.NUMBER_CHANGE[curPart][nextPart];

        }
        return BigDecimal.valueOf(totalNumberChange);
    }

    private BigDecimal calculateSewCombinationContinuity() {
        //缝制组合连续性，有没有两个部件可以一起缝制，不用移动工位和设备
        int totalContinuity = 0;
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS - 1; i++) {
            //当前个体所选部件
            int curPart = this.genes[i];
            //当前个体所选部件的下一个部件
            int nextPart = this.genes[i+1];
            totalContinuity += ShirtAntTestData.CONTINUITY[curPart][nextPart];
        }
        return BigDecimal.valueOf(totalContinuity);
    }

    private BigDecimal calculateSewCombinationStability() {
        //缝制组合稳定性，有没有额外的辅助工具
        int totalStability = 0;
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS -1 ; i++) {
            //当前个体所选部件
            int curPart = this.genes[i];
            //当前个体所选部件的下一个部件
            int nextPart = this.genes[i+1];
            totalStability += ShirtAntTestData.STABILITY[curPart][nextPart];
        }
        return BigDecimal.valueOf(totalStability);
    }

    private BigDecimal calculateSewCombinationAccuracy() {
        //缝制组合精度，在缝制完成后与标准尺寸对比的变化量
        int totalAccuracy = 0;
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS -1 ; i++) {
            //当前个体所选部件
            int curPart = this.genes[i];
            //当前个体所选部件的下一个部件
            int nextPart = this.genes[i+1];
            totalAccuracy += ShirtAntTestData.ACCURACY[curPart][nextPart];
        }
        return BigDecimal.valueOf(totalAccuracy);
    }



}
