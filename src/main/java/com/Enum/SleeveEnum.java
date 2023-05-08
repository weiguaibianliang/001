package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum SleeveEnum {

    //袖的类型

    /**
     * 一片袖
     */
    ONE_SLEEVE(0,"一片袖"),

    /**
     * 两片袖
     */
    TWO_SLEEVE(1,"两片袖"),

    //袖的裁剪方式
    /**
     * 低袖山形
     */
    LOW_SLEEVE_YAMAGATA(2,"低袖山形"),

    /**
     * 高袖山形
     */
    HIGH_SLEEVE_YAMAGATA(3,"高袖山形"),

    /**
     * 阔袖
     */
    WIDE_SLEEVE(4,"阔袖"),

    /**
     * 紧身袖
     */
    SKINNY_SLEEVE(5,"紧身袖"),

    /**
     * 法式袖
     */
    FRENCH_SLEEVE(6,"法式袖"),

    /**
     * 翻领袖(指一种衬衫袖子的款式，这种袖子被设计成可以翻折，露出袖子内侧的另一面
     * 通常会在袖口处缝有一个纽扣，以便固定翻折的位置)
     */
    TURNING_LEADERS(7,"翻领袖");

    private Integer type;

    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){

        for (SleeveEnum sleeveEnum : SleeveEnum.values()) {
            if(sleeveEnum.type == type){
                return sleeveEnum.getName();
            }
        }
        return null;
    }


    //通过首字的数学分析法建立哈希函数
    public static Map<String,Integer> getFirstCharMap(){
        Map<String,Integer> map = new HashMap<>(16);
        for (SleeveEnum sleeveEnum : SleeveEnum.values()) {
            String str = sleeveEnum.getName().substring(0,1);
            map.put(str, sleeveEnum.getType());
        }
        return map;
    }

}
