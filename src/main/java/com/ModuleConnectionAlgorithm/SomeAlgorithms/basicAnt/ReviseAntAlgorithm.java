package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicAnt;


import com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic.RestrictiveCondition;
import com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic.ShirtTestData;
import com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic.SpeciesModuleIndividual;
import com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic.SpeciesModulePopulation;
import com.Util.GenerateChartUtil;
import com.Util.JFreeChartUtil;
import org.apache.commons.collections.CollectionUtils;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 改进蚁群算法
 */
public class ReviseAntAlgorithm {


    public void run() throws Exception {

        int generation = 1;
        //是否达到最大迭代数
        while (generation <= ShirtAntTestData.MAX_GENERATIONS) {

            //每只蚂蚁都是一个单独的个体
            List<AntModuleIndividual> individualList = new ArrayList<>();

            //得到初始信息素浓度矩阵
            ShirtAntTestData shirtAntTestData = new ShirtAntTestData();
            double[][] pheromoneMatrix = shirtAntTestData.getInitialPheromone();
            //将缝制组合中的第一个部件设置为基础件，将蚂蚁全部放在基础件上。
            for (int i = 0; i < ShirtAntTestData.ANT_POPULATION_SIZE; i++) {

                //假设右前片为基础件
                AntModuleIndividual individual = new AntModuleIndividual();
                individual.getGenes()[0] = 0;
                individual.getList().add(0);

                //获取和基础件有联接关系的部件集合，并放入allowed中
                Map<Integer, List<Integer>> allowedMap = shirtAntTestData.getAllowedMap();

                //根据状态概率公式，计算每个部件的转移概率
                double[][] stateProbability = calculateStateProbability(allowedMap,individual,pheromoneMatrix);

                //缝制组合是否完成，选择的部件数目为服装完成的部件数
                for (int j = 1; j < ShirtAntTestData.NUM_OF_SHIRT_PARTS; j++) {
                    //根据轮盘赌选择方法选择下一个部件
                    //根据状态转移概率的累计比例进行选择
                    individual.getGenes()[j] = selectNextPart(individual.getList(), allowedMap, stateProbability);
                    individual.getList().add(individual.getGenes()[j]);

                    //进行相邻部件之间信息素局部更新
                    updateSectorPheromone(individual,pheromoneMatrix);
                }

                individualList.add(individual);

            }

            individualList.forEach(AntModuleIndividual::calFitness);
            double averageFitness = individualList.stream().map(AntModuleIndividual::getFitness)
                    .reduce(BigDecimal.ZERO,BigDecimal::add).doubleValue()/ShirtAntTestData.ANT_POPULATION_SIZE;

            System.out.println(averageFitness);


            //根据目标函数，计算当前最佳缝制组合
            AntModuleIndividual individual = calculateBestIndividual(individualList);

            //进行信息素全局更新，allowed清零
            updateTotalPheromone(individualList,pheromoneMatrix);

//            //输出当前迭代时的最佳缝制组合
//            System.out.println(individual.getFitness());

            generation++;
        }
    }


