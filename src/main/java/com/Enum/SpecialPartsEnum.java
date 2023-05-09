package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum SpecialPartsEnum {


    /**
     * 肩部
     */
    SHOULDER(0,"肩部"),

    /**
     * 袖口
     */
    CUFFS(1,"袖口"),

    /**
     * 领口
     */
    NECKLINE(2,"领口"),

    /**
     * 袖窿
     */
    SLEEVE_HOLE(3,"袖窿");


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

    SpecialPartsEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }


    public static Map<String,Integer> getSpecialPartsMap(){
        Map<String,Integer> map = new HashMap<>(16);
        for (SpecialPartsEnum specialPartsEnum : SpecialPartsEnum.values()) {
            map.put(specialPartsEnum.getName(), specialPartsEnum.getType());
        }
        return map;
    }
}
