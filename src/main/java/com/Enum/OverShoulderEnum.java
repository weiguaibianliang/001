package com.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OverShoulderEnum {


    /**
     * 过肩类型：落肩、正肩、插肩（根据肩线的位置来判断）
     */
    /**
     * 休闲衬衫有的没有过多的肩部结构和剪裁，衣领和袖口通常直接连接，没有过肩片或肩部加强结构。
     */

    /**
     * 后肩育克（只有后片那里是双层的，前片直接与后肩育克相连）
     */
    REAR_SHOULDER_YUKON(0,"后肩育克"),

    /**
     * 前后肩育克（前后肩缝合形成双层过肩是指前后两片衬衫分别带有肩部结构，在缝合后形成的过肩处是双层的）
     * 双层过肩
     */
    FRONT_REAR_SHOULDER_YUKON(1,"前后肩育克"),

    /**
     * 前片与后片合并成单独的过肩(这种不需要单独的过肩片设计，前片和后片的肩线会被合并成一个单独的过肩线，
     * 袖子则是分别缝制在前后片上的。)
     */
    FRONT_MERGE_BACK_SHOULDER(2,"前片与后片合并成单独的过肩");

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

    OverShoulderEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){
        for (OverShoulderEnum overShoulderEnum : OverShoulderEnum.values()) {
            if(overShoulderEnum.getType() == type){
                return overShoulderEnum.getName();
            }
        }
        return null;
    }

    public static List<String> getListByVital(){
        List<String> list = new ArrayList<>();
        list.add("前");
        list.add("后");
        list.add("合并");
        return list;
    }
    public static Map<String,Integer> getVitalCharMap(){

        Map<String,Integer> map = new HashMap<>(16);
        for (OverShoulderEnum overShoulderEnum : OverShoulderEnum.values()) {
            map.put(overShoulderEnum.getName().substring(0,1),overShoulderEnum.getType());
        }
        return map;

    }
}
