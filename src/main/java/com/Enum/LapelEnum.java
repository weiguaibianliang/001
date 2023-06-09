package com.Enum;

import java.util.HashMap;
import java.util.Map;

public enum LapelEnum {

    /**
     * 多少粒扣还不确定
     */

    /**
     * 明门襟
     */
    MING_MEN_LAPEL(0,"明门襟"),

    /**
     * 暗门襟(隐藏式门襟)
     */
    DARK_LAPEL(1,"暗门襟"),

    /**
     * 隐藏式门襟
     */
    HIDDEN_LAPEL(1,"隐藏式门襟"),

    /**
     * 软门襟（法式门襟、平门襟）
     */
    SOFT_LAPEL(2,"软门襟"),

    /**
     * 法式门襟
     */
    FRENCH_STYLE_LAPEL(2,"法式门襟"),

    /**
     * 平门襟
     */
    FLAT_LAPEL(3,"平门襟"),

    /**
     * 塔士多门襟（通常胸前有U形胸挡）
     */
    TAS_TO_LAPEL(3,"塔士多门襟");


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

    LapelEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }


    public static String getNameByType(int type){
        for (LapelEnum lapelEnum : LapelEnum.values()) {
            if(lapelEnum.getType() == type){
                return lapelEnum.getName();
            }
        }
        return null;
    }



    public static Map<String, Integer> getFirstCharMap() {

        Map<String,Integer> map = new HashMap<>(16);
        for (LapelEnum lapelEnum : LapelEnum.values()) {
            map.put(lapelEnum.getName().substring(0,1),lapelEnum.getType());
        }
        return map;
    }
}
