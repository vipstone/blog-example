import java.util.EnumMap;


public class EnumTest {

    public static void main(String[] args) {

//        ErrorCodeEnum errorCode = ErrorCodeEnum.SUCCESS;
//        System.out.println("状态码：" + errorCode.code() + " 状态信息：" + errorCode.msg());

//        ColorEnum colorEnum = ColorEnum.RED;
//        colorEnum.print();
//        System.out.println("颜色：" + colorEnum.getInfo());


//        // 赋值第一个枚举类
//        ColorInterface colorEnum = ColorInterface.ColorEnum.RED;
//        System.out.println(colorEnum);
//        // 赋值第二个枚举类
//        colorEnum = ColorInterface.NewColorEnum.NEW_RED;
//        System.out.println(colorEnum);

//        for (ColorEnum colorEnum : ColorEnum.values()) {
//            System.out.println(colorEnum.getInfo());
//        }

//        List<ColorEnum> list = new ArrayList<ColorEnum>();
//        list.add(ColorEnum.RED);
//        list.add(ColorEnum.RED);//重复元素
//        list.add(ColorEnum.YELLOW);
//        list.add(ColorEnum.GREEN);
//        // 去掉重复数据
//        EnumSet<ColorEnum> enumSet = EnumSet.copyOf(list);
//        System.out.println(enumSet);
//
//        // 获取指定范围的枚举（获取所有的失败状态）
//        EnumSet<ErrorCodeEnum> errorCodeEnums = EnumSet.range(ErrorCodeEnum.ERROR, ErrorCodeEnum.UNKNOWN_ERROR);
//        System.out.println("所有失败状态：" + errorCodeEnums);

        EnumMap<ColorEnum, String> enumMap = new EnumMap<>(ColorEnum.class);
        enumMap.put(ColorEnum.RED, "红色");
        enumMap.put(ColorEnum.GREEN, "绿色");
        enumMap.put(ColorEnum.BLANK, "白色");
        enumMap.put(ColorEnum.YELLOW, "黄色");
        System.out.println(ColorEnum.RED + ":" + enumMap.get(ColorEnum.RED));


    }
}

enum ColorEnum {
    RED, GREEN, BLANK, YELLOW;
//    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
//    private String name;
//    private int index;
//
//    private ColorEnum(String name, int index) {
//        this.name = name;
//        this.index = index;
//    }
}

enum ErrorCodeEnum {
    SUCCESS(1000, "success"),
    ERROR(2001, "parameter error"),
    SYS_ERROR(2002, "system error"),
    NAMESPACE_NOT_FOUND(2003, "namespace not found"),
    NODE_NOT_EXIST(3002, "node not exist"),
    NODE_ALREADY_EXIST(3003, "node already exist"),
    UNKNOWN_ERROR(9999, "unknown error");

    private int code;
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    public static ErrorCodeEnum getErrorCode(int code) {
        for (ErrorCodeEnum it : ErrorCodeEnum.values()) {
            if (it.code() == code) {
                return it;
            }
        }
        return UNKNOWN_ERROR;
    }
}

interface ColorInterface {
    enum ColorEnum implements ColorInterface {
        GREEN, YELLOW, RED
    }

    enum NewColorEnum implements ColorInterface {
        NEW_GREEN, NEW_YELLOW, RED
    }
}


interface Behaviour {
    void print();

    String getInfo();
}


//enum ColorEnum {
//    GREEN, YELLOW, RED
//}

class ColorTest {
    ColorEnum color = ColorEnum.RED;

    public void change() {
        switch (color) {
            case RED:
                color = ColorEnum.GREEN;
                break;
            case YELLOW:
                color = ColorEnum.RED;
                break;
            case GREEN:
                color = ColorEnum.YELLOW;
                break;
        }
    }
}