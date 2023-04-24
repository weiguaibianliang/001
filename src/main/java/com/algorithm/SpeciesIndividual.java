package com.algorithm;

/**
 * 物种个体类
 * 每一个个体的染色体对应着一个解决方案
 * 基因：直接采用城市序列作为基因的编码，染色体由随机排列的基因组成。
 * 物种适应度：对于TSP问题，直接用总距离的倒数作为物种适应度
 * 打印解决方案：把城市排列出来
 */

import java.util.Random;

/**
 * 个体类包含：
 * 1、createByRandomGenes 初始物种基因（随机）基因直接用城市序列编码
 * 2、calFitness 计算物种适应度
 * 3、printRate 打印路径
 */
public class SpeciesIndividual {
    //基因序列
    String[] genes;
    //路程
    float distance;
    //适应度
    float fitness;
    //个体
    SpeciesIndividual next;

    float rate;

    SpeciesIndividual(){
        //初始化
        this.genes = new String[TSPData.CITY_NUM];
        this.fitness = 0.0f;
        this.distance = 0.0f;
        this.next = null;
        rate = 0.0f;
    }

    //初始物种基因（随机）
    void createByRandomGenes(){
        //初始基因为1-CITY_NUM序列
        for (int i = 0; i < genes.length; i++){
            genes[i] = Integer.toString(i+1);
        }
        //获取随机种子
        Random rand = new Random();
        for (int j = 0; j < genes.length; j++){
            //rand.nextInt（int n）用于获取一个介于0（包括）和此参数（n）中传递的数字之间的随机数。
            int num = j + rand.nextInt(genes.length - j);
            //交换
            String tmp;
            tmp = genes[num];
            genes[num] = genes[j];
            genes[j] = tmp;
        }
    }

    //初始物种基因（贪婪）
    void createByGreedyGenes(){
        Random rand = new Random();
        //随机产生一个城市作为起点
        int i = rand.nextInt(TSPData.CITY_NUM);
        genes[0] = Integer.toString(i+1);
        //终点
        int j;
        int cityNum = 0;
        do {
            cityNum++;
            //选出单源最短的城市
            float minDis = Integer.MAX_VALUE;
            int minCity = 0;
            for (j = 0; j < TSPData.CITY_NUM; j++){
                if(j != i){
                    //判断是否和已有重复
                    boolean repeat = false;
                    for (int n = 0; n < cityNum; n++){
                        if(Integer.parseInt(genes[n])== j+1){
                            repeat = true;
                            break;
                        }
                    }
                    //没重
                    if(repeat == false){
                        //判长度
                        if(TSPData.disMap[i][j] < minDis){
                            minDis = TSPData.disMap[i][j];
                            minCity = j;
                        }

                    }
                }
            }
            //加入染色体
            genes[cityNum] = Integer.toString(minCity +1);
            i = minCity;
        }while (cityNum < TSPData.CITY_NUM - 1);
    }

    //计算物种适应度
    void calFitness(){
        float totalDis = 0.0f;
        for (int i = 0; i < TSPData.CITY_NUM; i++){
            int curCity = Integer.parseInt(this.genes[i]) - 1;
            int nextCity = Integer.parseInt(this.genes[(i + 1) % TSPData.CITY_NUM]) - 1;
            totalDis += TSPData.disMap[curCity][nextCity];
        }
        this.distance = totalDis;
        this.fitness = 1.0f/totalDis;
    }

    //深拷贝
    public SpeciesIndividual clone(){
        SpeciesIndividual species=new SpeciesIndividual();
        //复制值
        System.arraycopy(this.genes, 0, species.genes, 0, this.genes.length);
        species.distance=this.distance;
        species.fitness=this.fitness;

        return species;
    }

    //打印路径
    void printRate(){
        System.out.println("最短路线：");
        for (int i = 0;i < genes.length; i++){
            System.out.print(genes[i]+"->");
        }
        System.out.print(genes[0]+"\n");
        System.out.print("最短长度：" + distance);
    }
}
