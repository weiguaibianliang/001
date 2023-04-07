package com.Enum;

import java.util.*;
import java.util.stream.Collectors;

public enum ChestPouchEnum {

    /**
     * 胸袋上的纽扣得考虑
     */

    /**
     * 左胸圆角明贴袋1个
     */
    LEFT_ROUND_PATCH_1(0,"左胸圆角明贴袋1个"),

    /**
     * 左右胸圆角明贴袋各1个
     */
    LEFT_RIGHT_ROUND_PATCH_1(1,"左右胸圆角明贴袋各1个"),

    /**
     * 左胸直角明贴袋1个
     */
    LEFT_STRAIGHT_PATCH_1(2,"左胸直角明贴袋1个"),

    /**
     * 左右胸直角明贴袋各一个
     */
    LEFT_RIGHT_STRAIGHT_PATCH_1(3,"左右胸直角明贴袋各1个"),

    /**
     * 左胸截角明贴袋1个(切角)
     */
    LEFT_SLANT_PATCH_1(4,"左胸截角明贴袋1个"),

    /**
     * 左右胸截角明贴袋各一个（切角）
     */
    LEFT_RIGHT_SLANT_PATCH_1(5,"左右胸截角明贴袋各1个"),

    /**
     * 左胸挖袋1个
     */
    LEFT_GOUGE_1(6,"左胸挖袋1个"),

    /**
     * 左右胸挖袋各一个
     */
    LEFT_RIGHT_GOUGE_1(7,"左右胸挖袋各1个"),

    /**
     * 左胸假袋一个
     */
    LEFT_PROSTHETIC_1(8,"左胸假袋1个"),

    /**
     * 左右胸假袋各一个
     */
    LEFT_RIGHT_PROSTHETIC_1(9,"左右胸假袋各1个");



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

    ChestPouchEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){
        for (ChestPouchEnum chestPouchEnum : ChestPouchEnum.values()) {
            if(chestPouchEnum.getType() == type){
                return chestPouchEnum.getName();
            }
        }
        return null;
    }

    public static Map<String, List<Integer>> getVitalMap(){

        Map<String,List<Integer>> map = new HashMap<>(16);
        List<Integer> list = getChestPouchEnumName();
        List<Integer> list1 = list.stream().filter(s -> Objects.requireNonNull(getNameByType(s)).contains("圆角")).collect(Collectors.toList());
        List<Integer> list2 = list.stream().filter(s -> Objects.requireNonNull(getNameByType(s)).contains("截角")).collect(Collectors.toList());
        List<Integer> list3 = list.stream().filter(s -> Objects.requireNonNull(getNameByType(s)).contains("直角")).collect(Collectors.toList());
        List<Integer> list4 = list.stream().filter(s -> Objects.requireNonNull(getNameByType(s)).contains("挖")).collect(Collectors.toList());
        List<Integer> list5 = list.stream().filter(s -> Objects.requireNonNull(getNameByType(s)).contains("假")).collect(Collectors.toList());
        map.put("圆角",list1);
        map.put("截角",list2);
        map.put("直角",list3);
        map.put("挖",list4);
        map.put("假",list5);
        return map;
    }

    public static List<Integer> getChestPouchEnumName() {
        List<Integer> list = new ArrayList<>();
        for (ChestPouchEnum chestPouchEnum : ChestPouchEnum.values()) {
            list.add(chestPouchEnum.getType());
        }
        return list;
    }

    public static Map<String, Integer> getSpecialMap(List<Integer> integers) {

        Map<String,Integer> integerMap = new HashMap<>(16);
        for (Integer integer : integers) {
            String name = getNameByType(integer);
            assert name != null;
            if(name.contains("右")){
                integerMap.put("右",integer);
            }else {
                integerMap.put("hu",integer);
            }
        }
        return integerMap;
    }

}
