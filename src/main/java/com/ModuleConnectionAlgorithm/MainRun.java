package com.ModuleConnectionAlgorithm;

import com.algorithm.GeneticAlgorithm;
import com.algorithm.SpeciesIndividual;
import com.algorithm.SpeciesPopulation;

//基于贪婪策略的改进遗传算法
public class MainRun {
    public static void main(String[] args) {

        //创建模块连接算法驱动对象
        ConnectionAlgorithm CA = new ConnectionAlgorithm();

        //创建初始种群
        SpeciesModulePopulation speciesModulePopulation = new SpeciesModulePopulation();

        //开始遗传算法（选择算子、交叉算子、变异算子）
        SpeciesModuleIndividual bestModuleCombination = CA.run(speciesModulePopulation);

        //打印最佳连接组合和指标最优的参数
        bestModuleCombination.printBestModuleCombination();
    }
}
