package com.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PleatEnum {


    /**
     * 袖口标准褶
     */
    STANDARD_PLEAT_CUFF(0,"袖口标准褶"),

    /**
     * 袖口多褶
     */
    MULTI_PLEAT_CUFF(1,"袖口多褶"),

    /**
     * 袖口碎褶
     */
    CRUSH_PLEAT_CUFF(2,"袖口碎褶"),

//    /**
//     * 后背无褶
//     */
//    NO_PLEAT_BACK(3,"后背无褶"),

    /**
     * 后背双肩褶
     */
    DOUBLE_SHOULDER_PLEAT_BACK(4,"后背双肩褶"),

    /**
     * 后背工字褶
     */
    I_BEAM_PLEAT_BACK(5,"后背工字褶"),

    /**
     * 后腰褶
     */
    WAIST_PLEAT_BACK(6,"后腰褶"),

    /**
     * 褶皱领
     */
    COLLAR_PLEAT(7,"褶皱领"),

    /**
     * 前过肩褶
     */
    FRONT_OVER_SHOULDER_PLEAT(8,"前过肩褶"),
    
    /**
     * 领窝褶
     */
    COLLAR_NEST_PLEAT(9,"领窝褶"),

    /**
     * 前腰褶
     */
    WAIST_PLEAT_FRONT(10,"前腰褶"),

    /**
     * 胸褶
     */
    BREAST_PLEAT(11,"胸褶"),

    /**
     * 肚褶
     */
    BELLY_PLEAT(12,"肚褶"),

    /**
     * 胁褶/腋下褶
     */
    COERCIVE_PLEAT(13,"胁褶");




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

    PleatEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type){
        for (PleatEnum pleatEnum : PleatEnum.values()) {
            if(pleatEnum.getType() == type){
                return pleatEnum.getName();
            }
        }
        return null;
    }

    //以数字分析法建立哈希函数（特殊关键字）

    public static Map<String, List<Integer>> getMapByVitalRepeatChar(){
        Map<String,List<Integer>> map = new HashMap<>(16);
        //袖
        List<Integer> sleeveList = new ArrayList<>();
        sleeveList.add(0);
        sleeveList.add(1);
        sleeveList.add(2);
        //后
        List<Integer> backList = new ArrayList<>();
        backList.add(3);
//        backList.add(4);
        backList.add(5);
        backList.add(6);
        //前
        List<Integer> frontList = new ArrayList<>();
        frontList.add(8);
        frontList.add(10);
        map.put("袖",sleeveList);
        map.put("后",backList);
        map.put("前",frontList);
        return map;
    }

    //重复关键字map,再进行一次筛选判断
    public static Map<String,Integer> getMapByQueryVital(List<Integer> list){

        Map<String,Integer> map = new HashMap<>(16);
        for (Integer integer : list) {
            String name = getNameByType(integer);
            assert name != null;
            if(name.contains("袖")){
                if(name.contains("标")){
                    map.put("标",0);
                }
                if(name.contains("多")){
                    map.put("多",1);
                }else {
                    map.put("碎",2);
                }
            }
            if(name.contains("后")){
                if(name.contains("双")){
                    map.put("双",5);
                }
                if(name.contains("工")){
                    map.put("工",6);
                }
            }
            if(name.contains("前")){
                if(name.contains("肩")){
                    map.put("肩",8);
                }
                if(name.contains("腰")){
                    map.put("腰",10);
                }
            }
        }
        return map;
    }


    public static Map<String,Integer> getMapByVitalChar(){
        //特殊关键字组成的map
        Map<String,Integer> integerMap = new HashMap<>(16);
        // 7/9/11/12/13
        integerMap.put("皱",7);
        integerMap.put("窝",9);
        integerMap.put("胸",11);
        integerMap.put("肚",12);
        integerMap.put("胁",13);
        return integerMap;

    }

}
