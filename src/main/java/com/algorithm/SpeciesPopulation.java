package com.algorithm;


/**
 * 种群类，总群由物种组成。该类功能主要是把物种聚集起来形成总群的。我们姑且就当做一个物种
 * 只有一个个体。
 */

/**
 * 种群类
 * 包含：
 * 1、add 添加物种
 * 2、traverse 遍历
 */
public class SpeciesPopulation {

    //头结点
    SpeciesIndividual head;
    //物种数量
    int speciesNum;
    SpeciesPopulation() {
        head = new SpeciesIndividual();
        speciesNum = TSPData.SPECIES_NUM;
    }

    //添加物种
    void add(SpeciesIndividual species){
        //游标
        SpeciesIndividual point = head;
        //寻找表尾结点
        while (point.next != null){
            point = point.next;
        }
        point.next = species;
    }

    //遍历
    void traverse(){
        //游标
        SpeciesIndividual point = head.next;
        //寻找表尾结点
        while (point != null){
            for(int i=0;i<TSPData.CITY_NUM;i++)
                System.out.print(point.genes[i]+" ");
            System.out.println(point.distance);
            point=point.next;
        }
        System.out.println("_______________________");
        }
    }
