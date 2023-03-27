package com.Enum;

public enum ChestPouchEnum {

    /**
     * 胸袋上的纽扣得考虑
     */

    /**
     * 左胸圆角明贴袋1个
     */
    LEFT_ROUND_PATCH_1(0,"左胸圆角明贴袋1个"),

    /**
     * 左右胸圆角明贴袋各1个
     */
    LEFT_RIGHT_ROUND_PATCH_1(1,"左右胸圆角明贴袋各1个"),

    /**
     * 左胸直角明贴袋1个
     */
    LEFT_STRAIGHT_PATCH_1(2,"左胸直角明贴袋1个"),

    /**
     * 左右胸直角明贴袋各一个
     */
    LEFT_RIGHT_STRAIGHT_PATCH_1(3,"左右胸直角明贴袋各一个"),

    /**
     * 左胸截角明贴袋1个
     */
    LEFT_SLANT_PATCH_1(4,"左胸截角明贴袋1个"),

    /**
     * 左右胸截角明贴袋各一个
     */
    LEFT_RIGHT_SLANT_PATCH_1(5,"左右胸截角明贴袋各一个"),

    /**
     * 左胸挖袋1个
     */
    LEFT_GOUGE_1(6,"左胸挖袋1个"),

    /**
     * 左右胸挖袋各一个
     */
    LEFT_RIGHT_GOUGE_1(7,"左右胸挖袋各一个"),

    /**
     * 左胸假袋一个
     */
    LEFT_PROSTHETIC_1(8,"左胸假袋一个"),

    /**
     * 左右胸假袋各一个
     */
    LEFT_RIGHT_PROSTHETIC_1(9,"左右胸假袋各一个");



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

    ChestPouchEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

}
