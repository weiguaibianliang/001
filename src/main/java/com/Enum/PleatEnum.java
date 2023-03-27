package com.Enum;

public enum PleatEnum {

    //褶的个数不确定

    /**
     * 袖口有标准褶
     */
    /**
     * 袖口有多褶
     */
    /**
     * 袖口有碎褶
     */
    /**
     * 后背无褶
     */
    /**
     * 后背有双肩褶
     */
    /**
     * 后背有工字褶
     */
    /**
     * 有后腰褶
     */
    /**
     * 褶皱领
     */
    /**
     * 前过肩褶
     */
    /**
     * 领窝褶
     */
    /**
     * 有前腰褶
     */
    /**
     * 有胸褶
     */
    /**
     * 有肚褶
     */
    /**
     * 有胁褶
     */
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

    PleatEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
