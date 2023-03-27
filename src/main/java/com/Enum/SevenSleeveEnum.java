package com.Enum;

public enum SevenSleeveEnum {

    /**
     * 平口七分袖
     */
    FLAT_SEVENTH_SLEEVE(0,"平口七分袖"),

    /**
     * 叠口七分袖
     */
    FOLD_SEVENTH_SLEEVE(1,"叠口七分袖");


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

    SevenSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
