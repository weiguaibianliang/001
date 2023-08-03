package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic;


import com.Util.GenerateChartUtil;
import com.Util.JFreeChartUtil;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 传统遗传算法
 */
public class BasicGeneticAlgorithm {


    public void run() throws Exception {


        //创建初始种群
        SpeciesModulePopulation list = createBeginningSpecies();

        //创建每一代中的种群数
        List<SpeciesModulePopulation> populationList = new ArrayList<>();

        //每一代中的适应度最小值
        List<Object> bigDecimalList = new ArrayList<>();

        int generation = 0;

        while (generation < ShirtTestData.MAX_GENERATIONS) {

            //轮盘赌选择方法，计算种群P中各个个体的适应度，然后挑出适应度在总的适应度概率最大的那一个个体
            List<BigDecimal> rateList = calculate(list);

            // 打印当前最优解
            SpeciesModuleIndividual fittestIndividual = list.getFittestIndividual();
            bigDecimalList.add(fittestIndividual.getFitness());
            System.out.println("Generation: " + generation + ", Fittest Individual: " + fittestIndividual);

            if (Objects.equals(fittestIndividual.getFitness(), BigDecimal.valueOf(1.0))) {
                // 达到最优解，停止迭代
                break;
            }
            // 创建下一代种群
            SpeciesModulePopulation nextGeneration = new SpeciesModulePopulation();

            for (int j = 0; j < list.getSpeciesNum().size(); j++) {
                //选择
                List<SpeciesModuleIndividual> individualList = select(list, rateList);

                //交叉-算术交叉/单点交叉
                SpeciesModuleIndividual child = singleCrossover(individualList);

                //变异-均匀变异/交换变异
                swapMutate(child);

                // 添加到下一代种群
                nextGeneration.getSpeciesNum().add(child);
            }
            // 更新种群
            list = nextGeneration;
            populationList.add(list);
            generation++;
        }

        //生成折线图
        //适应度随迭代次数的变化
        //平均适应度随迭代次数的变化
        lineChart(bigDecimalList, populationList);

    }


