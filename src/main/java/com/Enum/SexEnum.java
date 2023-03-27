package com.Enum;

public enum SexEnum {

    /**
     * 男
     */
    MALE(0,"男"),

    /**
     * 女
     */
    FEMALE(1,"女");


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

    SexEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
