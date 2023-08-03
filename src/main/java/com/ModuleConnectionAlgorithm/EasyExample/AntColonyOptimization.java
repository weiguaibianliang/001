package com.ModuleConnectionAlgorithm.EasyExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimization {
    private int numberOfAnts; // 蚂蚁数量
    private int numberOfIterations; // 迭代次数
    private double[][] pheromoneMatrix; // 信息素矩阵
    private double[][] heuristicMatrix; // 启发信息矩阵
    private double[][] visibilityMatrix; // 能见度矩阵
    private double alpha; // 信息素重要程度参数
    private double beta; // 启发信息重要程度参数
    private double evaporationRate; // 信息素蒸发率
    private int[][] connections; // 部件连接矩阵

    public AntColonyOptimization(int numberOfAnts, int numberOfIterations, double alpha, double beta,
                                 double evaporationRate, int[][] connections) {
        this.numberOfAnts = numberOfAnts;
        this.numberOfIterations = numberOfIterations;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.connections = connections;
        int numberOfComponents = connections.length;
        pheromoneMatrix = new double[numberOfComponents][numberOfComponents];
        heuristicMatrix = new double[numberOfComponents][numberOfComponents];
        visibilityMatrix = new double[numberOfComponents][numberOfComponents];
    }

    public void solve() {
        initializeMatrices();
        List<Integer> bestSolution = null;
        double bestFitness = Double.MAX_VALUE;

        for (int iteration = 0; iteration < numberOfIterations; iteration++) {
            List<List<Integer>> solutions = constructSolutions();
            updatePheromones(solutions);

                for (List<Integer> solution : solutions) {
                    double fitness = evaluateFitness(solution);
                    if (fitness < bestFitness) {
                        bestFitness = fitness;
                        bestSolution = new ArrayList<>(solution);
                    }
                }
        }

        System.out.println("Best Solution: " + bestSolution);
        System.out.println("Best Fitness: " + bestFitness);
    }

    private void initializeMatrices() {
        int numberOfComponents = connections.length;

        // 初始化信息素矩阵和启发信息矩阵
        for (int i = 0; i < numberOfComponents; i++) {
            for (int j = 0; j < numberOfComponents; j++) {
                pheromoneMatrix[i][j] = 1.0; // 初始信息素浓度设为1
                heuristicMatrix[i][j] = 1.0 / connections[i][j]; // 启发信息设为连接强度的倒数
            }
        }
    }

    private List<List<Integer>> constructSolutions() {
        List<List<Integer>> solutions = new ArrayList<>();

        for (int ant = 0; ant < numberOfAnts; ant++) {
            List<Integer> solution = new ArrayList<>();
            boolean[] visited = new boolean[connections.length];
            int currentNode = 0; // 起始节点为0

            while (solution.size() < connections.length) {
                visited[currentNode] = true;
                solution.add(currentNode);

                int nextNode = selectNextNode(currentNode, visited);
                currentNode = nextNode;
            }

            solutions.add(solution);
        }

        return solutions;
    }

    private int selectNextNode(int currentNode, boolean[] visited) {
        double[] probabilities = new double[connections.length];
        double totalProbability = 0.0;

        for (int i = 0; i < connections.length; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromoneMatrix[currentNode][i], alpha) *
                        Math.pow(heuristicMatrix[currentNode][i], beta);
                totalProbability += probabilities[i];
            }
        }

        double randomValue = new Random().nextDouble();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < connections.length; i++) {
            if (!visited[i]) {
                probabilities[i] /= totalProbability;
                cumulativeProbability += probabilities[i];

                if (randomValue <= cumulativeProbability) {
                    return i;
                }
            }
        }

        // 如果无法选择下一个节点，则随机选择一个未访问节点
        for (int i = 0; i < connections.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }

        return -1; // 如果所有节点都已访问，则返回-1
    }

    private void updatePheromones(List<List<Integer>> solutions) {
        for (int i = 0; i < connections.length; i++) {
            for (int j = 0; j < connections.length; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate); // 信息素蒸发

                for (List<Integer> solution : solutions) {
                    if (solution.contains(i) && solution.contains(j)) {
                        pheromoneMatrix[i][j] += (1.0 / evaluateFitness(solution)); // 信息素增加
                    }
                }
            }
        }
    }

    private double evaluateFitness(List<Integer> solution) {
        // 根据优化指标计算适应度函数值
        // 这里简单地将优化指标相加作为适应度函数值
        double fitness = 0.0;

        // 假设优化指标是缝制设备变换次数、缝制组合连续性、缝制组合稳定性、缝制组合精度
        for (int i = 0; i < solution.size() - 1; i++) {
            int componentA = solution.get(i);
            int componentB = solution.get(i + 1);
            fitness += connections[componentA][componentB];
        }

        return fitness;
    }

    public static void main(String[] args) {
        int numberOfAnts = 10;
        int numberOfIterations = 100;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;

        int[][] connections = {
                {0, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 0}
        };
        AntColonyOptimization aco = new AntColonyOptimization(numberOfAnts, numberOfIterations, alpha, beta,
                evaporationRate, connections);
        aco.solve();
    }
}
