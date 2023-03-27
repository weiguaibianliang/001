package com.Enum;

public enum ProvinceEnum {

    //省的个数也不确定

    /**
     * 领省
     */
    COLLAR_PROVINCE(0,"领省"),

    /**
     * 前腰省
     */
    FRONT_WAIST_PROVINCE(1,"前腰省"),

    /**
     * 胁省
     */
    THREATEN_PROVINCE(2,"胁省"),

    /**
     * 横省
     */
    YOKOHAMA_PROVINCE(3,"横省"),

    /**
     * 前肩省
     */
    FRONT_SHOULDER_PROVINCE(4,"前肩省"),

    /**
     * 肚省
     */
    BELLY_PROVINCE(5,"肚省"),

    /**
     * 前身通省
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
    BACK_SAVE_PROVINCE(9,"后腰省");



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
}
