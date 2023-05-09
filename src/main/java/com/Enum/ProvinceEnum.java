package com.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ProvinceEnum {

    //省的个数也不确定

    /**
     * 前领省
     */
    COLLAR_PROVINCE(0,"前领省"),

    /**
     * 前腰省
     */
    FRONT_WAIST_PROVINCE(1,"前腰省"),

    /**
     * 胁省（腋下省）
     */
    THREATEN_PROVINCE(2,"胁省"),

    /**
     * 横省（指腋下摆缝处至胸部的省道）
     */
    YOKOHAMA_PROVINCE(3,"横省"),

    /**
     * 前肩省
     */
    FRONT_SHOULDER_PROVINCE(4,"前肩省"),

    /**
     * 肚省（大袋口部位的省道）
     */
    BELLY_PROVINCE(5,"肚省"),

    /**
     * 前身通省（从肩缝到下摆的开刀缝）
     */
    FORMERLY_TONG_PROVINCE(6,"前身通省"),

    /**
     * 后领省
     */
    POST_COLLAR_PROVINCE(7,"后领省"),

    /**
     * 后肩省
     */
    REAR_SHOULDER_PROVINCE(8,"后肩省"),

    /**
     * 后腰省
     */
    BACK_SAVE_PROVINCE(9,"后腰省"),

    /**
     * 前胸省
     */
    FRONT_CHEST_PROVINCE(10,"前胸省");



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

    ProvinceEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){
        for (ProvinceEnum provinceEnum : ProvinceEnum.values()) {
            if(provinceEnum.getType() == type){
                return provinceEnum.getName();
            }
        }
        return null;
    }

    /*
    怎样解决腋下省和胁省的同义互换
    key不同，value值可以相同
     */

    public static Map<String, List<Integer>> getFirsCharMap(){

        Map<String,List<Integer>> map = new HashMap<>(16);
        for (ProvinceEnum provinceEnum : ProvinceEnum.values()) {
            String str = provinceEnum.name.substring(0,1);
            //判断首字是否有重复的枚举类型
            if(map.containsKey(str)){
                //添加重复元素对应的特征
                List<Integer> list = map.get(str);
                list.add(provinceEnum.getType());
                map.put(str,list);
            }else {
                //腋下省和胁省
                List<Integer> integerList = new ArrayList<>();
                integerList.add(provinceEnum.getType());
                map.put(str, integerList);
            }
        }
        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        map.put("腋",integers);
        return map;
    }

    public static Map<String, Integer> getSecondCharMap(List<Integer> integers) {

        Map<String,Integer> map = new HashMap<>(16);
        for (Integer integer : integers) {
            String name = getNameByType(integer);
            assert name != null;
            map.put(name.substring(1,2),integer);
        }
        return map;
    }





}
