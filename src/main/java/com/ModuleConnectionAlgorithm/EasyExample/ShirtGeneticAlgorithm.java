package com.ModuleConnectionAlgorithm.EasyExample;

import java.util.Arrays;
import java.util.Random;

public class ShirtGeneticAlgorithm {
    private static final int POPULATION_SIZE = 100;
    private static final int MAX_GENERATIONS = 100;
    private static final double MUTATION_RATE = 0.1;

    private static final int NUM_COMPONENTS = 7;

    private static final int FITNESS_WEIGHT_1 = 1; // 部件完整度权重
    private static final int FITNESS_WEIGHT_2 = 1; // 美观度权重
    private static final int FITNESS_WEIGHT_3 = 1; // 辅助时间权重

    private static final Random random = new Random();

    private static class Individual {
        int[] chromosome;
        double fitness;

        Individual() {
            this.chromosome = new int[NUM_COMPONENTS];
            initialize();
        }

        void initialize() {
            for (int i = 0; i < NUM_COMPONENTS; i++) {
                chromosome[i] = i;
            }
            shuffleChromosome();
        }

        void shuffleChromosome() {
            for (int i = chromosome.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = chromosome[i];
                chromosome[i] = chromosome[j];
                chromosome[j] = temp;
            }
        }

        double calculateFitness() {
            // 在这里根据自定义的评估标准计算适应度
            double fitness = FITNESS_WEIGHT_1 * calculatePartCompleteness() +
                    FITNESS_WEIGHT_2 * calculateAppearanceQuality() +
                    FITNESS_WEIGHT_3 * calculateAuxiliaryTime();

            this.fitness = fitness;
            return fitness;
        }

        double calculatePartCompleteness() {
            // 计算部件完整度，根据部件之间的依赖关系来评估
            // 返回一个适应度值，表示部件缝制的完整度
            // 需根据具体问题进行实现
            // 这里只是一个示例，假设部件完整度是根据顺序的正确性计算的
            int count = 0;
            for (int i = 0; i < chromosome.length - 1; i++) {
                if (chromosome[i] < chromosome[i + 1]) {
                    count++;
                }
            }
            return (double) count / (chromosome.length - 1);
        }

        double calculateAppearanceQuality() {
            // 计算美观度，根据衬衫外观的质量来评估
            // 返回一个适应度值，表示衬衫外观的美观度
            // 需根据具体问题进行实现
            // 这里只是一个示例，假设外观质量是根据部件位置的对齐程度计算的
            int count = 0;
            for (int i = 0; i < chromosome.length - 1; i++) {
                if (chromosome[i] < chromosome[i + 1]) {
                    count++;
                }
            }
            return (double) count / (chromosome.length - 1);
        }

        double calculateAuxiliaryTime() {
            // 计算辅助时间，根据部件的缝制顺序评估所需的辅助时间。
            //// 返回一个适应度值，表示所需的辅助时间
            //// 需根据具体问题进行实现
            //// 这里只是一个示例，假设辅助时间与部件的顺序有关
            int time = 0;
            for (int i = 0; i < chromosome.length; i++) {
            time += chromosome[i]; // 假设每个部件的缝制时间是按照顺序递增的
            }
            return (double) time;
            }

        @Override
        public String toString() {
            return "Chromosome: " + Arrays.toString(chromosome) + ", Fitness: " + fitness;
        }
    }


    private static class Population {
        Individual[] individuals;

        Population() {
            this.individuals = new Individual[POPULATION_SIZE];
            initialize();
        }

        void initialize() {
            for (int i = 0; i < POPULATION_SIZE; i++) {
                individuals[i] = new Individual();
            }
        }

        Individual getFittestIndividual() {
            Individual fittest = individuals[0];
            for (int i = 1; i < POPULATION_SIZE; i++) {
                if (individuals[i].fitness > fittest.fitness) {
                    fittest = individuals[i];
                }
            }
            return fittest;
        }
    }

    public static void main(String[] args) {
        Population population = new Population();
        int generation = 0;

        while (generation < MAX_GENERATIONS) {
            // 计算适应度值
            for (Individual individual : population.individuals) {
                individual.calculateFitness();
            }

            // 打印当前最优解
            Individual fittestIndividual = population.getFittestIndividual();
            System.out.println("Generation: " + generation + ", Fittest Individual: " + fittestIndividual);

            if (fittestIndividual.fitness == 1.0) {
                // 达到最优解，停止迭代
                break;
            }

            // 创建下一代种群
            Population nextGeneration = new Population();

            // 选择操作
            for (int i = 0; i < POPULATION_SIZE; i++) {
                Individual parent1 = selectParent(population);
                Individual parent2 = selectParent(population);

                // 交叉操作
                Individual child = crossover(parent1, parent2);

                // 变异操作
                mutate(child);

                // 添加到下一代种群
                nextGeneration.individuals[i] = child;
            }

            // 更新种群
            population = nextGeneration;

            generation++;
        }
    }

    private static Individual selectParent(Population population) {
        // 使用锦标赛选择算子选择一个父代个体
        int tournamentSize = 5;
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = random.nextInt(POPULATION_SIZE);
            tournament.individuals[i] = population.individuals[randomIndex];
        }
        return tournament.getFittestIndividual();
    }

    private static Individual crossover(Individual parent1, Individual parent2) {
        // 使用单点交叉操作生成子代个体
        Individual child = new Individual();
        int crossoverPoint = random.nextInt(NUM_COMPONENTS);
        for(int i = 0; i < NUM_COMPONENTS; i++) {
            if (i < crossoverPoint) {
                child.chromosome[i] = parent1.chromosome[i];
            } else {
                child.chromosome[i] = parent2.chromosome[i];
            }
        }
        return child;
    }
    private static void mutate(Individual individual) {
        // 使用位翻转变异操作对个体进行变异
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            if (Math.random() < MUTATION_RATE) {
                int randomIndex = random.nextInt(NUM_COMPONENTS);
                int temp = individual.chromosome[i];
                individual.chromosome[i] = individual.chromosome[randomIndex];
                individual.chromosome[randomIndex] = temp;
            }
        }
    }
}

