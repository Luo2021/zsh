/**
 * 0-蓝色，1-黄色，2-黑色，3-白色，4- 渐变绿色，5- 黄绿双拼色，6- 蓝白渐变色，7- 临时牌照，11-绿色，12-红色
 * @author luoli
 * @date 2021/4/28 20:09
 */
public enum ColorType {
    BLUE(0,"蓝色"),
    YELLOW(1,"黄色"),
    BLACK(2,"黑色"),
    WHITE(3,"白色"),
    GRADIENT_GREEN(4,"渐变绿色"),
    YELLOW_AND_GREEN(5,"黄绿双拼色"),
    BLUE_AND_WHITE(6,"蓝白渐变色"),
    TEMPORARY_LICENCE(7,"临时牌照"),
    GREEN(11,"绿色"),
    RED(12,"红色");

    private final int value;
    private final String type;

    ColorType (int value, String reasonPhrase) {
        this.value = value;
        this.type = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String type() {
        return this.type;
    }

    public static String getTypeByValue (int value) throws IllegalArgumentException {
        for (ColorType colorType: ColorType.values()) {
            if (colorType.value() == value){
                return colorType.type();
            }
        }
        return null;
    }

    public static Integer getValueByType (String type) throws IllegalArgumentException {
        for (ColorType colorType: ColorType.values()) {
            if (colorType.type().equals(type)){
                return colorType.value();
            }
        }
        return null;
    }
}