    private void updateTotalPheromone(List<AntModuleIndividual> individualList,double[][] pheromoneMatrix) {
        //每一只蚂蚁都有信息素浓度矩阵
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS; i++) {
            for (int j = 0; j < ShirtAntTestData.NUM_OF_SHIRT_PARTS; j++) {
                //信息素蒸发
                pheromoneMatrix[i][j] *= (1.0 - ShirtAntTestData.totalVolFactor);

                for (AntModuleIndividual individual : individualList) {
                    if (individual.getList().contains(i) && individual.getList().contains(j)) {
                        //信息素增加
                        pheromoneMatrix[i][j] += (ShirtAntTestData.pheromoneEnhanceFactor/individual.getFitness().doubleValue());
                    }
                }
            }
        }
        for (AntModuleIndividual individual : individualList) {
            for (int i = 0; i < individual.getGenes().length - 1; i++) {
                //当前个体所选部件
                int curPart = individual.getGenes()[i];
                //当前个体所选部件的下一个部件
                int nextPart = individual.getGenes()[i+1];
                //信息素蒸发
                pheromoneMatrix[curPart][nextPart] *= (1.0 - ShirtAntTestData.totalVolFactor);
                //信息素增加
                individual.calFitness();
                pheromoneMatrix[curPart][nextPart] += (ShirtAntTestData.pheromoneEnhanceFactor/individual.getFitness().doubleValue());

            }

        }


    }

    private AntModuleIndividual calculateBestIndividual(List<AntModuleIndividual> individualList) {
        AntModuleIndividual individual = individualList.get(0);
        individual.calFitness();
        for (AntModuleIndividual antModuleIndividual : individualList) {
            antModuleIndividual.calFitness();
            if (antModuleIndividual.getFitness().compareTo(individual.getFitness()) < 0) {
                individual = antModuleIndividual;
            }
        }
        return individual;
    }

    private void updateSectorPheromone(AntModuleIndividual individual,double[][] pheromoneMatrix) {
        //对局部信息素浓度进行更新
        List<Integer> list = individual.getList();
        pheromoneMatrix[list.get(list.size() - 2)][list.get(list.size() - 1)] = (1.0 - ShirtAntTestData.localVolFactor)
                * pheromoneMatrix[list.get(list.size() - 2)][list.get(list.size() - 1)] + ShirtAntTestData.initialLocalVolFactor;
    }


    private int selectNextPart(List<Integer> list, Map<Integer, List<Integer>> allowedMap, double[][] stateProbability) {
        //得到当前部件紧接下一个部件的可行集合
        List<Integer> partList = allowedMap.get(list.get(list.size() - 1));
        Set<Integer> nextPartList = new HashSet<>();
        for (Integer value : partList) {
            if(!list.contains(value)){
                nextPartList.add(value);
            }
        }
        if(CollectionUtils.isEmpty(nextPartList)){
            for (int i = 0; i < allowedMap.size(); i++) {
                if(!list.contains(i)){
                    return i;
                }
            }
        }
        //得到可行集合中部件的状态转移概率和
        double sectorTotalStateProbability = 0.0;
        for (Integer integer : nextPartList) {
            sectorTotalStateProbability += stateProbability[list.get(list.size() - 1)][integer];
        }
        //得到累计状态转移概率比例集合map
        Map<Integer, Double> sumStateProbability = new HashMap<>();
        double probability = 0.0;
        //得到可行集合中每一个部件的累计状态转移概率比例
        for (Integer integer : nextPartList) {
            probability += stateProbability[list.get(list.size() - 1)][integer] / sectorTotalStateProbability;
            sumStateProbability.put(integer, probability);
        }
        //得到随机选择下一个部件的概率
        double randomValue = new Random().nextDouble();
        //根据随机概率来选择下一个部件
        for (Integer part : sumStateProbability.keySet()) {
            if (randomValue <= sumStateProbability.get(part)) {
                return part;
            }
        }
        return -1;
    }


    private double[][] calculateStateProbability(Map<Integer, List<Integer>> allowedMap,AntModuleIndividual individual,
    double[][] pheromoneMatrix) {
        //所有可能情况的状态转移概率矩阵
        double[][] stateProbability = new double[ShirtAntTestData.NUM_OF_SHIRT_PARTS][ShirtAntTestData.NUM_OF_SHIRT_PARTS];
        ShirtAntTestData shirtAntTestData = new ShirtAntTestData();
        //定义总的状态转移概率
        double[] totalSateProbability = new double[ShirtAntTestData.NUM_OF_SHIRT_PARTS];
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS; i++) {
            List<Integer> list = allowedMap.get(i);
            for (Integer integer : list) {
                stateProbability[i][integer] = Math.pow(pheromoneMatrix[i][integer], ShirtAntTestData.alpha) *
                        Math.pow(shirtAntTestData.getHeuristicMatrix()[i][integer], ShirtAntTestData.beta);
                totalSateProbability[i] += stateProbability[i][integer];
            }
        }
        //计算相邻部件之间的状态转移概率
        for (int i = 0; i < ShirtAntTestData.NUM_OF_SHIRT_PARTS; i++) {
            List<Integer> list = allowedMap.get(i);
            for (Integer integer : list) {
                stateProbability[i][integer] = Math.pow(pheromoneMatrix[i][integer], ShirtAntTestData.alpha) *
                        Math.pow(shirtAntTestData.getHeuristicMatrix()[i][integer], ShirtAntTestData.beta) / totalSateProbability[i];
            }
        }

        return stateProbability;
    }

}
