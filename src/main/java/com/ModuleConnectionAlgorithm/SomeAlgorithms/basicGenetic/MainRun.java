package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//传统遗传算法求解
public class MainRun {
    public static void main(String[] args) throws Exception {

        //创建模块连接算法驱动对象
        BasicGeneticAlgorithm BGA = new BasicGeneticAlgorithm();

        //开始遗传算法（选择算子、交叉算子、变异算子）
        long dateTime = System.currentTimeMillis();
        BGA.run();
        long dateTime1 = System.currentTimeMillis();
        System.out.println("遗传算法所消耗的时间"+(dateTime1-dateTime));
//
//        //打印最佳连接组合和指标最优的参数
//        bestModuleCombination.printBestModuleCombination();

        System.out.println("采用穷举法打印所有序列的适应度值，然后排序选择最优的方案");
        //满足条件的个体
        //满足条件个体的适应度比例
        long dateTime2 = System.currentTimeMillis();
        ShirtTestData shirtTestData = new ShirtTestData();
        RestrictiveCondition test = shirtTestData.getTest();
        List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
        List<SpeciesModuleIndividual> list = new ArrayList<>();
        for (List<Integer> toPoSort : allToPoSorts) {
            SpeciesModuleIndividual speciesModuleIndividual = new SpeciesModuleIndividual();
            int[] array = new int[toPoSort.size()];
            for (int j = 0; j < toPoSort.size(); j++) {
                array[j] = toPoSort.get(j);
            }
            speciesModuleIndividual.setGenes(array);
            speciesModuleIndividual.calFitness();
            System.out.println("  Individual: " + speciesModuleIndividual);
            list.add(speciesModuleIndividual);
        }
        SpeciesModuleIndividual individual = list.get(0);
        for (SpeciesModuleIndividual speciesModuleIndividual : list) {
            if (speciesModuleIndividual.getFitness().compareTo(individual.getFitness()) < 0) {
                individual = speciesModuleIndividual;
            }
        }
        System.out.println(individual.getFitness());
        long dateTime3 = System.currentTimeMillis();
        System.out.println("穷举算法所消耗的时间"+ (dateTime3 - dateTime2));

    }
}
