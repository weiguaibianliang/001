package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum FiveSleeveEnum {

    /**
     * 平口五分袖
     */
    FLAT_FIVE_SLEEVE(0,"平口五分袖"),

    /**
     * 叠口五分袖
     */
    FOLD_FIVE_SLEEVE(1,"叠口五分袖");


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

    FiveSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){

        for (FiveSleeveEnum fiveSleeveEnum: FiveSleeveEnum.values()) {
            if(fiveSleeveEnum.getType() == type){
                return fiveSleeveEnum.getName();
            }
        }
        return null;
    }

    //通过首字的数学分析法建立哈希函数
    public static Map<String,Integer> getFirstCharMap(){
        Map<String,Integer> map = new HashMap<>(16);
        for (FiveSleeveEnum fiveSleeveEnum: FiveSleeveEnum.values()) {
            String str = fiveSleeveEnum.getName().substring(0,1);
            map.put(str, fiveSleeveEnum.getType());
        }
        return map;
    }
}
