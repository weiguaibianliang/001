package com.Enum;

public enum CollarEnum {

    /**
     * 领子上的纽扣得考虑
     */


    /**
     * 标准
     */
    STANDARD(0,"标准领"),

    /**
     * 立领
     */
    STAND_UP_COLLAR(1,"立领"),

    /**
     * 小方领
     */
    SMALL_SQUARE_COLLAR(2,"小方领"),

    /**
     * 温莎领（温莎一字领，温莎八字领）
     */
    WINDSOR_COLLAR(3,"温莎领"),

    /**
     * 纽扣领
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
    CUBAN_COLLAR(11,"古巴领");


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
}
