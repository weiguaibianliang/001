package com.Enum;

public enum FrontPieceEnum {

    /**
     * 标准
     */
    STANDARD(0,"标准");

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

    FrontPieceEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
