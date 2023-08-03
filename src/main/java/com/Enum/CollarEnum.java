package com.Enum;

import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CollarEnum {

    //解决hash冲突，将所有哈希地址相同的记录都链接在同一链表中。
    //基本思想是将所有哈希地址为i的元素构成一个为同义词链的单链表。

    /**
     * 领子上的纽扣得考虑
     */
    //map中，多个key可以对应一个value


    /**
     * 标准(翻立领)
     */
    STANDARD(0,"标准领"),

    /**
     * 立领
     */
    STAND_UP_COLLAR(1,"立领"),

    /**
     * 方角领（小方领，方领）
     */
    SMALL_SQUARE_COLLAR(2,"方角领"),

    /**
     * 温莎领（温莎一字领，温莎八字领，敞角领）
     */
    WINDSOR_COLLAR(3,"温莎领"),

    /**
     * 纽扣领（纽扣领一般有一颗扣或者两个扣）
     */
    BUTTON_DOWN_COLLAR(4,"纽扣领"),

    /**
     * 针孔领
     */
    PINHOLE_COLLAR(5,"针孔领"),

    /**
     * 饰耳领
     */
    DECORATIVE_EAR_COLLAR(6,"饰耳领"),

    /**
     * 翼领（燕子领）
     */
    WING_LEADER(7,"翼领"),

    /**
     * 伊顿领（圆角领）
     */
    EATON_COLLAR(8,"伊顿领"),

    /**
     * 尖角领
     */
    LONG_POINTED_COLLAR(9,"尖角领"),

    /**
     * 片领
     */
    SHEET_COLLAR(10,"片领"),

    /**
     * 古巴领
     */
    CUBAN_COLLAR(11,"古巴领"),

    /**
     * 暗扣领
     */
    CONCEAL_BUTTON_COLLAR(12,"暗扣领"),

    /**
     * 异色领
     */
    HETERO_CHROMATIC_COLLAR(13,"异色领"),

    /**
     * 棒球领
     */
    BASEBALL_COLLAR(14,"棒球领"),

    /**
     * 双层领
     */
    DOUBLE_LAYER_COLLAR(15,"双层领"),

    /**
     * 窄角领
     */
    NARROW_HORN_COLLAR(16,"窄角领"),


    /**
     * 长尖领
     */
    LONG_POINT_COLLAR(17,"长尖领"),

    /**
     * 短尖领
     */
    SHORT_POINT_COLLAR(18,"短尖领"),

    /**
     * 小方领
     */
    SQUARE_CORNER_COLLAR(2,"小方领"),

    /**
     * 方领
     */
    SQUARE_COLLAR(2,"方领"),

    /**
     * 敞角领
     */
    OPEN_HORN_COLLAR(3,"敞角领"),

    /**
     * 燕子领
     */
    SWALLOW_COLLAR(7,"燕子领"),

    /**
     * 双翼领
     */
    DOUBLE_WING_LEADER(7,"双翼领"),

    /**
     * 圆角领
     */
    ROUNDED_COLLAR(8,"圆角领"),

    /**
     * 翻立领
     */
    LAPEL_COLLAR(0,"翻立领"),

    /**
     * 无领
     */
    NO_COLLAR(19,"无领");





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

    CollarEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type) {
        for (CollarEnum collarEnum : CollarEnum.values()) {
            if (collarEnum.getType() == type) {
                return collarEnum.getName();
            }
        }
        return null;
    }

    public static Map<String,List<Integer>> getFirsCharMap(){

     Map<String,List<Integer>> map = new HashMap<>(16);
        for (CollarEnum collarEnum : CollarEnum.values()) {
            String s = collarEnum.getName().substring(0,1);
            //判断首字是否有重复的枚举类型
            if(map.containsKey(s)){
                //添加重复元素对应的特征
                List<Integer> list = map.get(s);
                list.add(collarEnum.getType());
                map.put(s,list);
            }else {
                List<Integer> integerList = new ArrayList<>();
                integerList.add(collarEnum.getType());
                map.put(s, integerList);
            }
        }
        return map;
    }

    public static Map<String, Integer> getSecondCharMap(List<Integer> integers) {

        Map<String,Integer> map = new HashMap<>(16);
        for (Integer integer : integers) {
            String name = getNameByType(integer);
            assert name != null;
            if(name.contains("尖")){
                if(name.contains("角")){
                    map.put(getNameByType(integer),integer);
                }else if(name.contains("短")){
                    map.put(getNameByType(integer),integer);
                }else {
                    map.put(getNameByType(integer),integer);
                }
            }else if(name.contains("方")){
                if(name.contains("角")){
                    map.put(getNameByType(integer),integer);
                }else if(name.contains("小")){
                    map.put(getNameByType(integer),integer);
                }else {
                    map.put(getNameByType(integer),integer);
                }
            }else if(name.contains("翼")){
                if(name.contains("双")){
                    map.put(getNameByType(integer),integer);
                }else {
                    map.put(getNameByType(integer),integer);
                }
            }else if(name.contains("双")){
                if(name.contains("层")){
                    map.put(getNameByType(integer),integer);
                }else if(name.contains("翼")){
                    map.put(getNameByType(integer),integer);
                }
            }
        }
        return map;
    }

}
