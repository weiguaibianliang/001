package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum SevenSleeveEnum {

    /**
     * 平口七分袖
     */
    FLAT_SEVENTH_SLEEVE(0,"平口七分袖"),

    /**
     * 叠口七分袖
     */
    FOLD_SEVENTH_SLEEVE(1,"叠口七分袖");


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

    SevenSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){

        for (SevenSleeveEnum sevenSleeveEnum: SevenSleeveEnum.values()) {
            if(sevenSleeveEnum.getType() == type){
                return sevenSleeveEnum.getName();
            }
        }
        return null;
    }

    //通过首字的数学分析法建立哈希函数
    public static Map<String,Integer> getFirstCharMap(){
        Map<String,Integer> map = new HashMap<>(16);
        for (SevenSleeveEnum sevenSleeveEnum: SevenSleeveEnum.values()) {
            String str = sevenSleeveEnum.getName().substring(0,1);
            map.put(str, sevenSleeveEnum.getType());
        }
        return map;
    }
}
