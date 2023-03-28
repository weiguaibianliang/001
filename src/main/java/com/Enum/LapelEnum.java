package com.Enum;

public enum LapelEnum {

    /**
     * 多少粒扣还不确定
     */

    /**
     * 明门襟
     */
    MING_MEN_LAPEL(0,"明门襟"),

    /**
     * 暗门襟
     */
    DARK_LAPEL(1,"暗门襟"),

    /**
     * 软门襟
     */
    SOFT_LAPEL(2,"软门襟"),

    /**
     * 塔士多门襟（通常胸前有U形胸挡）
     */
    TAS_TO_LAPEL(3,"塔士多门襟");


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

    LapelEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}