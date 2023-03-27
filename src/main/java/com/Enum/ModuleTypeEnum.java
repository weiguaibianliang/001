package com.Enum;

public enum ModuleTypeEnum {

    /**
     * 基本模块
     */
    BASIC_MODULE(0,"基本模块"),

    /**
     * 领模块
     */
    COLLAR_MODULE(1,"领模块"),

    /**
     * 袖身袖衩模块
     */
    SLEEVE_BODY_MODULE(2,"袖身袖衩模块"),

    /**
     * 胸袋模块
     */
    CHEST_POUCH_MODULE(3,"胸袋模块"),

    /**
     * 袖克夫模块
     */
    SLEEVE_CUFF_MODULE(4,"袖克夫模块"),

    /**
     * 收尾模块
     */
    END_MODULE(5,"收尾模块");
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

    ModuleTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

}
