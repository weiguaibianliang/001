package com.ModuleConnectionAlgorithm;


import com.algorithm.TSPData;

import java.util.Random;

public class SpeciesModuleIndividual {

    //基因序列
    String[] genes;

    SpeciesModuleIndividual(){
        //初始化
        this.genes = new String[ShirtTestData.NUM_OF_SHIRT_PARTS];
    }

    //初始物种基因（基于启发式初始化，根据衬衫的紧前工序来确定约束关系）
    //启发式方法通常利用了问题的某些结构性质或者先验知识，将种群初始化为一些优秀的解，从而在搜索空间中更容易找到最优解。
    void createByHeuristicGenes() {
        //初始基因为1-CITY_NUM序列
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Integer.toString(i + 1);
        }
        //获取随机种子
        Random random = new Random();
        //事先把工件排序完成，按标准格式来，第一个部件是前片或者后片
        int num = random.nextInt(2) * 2 + 1;
        genes[0] = Integer.toString(num);
        //当第一个部件是前片
        if (genes[0].equals("1")) {
            int[] num1 = {2, 4, 6};
            for (int i : num1) {
                genes[1] = Integer.toString(i);
            }
            if (genes[1].equals("2")) {
                int[] num2 = {3, 4, 6};
                for (int i : num2) {
                    genes[2] = Integer.toString(i);
                }
                if (genes[2].equals("3")) {
                    int[] num3 = {4, 5, 6, 7, 8};
                    for (int i : num3) {
                        genes[3] = Integer.toString(i);
                    }
                }
            }
        }
    }

    public void printBestModuleCombination() {

    }
}
