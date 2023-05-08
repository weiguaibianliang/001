package com.Enum;

public enum SpecialProcessEnum {

    /**
     * U形胸挡
     */
    U_CHEST_BLOCK(0,"U形胸挡"),

    /**
     * 装饰袢
     */
    DECORATIVE_TABS(1,"装饰袢"),

    /**
     * 装饰分割
     */
    DECORATION_DIVISION(2,"装饰分割"),

    /**
     * 肩章
     */
    SHOULDER_BADGE(3,"肩章"),

    /**
     * 印花
     */
    PRINTS(4,"印花"),

    /**
     * 反领
     */
    REVERSE_COLLAR(5,"反领"),

    /**
     * 肘部补丁
     */
    ELBOW_PATCH(6,"肘部补丁"),

    /**
     * 花边
     */
    LACE(7,"花边"),

    /**
     * 卷边
     */
    EDGE_CURL(8,"卷边"),

    /**
     * 刺绣
     */
    EMBROIDERY(9,"刺绣"),

    /**
     * 领口袖（指一种用于固定领口形状的小片状配件，通常是由塑料或金属制成，
     * 长度与衬衫领口相当。）
     */
    COLLAR_SLEEVE(10,"领口袖");

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

    SpecialProcessEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
