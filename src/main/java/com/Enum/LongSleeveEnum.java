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

//    /**
//     * 宝箭头袖衩条
//     */
//    TREASURE_ARROW_SLIT_STRIP(8,"宝箭头袖衩条"),

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
     * 灯笼袖口（常见的是女士衬衫特有，但现在越来越多的男性衣服采用灯笼袖口，追求时尚感）
     */
    LANTERN_CUFF(13,"灯笼袖口"),

    /**
     * 常规袖口（标准袖口）
     * 常规袖口通常指袖口的形状是矩形或圆角矩形，即没有开衩或者其他特殊装饰
     */
    REGULAR_CUFF(14,"常规袖口"),

    /**
     * 窄袖头（克夫）
     */
    NARROW_SLEEVE_HEAD(15,"窄袖头"),

    /**
     * 肘部拼接袖口
     */
    ELBOW_PATCHWORK_CUFF(16,"肘部拼接袖口"),

    /**
     * 半袖套（是一种固定衬衫袖口位置的配件，通常由一条或多条带子组成，将袖子收紧并固定在手臂上，以防止袖子滑动或卷起。
     * 半袖套通常只适用于长袖衬衫，而不是短袖衬衫。）
     */
    HALF_SLEEVE_COVERS(17,"半袖套");




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

    public static Map<String,Integer> getImportantMap(){
        Map<String,Integer> map = new HashMap<>(16);
        map.put("灯笼",LongSleeveEnum.LANTERN_CUFF.getType());
        map.put("常规",LongSleeveEnum.REGULAR_CUFF.getType());
        map.put("肘部",LongSleeveEnum.ELBOW_PATCHWORK_CUFF.getType());

        return map;

    }




}
