package com.Enum;

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
     * 圆下摆不开衩
     */
    ROUND_WITHOUT_HEM(1,"圆下摆不开衩"),

    /**
     * 圆下摆开衩
     */
    ROUND_WITH_HEM(1,"圆下摆开衩");



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
}
