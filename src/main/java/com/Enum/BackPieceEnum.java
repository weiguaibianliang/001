package com.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BackPieceEnum {


    /**
     * 直下摆不开衩
     */
    STRAIGHT_WITHOUT_HEM(0,"直下摆不开衩"),

    /**
     * 直下摆开衩
     */
    STRAIGHT_WITH_HEM(1,"直下摆开衩"),

    /**
     * 方下摆不开衩
     */
    SQUARE_WITHOUT_HEM(0,"方下摆不开衩"),

    /**
     * 方下摆开衩
     */
    SQUARE_WITH_HEM(1,"方下摆开衩"),

    /**
     * 圆下摆不开衩
     */
    ROUND_WITHOUT_HEM(2,"圆下摆不开衩"),

    /**
     * 圆下摆开衩
     */
    ROUND_WITH_HEM(3,"圆下摆开衩");



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

    BackPieceEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){
        for (BackPieceEnum backPieceEnum : BackPieceEnum.values()) {
            if(backPieceEnum.getType() == type){
                return backPieceEnum.getName();
            }
        }
        return null;
    }


    //提取首字的Map
    public static Map<String, List<Integer>> getFirstCharMap(){
        Map<String,List<Integer>> map = new HashMap<>(16);
        for (BackPieceEnum backPieceEnum : BackPieceEnum.values()) {
            String s = backPieceEnum.getName().substring(0,1);
            //判断首字是否有重复的枚举类型
            if(map.containsKey(s)){
                //添加重复元素对应的特征
                List<Integer> list = map.get(s);
                list.add(backPieceEnum.getType());
                map.put(s,list);
            }else {
                List<Integer> integerList = new ArrayList<>();
                integerList.add(backPieceEnum.getType());
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
            if(name.contains("不")){
                map.put("不",integer);
            }else {
                map.put("hu",integer);
            }
        }
        return map;
    }
}
