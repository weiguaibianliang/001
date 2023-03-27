package com.Enum;

public enum PartsLibraryEnum {


    /**
     * 领
     */
    COLLAR(0,"领"),

    /**
     * 育克
     */
    OVER_SHOULDER(1,"育克"),

    /**
     * 门襟
     */
    LAPEL(2,"门襟"),


    /**
     * 胸袋
     */
    CHEST_POUCH(3,"胸袋"),


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
    HEM(7,"下摆"),

    /**
     * U形胸挡
     */
    U_CHEST_BLOCK(8,"U形胸挡"),

    /**
     * 长袖
     */
    LONG_SLEEVE(9,"长袖"),

    /**
     * 短袖
     */
    SHORT_SLEEVE(10,"短袖"),

    /**
     * 五分袖
     */
    FIVE_SLEEVE(11,"五分袖"),

    /**
     * 七分袖
     */
    SEVENTH_SLEEVE(12,"七分袖");


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

    PartsLibraryEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
