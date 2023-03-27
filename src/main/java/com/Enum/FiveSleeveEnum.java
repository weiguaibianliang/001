package com.Enum;

public enum FiveSleeveEnum {

    /**
     * 平口五分袖
     */
    FLAT_FIVE_SLEEVE(0,"平口五分袖"),

    /**
     * 叠口五分袖
     */
    FOLD_FIVE_SLEEVE(1,"叠口五分袖");


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

    FiveSleeveEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
