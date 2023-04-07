package com.Enum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum LongSleeveEnum {

    /**
     * 袖身形状：平袖、圆袖、插肩袖、蓬蓬袖、塔袖、喇叭袖、马蹄袖
     */

    /**
     * 袖口多少粒得考虑
     */

    /**
     * 圆角袖克夫（袖头、袖英）
     */
    ROUND_CUFF(0,"圆角袖克夫"),

    /**
     * 直角袖克夫
     */
    STRAIGHT_CUFF(1,"直角袖克夫"),

    /**
     * 截角（切角）袖克夫
     */
    SLANT_CUFF(2,"截角袖克夫"),

    /**
     * 法式双叠袖袖克夫
     */
    FRENCH_FOLD_CUFF(3,"法式双叠袖袖克夫"),

    /**
     * 米兰袖袖克夫
     */
    MI_LAN_CUFF(4,"米兰袖袖克夫"),

    /**
     * 袖口不开衩
     */
    CUFF_WITHOUT_SLIT(5,"袖口不开衩"),

    /**
     * 袖口开衩
     */
    CUFF_WITH_SLIT(6,"袖口开衩"),

    /**
     * 直袖衩条
     */
    STRAIGHT_SLIT_STRIP(7,"直袖衩条"),

    /**
     * 琵琶袖衩条（宝箭头袖衩条）
     */
    PI_PA_SLIT_STRIP(8,"琵琶袖衩条"),

    /**
     * 暗袖衩条
     */
    DARK_SLIT_STRIP(9,"暗袖衩条"),

    /**
     * 直袖衩折转式琵琶袖衩条
     */
    STRAIGHT_FOLD_PI_PA_SLIT_STRIP(10,"直袖衩折转式琵琶袖衩条"),

    /**
     * 特殊外贴式琵琶袖衩条
     */
    SPECIAL_EXTERNAL_PI_PA_SLIT_STRIP(11,"特殊外贴式琵琶袖衩条"),

    /**
     * 大小袖片拼缝式斜角袖衩条
     */
    LARGE_SMALL_TOGETHER_SLIT_STRIP(12,"大小袖片拼缝式斜角袖衩条"),

    /**
     * 灯笼袖口
     */
    LANTERN_CUFF(13,"灯笼袖口"),

    /**
     * 窄袖头（克夫）
     */
    NARROW_SLEEVE_HEAD(14,"窄袖头");





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

    LongSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){

        for (LongSleeveEnum longSleeveEnum : LongSleeveEnum.values()) {
            if(longSleeveEnum.type == type){
                return longSleeveEnum.getName();
            }
        }
        return null;
    }

    //袖克夫首字建立数学分析哈希函数
    public static Map<String,Integer> getFirstCharCuffMap(){

        Map<String,Integer> map = new HashMap<>(16);
        List<LongSleeveEnum> list = Arrays.stream(LongSleeveEnum.values()).filter(longSleeveEnum -> longSleeveEnum.type > -1 && longSleeveEnum.type < 5).collect(Collectors.toList());
        for (LongSleeveEnum longSleeveEnum : list) {
            String str = longSleeveEnum.getName().substring(0,1);
            map.put(str,longSleeveEnum.getType());
        }
        map.put("窄",LongSleeveEnum.NARROW_SLEEVE_HEAD.getType());
        return map;
    }

    //袖衩条特殊字段建立数学分析哈希函数
    public static Map<String,Integer> getSpecialCharMap(){

        Map<String,Integer> map = new HashMap<>(16);
        List<LongSleeveEnum> list = Arrays.stream(LongSleeveEnum.values()).filter(longSleeveEnum -> longSleeveEnum.type > 6 && longSleeveEnum.type < 10).collect(Collectors.toList());
        for (LongSleeveEnum longSleeveEnum : LongSleeveEnum.values()) {
            String str = longSleeveEnum.getName().substring(0,1);
            map.put(str, longSleeveEnum.getType());
        }
        map.put("宝",LongSleeveEnum.PI_PA_SLIT_STRIP.getType());
        map.put("折",LongSleeveEnum.STRAIGHT_FOLD_PI_PA_SLIT_STRIP.getType());
        map.put("贴",LongSleeveEnum.SPECIAL_EXTERNAL_PI_PA_SLIT_STRIP.getType());
        map.put("拼",LongSleeveEnum.LARGE_SMALL_TOGETHER_SLIT_STRIP.getType());
        return map;
    }




}
