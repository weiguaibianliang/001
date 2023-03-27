package com.Enum;

public enum ShortSleeveEnum {

    /**
     * 平口短袖
     */
    FLAT_SHORT_SLEEVE(0,"平口短袖"),

    /**
     * 叠口短袖
     */
    FOLD_SHORT_SLEEVE(1,"叠口短袖");




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

    ShortSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
