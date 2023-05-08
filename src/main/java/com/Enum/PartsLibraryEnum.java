package com.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PartsLibraryEnum {


    /**
     * 领
     */
    COLLAR(0,"领"),

    /**
     * 育克（过肩、担干）
     */
    OVER_SHOULDER(1,"育克"),

    /**
     * 门襟
     */
    LAPEL(2,"襟"),


    /**
     * 胸袋
     */
    CHEST_POUCH(3,"袋"),


    /**
     * 省
     */
    PROVINCE(4,"省"),

    /**
     * 褶
     */
    PLEAT(5,"褶"),

    /**
     * 裥
     */
    FOLD(6,"裥"),

    /**
     * 下摆
     */
    HEM(7,"摆"),


    /**
     * 袖
     */
    SLEEVE(8,"袖"),

    /**
     * 短袖
     */
    SHORT_SLEEVE(9,"短袖"),

    /**
     * 五分袖
     */
    FIVE_SLEEVE(10,"五分袖"),

    /**
     * 七分袖
     */
    SEVENTH_SLEEVE(11,"七分袖");

    private Integer type;

    private String name;

    private static final Character[] CN_NUMERIC = { '一', '二', '三', '四', '五',
            '六', '七', '八', '九', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖',
            '十', '百', '千', '拾', '佰', '仟', '万', '亿', '○', 'Ｏ', '零' };

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

    PartsLibraryEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static List<String> getListByOverShoulder(){
        List<String> strings = new ArrayList<>();
        strings.add(PartsLibraryEnum.OVER_SHOULDER.name);
        strings.add("过肩");
        strings.add("担干");
        return strings;
    }

    public static Map<Character, Integer> getButtonMap(){
        Map<Character,Integer> cnNumeric = new HashMap<Character, Integer>(40, 0.85f);
        for (int j = 0; j < 9; j++)
            cnNumeric.put(CN_NUMERIC[j], j + 1);
        for (int j = 9; j < 18; j++)
            cnNumeric.put(CN_NUMERIC[j], j - 8);
        cnNumeric.put('两', 2);
        cnNumeric.put('十', 10);
        cnNumeric.put('拾', 10);
        cnNumeric.put('百', 100);
        cnNumeric.put('佰', 100);
        cnNumeric.put('千', 1000);
        cnNumeric.put('仟', 1000);
        cnNumeric.put('万', 10000);
        cnNumeric.put('亿', 100000000);
        cnNumeric.put('0',0);
        cnNumeric.put('1',1);
        cnNumeric.put('2',2);
        cnNumeric.put('3',3);
        cnNumeric.put('4',4);
        cnNumeric.put('5',5);
        cnNumeric.put('6',6);
        cnNumeric.put('7',7);
        cnNumeric.put('8',8);
        cnNumeric.put('9',9);
        return cnNumeric;
    }

}
