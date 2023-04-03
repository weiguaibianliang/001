package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum ShortSleeveEnum {

    /**
     * 平口短袖
     */
    FLAT_SHORT_SLEEVE(0,"平口短袖"),

    /**
     * 叠口短袖
     */
    FOLD_SHORT_SLEEVE(1,"叠口短袖");




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

    ShortSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){

        for (ShortSleeveEnum shortSleeveEnum : ShortSleeveEnum.values()) {
            if(shortSleeveEnum.getType() == type){
                return shortSleeveEnum.getName();
            }
        }
        return null;
    }

    //通过首字的数学分析法建立哈希函数
    public static Map<String,Integer> getFirstCharMap(){
        Map<String,Integer> map = new HashMap<>(16);
        for (ShortSleeveEnum shortSleeveEnum : ShortSleeveEnum.values()) {
            String str = shortSleeveEnum.getName().substring(0,1);
            map.put(str, shortSleeveEnum.getType());
        }
        return map;
    }
}