    private List<SpeciesModuleIndividual> select(SpeciesModulePopulation list, List<BigDecimal> rateList) {
        //选择适应度高且可行的染色体进行复制
        //找到初始种群中满足可行个体的个体集合
        List<SpeciesModuleIndividual> individualList = new ArrayList<>();
        Map<SpeciesModuleIndividual, BigDecimal> map = getMapByRate(list, rateList);
        List<BigDecimal> bigDecimalList = new ArrayList<>();
        //满足条件的个体
        //满足条件个体的适应度比例
        ShirtTestData shirtTestData = new ShirtTestData();
        RestrictiveCondition test = shirtTestData.getTest();
        List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
        for (int i = 0; i < list.getSpeciesNum().size(); i++) {
            //int[]与List<Integer>的相互转化
            List<Integer> testList = Arrays.stream(list.getSpeciesNum().get(i).getGenes())
                    .boxed().collect(Collectors.toList());
            if (test.isValidPath(testList, allToPoSorts)) {
                individualList.add(list.getSpeciesNum().get(i));
                bigDecimalList.add(map.get(list.getSpeciesNum().get(i)));
            }
        }
        //建立新的种群个体
        List<SpeciesModuleIndividual> newIndividualList = new ArrayList<>();
        //建立累加概率集合
        //挑选中间的5个个体作为选择目标
        bigDecimalList.sort(BigDecimal::compareTo);
        individualList.sort(Comparator.comparing(SpeciesModuleIndividual::getFitness));
        List<SpeciesModuleIndividual> individualList1 = individualList.subList(0, 9);
        List<SpeciesModuleIndividual> individualList2 = individualList.subList(individualList.size() - 11, individualList.size() - 1);
        individualList1.addAll(individualList2);
        List<BigDecimal> bigDecimalList1 = individualList1.stream().map(SpeciesModuleIndividual::getFitness)
                .collect(Collectors.toList());
        //参考挑选个体的总适应度
        BigDecimal totalFitness = bigDecimalList1.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        List<BigDecimal> addBigDecimalList = new ArrayList<>();
        //计算满足条件个体的累加概率
        BigDecimal addRate = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : bigDecimalList1) {
            addRate = addRate.add(bigDecimal.divide(totalFitness, 10, RoundingMode.UP));
            addBigDecimalList.add(addRate);
        }
        //在满足条件的可行域中找出最大适应度个体
        BigDecimal maxIndividualRate = addBigDecimalList.stream().max(
                Comparator.comparing(bigDecimal -> bigDecimal)).orElse(null);
        for (int i = 0; i < 2; i++) {
            //产生0-1的概率
            BigDecimal rate = BigDecimal.valueOf(Math.random());
            if (rate.compareTo(maxIndividualRate) <= 0) {
                for (int j = 0; j < addBigDecimalList.size(); j++) {
                    if (rate.compareTo(addBigDecimalList.get(j)) <= 0) {
                        //把满足条件的个体加在新种群中
                        newIndividualList.add(individualList1.get(j));
                        break;
                    }
                }
            } else {
                //如果随机概率比累加概率的最大值都还要大，直接把累加概率最大的个体放进新种群中
                newIndividualList.add(individualList.get(addBigDecimalList.size() - 1));
            }

        }
//        //建立累加概率集合
//        List<BigDecimal> addBigDecimalList = new ArrayList<>();
//        //计算满足条件个体的累加概率
//        BigDecimal addRate = BigDecimal.ZERO;
//        for (BigDecimal bigDecimal : bigDecimalList) {
//            addRate = addRate.add(bigDecimal);
//            addBigDecimalList.add(addRate);
//        }
//        //在满足条件的可行域中找出最大适应度个体
//        BigDecimal maxIndividualRate = addBigDecimalList.stream().max(
//                Comparator.comparing(bigDecimal -> bigDecimal)).orElse(null);
//        for (int i = 0; i < 2; i++) {
//            //产生0-1的概率
//            BigDecimal rate = BigDecimal.valueOf(Math.random());
//            if (rate.compareTo(maxIndividualRate) <= 0) {
//                for (int j = 0; j < addBigDecimalList.size(); j++) {
//                    if (rate.compareTo(addBigDecimalList.get(j)) <= 0) {
//                        //把满足条件的个体加在新种群中
//                        newIndividualList.add(individualList.get(j));
//                        break;
//                    }
//                }
//            } else {
//                //如果随机概率比累加概率的最大值都还要大，直接把累加概率最大的个体放进新种群中
//                newIndividualList.add(individualList.get(addBigDecimalList.size() - 1));
//            }
//
//        }
        return newIndividualList;
    }

    private Map<SpeciesModuleIndividual, BigDecimal> getMapByRate(SpeciesModulePopulation list, List<BigDecimal> rateList) {
        Map<SpeciesModuleIndividual, BigDecimal> map = new HashMap<>();
        for (int i = 0; i < rateList.size(); i++) {
            map.put(list.getSpeciesNum().get(i), rateList.get(i));
        }
        return map;
    }

    private List<BigDecimal> calculate(SpeciesModulePopulation list) {
        //计算总适应度
        BigDecimal totalFitness = BigDecimal.ZERO;
        List<SpeciesModuleIndividual> individualList = list.getSpeciesNum();
        for (SpeciesModuleIndividual individual : individualList) {
            //计算每个个体的适应度
            individual.calFitness();
            BigDecimal fitness = individual.getFitness();
            //总适应度是每个个体的适应度相加
            totalFitness = totalFitness.add(fitness);

        }
        List<BigDecimal> rateList = new ArrayList<>();
        for (SpeciesModuleIndividual individual : individualList) {
            //计算每个个体的适应度
            individual.calFitness();
            BigDecimal fitness = individual.getFitness();
            //计算每个个体被选中的概率
            BigDecimal rate = fitness.divide(totalFitness, 10, RoundingMode.UP);
            individual.setRate(rate);
            rateList.add(rate);
        }
        return rateList;
    }

    //实数编码，采用算术交叉
    private SpeciesModuleIndividual crossover(List<SpeciesModuleIndividual> list) {
        //以交叉概率范围内进行选择交叉
        SpeciesModuleIndividual individual1 = list.get(0);
        SpeciesModuleIndividual individual2 = list.get(1);
        double alpha = 0.2; // 权重系数
        int length = individual1.getGenes().length;
        int[] offspring = new int[length];
        for (int i = 0; i < length; i++) {
            offspring[i] = (int) (alpha * individual1.getGenes()[i] + (1 - alpha) * individual2.getGenes()[i]);
        }
        SpeciesModuleIndividual speciesModuleIndividual = new SpeciesModuleIndividual();
        speciesModuleIndividual.setGenes(offspring);
        //满足条件的个体
        //满足条件个体的适应度比例
        ShirtTestData shirtTestData = new ShirtTestData();
        RestrictiveCondition test = shirtTestData.getTest();
        List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
        //int[]与List<Integer>的相互转化
        List<Integer> testList = Arrays.stream(speciesModuleIndividual.getGenes())
                .boxed().collect(Collectors.toList());
        if (test.isValidPath(testList, allToPoSorts)) {
            return speciesModuleIndividual;
        }
        return list.get(0);
    }

    //实数编码，采用单点交叉
    private SpeciesModuleIndividual singleCrossover(List<SpeciesModuleIndividual> list) {
        //以交叉概率范围内进行选择交叉

        SpeciesModuleIndividual individual1 = list.get(0);
        SpeciesModuleIndividual individual2 = list.get(1);
        SpeciesModuleIndividual individual = new SpeciesModuleIndividual();
        int length = individual1.getGenes().length;
        int[] offspring = new int[length];
        Random random = new Random();
        BigDecimal crossoverRate = BigDecimal.valueOf(Math.random());
        boolean flag = crossoverRate.compareTo(ShirtTestData.CROSSOVER_RATE_L) >= 0
                && crossoverRate.compareTo(ShirtTestData.CROSSOVER_RATE_H) <= 0;
        if (crossoverRate.compareTo(BigDecimal.valueOf(0.75)) <= 0) {
            int crossoverPoint = random.nextInt(5) + 2;
            for (int i = 0; i < crossoverPoint; i++) {
                offspring[i] = individual1.getGenes()[i];
            }

            for (int i = crossoverPoint; i < length; i++) {
                offspring[i] = individual2.getGenes()[i];
            }

//            System.arraycopy(individual1.getGenes(), 0, offspring, 0, crossoverPoint);
//
//            if (length - crossoverPoint >= 0)
//                System.arraycopy(individual2.getGenes(), crossoverPoint, offspring, crossoverPoint, length - crossoverPoint);
        } else {
            //如果不能变异，直接把父代的个体传进去
            offspring = Arrays.copyOf(individual1.getGenes(), length);
        }
        individual.setGenes(offspring);
        individual.calFitness();
//        individual.setRate(individual.getFitness()/);
        //满足条件的个体
        ShirtTestData shirtTestData = new ShirtTestData();
        RestrictiveCondition test = shirtTestData.getTest();
        List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
        //int[]与List<Integer>的相互转化
        List<Integer> testList = Arrays.stream(individual.getGenes())
                .boxed().collect(Collectors.toList());
        if (test.isValidPath(testList, allToPoSorts)) {
            return individual;
        }
        return list.get(0);
    }

    //保持种群的多样性（均匀变异）
    private void mutate(SpeciesModuleIndividual speciesModuleIndividual) {
        Random random = new Random();
        for (int i = 0; i < speciesModuleIndividual.getGenes().length; i++) {
            BigDecimal crossoverRate = BigDecimal.valueOf(Math.random());
            boolean flag = crossoverRate.compareTo(ShirtTestData.MUTATION_RATE_L) >= 0
                    && crossoverRate.compareTo(ShirtTestData.MUTATION_RATE_H) <= 0;
            if (crossoverRate.compareTo(BigDecimal.valueOf(0.065)) <= 0) {
                //进行变异操作，增加一个随机值
                int newValue = (int) (speciesModuleIndividual.getGenes()[i] + random.nextDouble() - 0.5);
                speciesModuleIndividual.getGenes()[i] = newValue;
            }
            //满足条件的个体
            //满足条件个体的适应度比例
            ShirtTestData shirtTestData = new ShirtTestData();
            RestrictiveCondition test = shirtTestData.getTest();
            List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
            //int[]与List<Integer>的相互转化
            List<Integer> testList = Arrays.stream(speciesModuleIndividual.getGenes())
                    .boxed().collect(Collectors.toList());
            if (test.isValidPath(testList, allToPoSorts)) {
                break;
            }
        }

    }

    //交换变异
    private void swapMutate(SpeciesModuleIndividual speciesModuleIndividual) {
        int length = speciesModuleIndividual.getGenes().length;
        int[] mutatedIndividual = Arrays.copyOf(speciesModuleIndividual.getGenes(), length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            BigDecimal crossoverRate = BigDecimal.valueOf(Math.random());
            boolean flag = crossoverRate.compareTo(ShirtTestData.MUTATION_RATE_L) >= 0
                    && crossoverRate.compareTo(ShirtTestData.MUTATION_RATE_H) <= 0;
            if (crossoverRate.compareTo(BigDecimal.valueOf(0.08)) <= 0) {
                // 选择随机的位点进行变异
                int swapPosition = random.nextInt(5) + 2;
                // 交换值
                int temp = mutatedIndividual[i];
                mutatedIndividual[i] = mutatedIndividual[swapPosition];
                mutatedIndividual[swapPosition] = temp;
            }
        }
        //满足条件的个体
        ShirtTestData shirtTestData = new ShirtTestData();
        RestrictiveCondition test = shirtTestData.getTest();
        List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
        //int[]与List<Integer>的相互转化
        List<Integer> testList = Arrays.stream(mutatedIndividual)
                .boxed().collect(Collectors.toList());
        if (test.isValidPath(testList, allToPoSorts)) {
            speciesModuleIndividual.setGenes(mutatedIndividual);
            speciesModuleIndividual.calFitness();
        }

    }

    private SpeciesModulePopulation createBeginningSpecies() {

        //创建初始种群
        SpeciesModulePopulation list = new SpeciesModulePopulation();
        Random random = new Random();
        while (list.getSpeciesNum().size() < ShirtTestData.POPULATION_SIZE) {
            //创建结点
            SpeciesModuleIndividual individual = new SpeciesModuleIndividual();
            //满足条件的个体
            //满足条件个体的适应度比例
            ShirtTestData shirtTestData = new ShirtTestData();
            RestrictiveCondition test = shirtTestData.getTest();
            List<List<Integer>> allToPoSorts = test.allTopologicalSorts();
            List<Integer> randomSort = allToPoSorts.get(random.nextInt(30));
            for (int i = 0; i < randomSort.size(); i++) {
                individual.getGenes()[i] = randomSort.get(i);
            }
            individual.calFitness();
            list.getSpeciesNum().add(individual);
        }
        List<BigDecimal> fitnessList = list.getSpeciesNum().stream().map(SpeciesModuleIndividual::getFitness).
                sorted(BigDecimal::compareTo).collect(Collectors.toList());
        BigDecimal maxFitness = fitnessList.get(fitnessList.size() - 1);
        BigDecimal minFitness = fitnessList.get(0);
        SpeciesModulePopulation speciesModulePopulation = new SpeciesModulePopulation();
        for (int i = 0; i < list.getSpeciesNum().size(); i++) {
            if (list.getSpeciesNum().get(i).getFitness().compareTo(minFitness) > 0 &&
                    list.getSpeciesNum().get(i).getFitness().compareTo(maxFitness) < 0) {
                speciesModulePopulation.getSpeciesNum().add(list.getSpeciesNum().get(i));
            }
        }
        int length = ShirtTestData.POPULATION_SIZE - speciesModulePopulation.getSpeciesNum().size();
        for (int i = 0; i < length; i++) {
            speciesModulePopulation.getSpeciesNum().add(speciesModulePopulation.getSpeciesNum().get(random.nextInt(speciesModulePopulation.getSpeciesNum().size())));
        }
        return speciesModulePopulation;

    }


    private static void lineChart(List<Object> bigDecimalList, List<SpeciesModulePopulation> populationList) throws Exception {
        //x轴名称列表
        String[] array = new String[300];
        for (int i = 0; i < 300; i++) {
            array[i] = String.valueOf((i));
        }
        List<String> xAxisNameList = new ArrayList<>(Arrays.asList(array));
        //图例名称列表
        List<String> legendNameList = new ArrayList<>(Arrays.asList("目标函数最优值", "目标函数平均值"));
        //数据列表
        //得到所有代中每一代个体的适应度平均值List
        List<Object> averageBigDecimalList = new ArrayList<>();
        for (SpeciesModulePopulation speciesModulePopulation : populationList) {
            List<SpeciesModuleIndividual> speciesModuleIndividualList = speciesModulePopulation.getSpeciesNum();
            BigDecimal bigDecimal = speciesModuleIndividualList.stream().map(SpeciesModuleIndividual::getFitness)
                    .collect(Collectors.toList()).stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            averageBigDecimalList.add(bigDecimal.divide(BigDecimal.valueOf(speciesModuleIndividualList.size()), 10, RoundingMode.UP));
        }
        List<List<Object>> dataList = new ArrayList<>();
        dataList.add(bigDecimalList);
        dataList.add(averageBigDecimalList);

        JFreeChart chart = GenerateChartUtil.createLineChart("改进遗传算法最优组合", legendNameList, xAxisNameList
                , dataList, JFreeChartUtil.createChartTheme("宋体"), "适应度大小", "迭代次数");
        //在D盘目录下生成图片
        File p = new File("D:\\");
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName = System.currentTimeMillis() + "_折线图" + ".jpeg";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if (file.exists()) {
                file.delete();
            }
            ChartUtils.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
