package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum OverShoulderEnum {


    /**
     * 过肩类型：落肩、正肩、插肩（根据肩线的位置来判断）
     */

    /**
     * 后肩育克
     */
    REAR_SHOULDER_YUKON(0,"后肩育克"),

    /**
     * 前后肩育克
     */
    FRONT_REAR_SHOULDER_YUKON(0,"前后肩育克");

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

    public static Map<String,Integer> getFirstCharMap(){

        Map<String,Integer> map = new HashMap<>(16);
        for (OverShoulderEnum overShoulderEnum : OverShoulderEnum.values()) {
            map.put(overShoulderEnum.getName().substring(0,1),overShoulderEnum.getType());
        }
        return map;

    }
}
