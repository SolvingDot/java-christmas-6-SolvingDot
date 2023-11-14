package christmas.constants;

import java.util.List;

public enum Menu {
    // 에피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, "에피타이저"),
    TAPAS("타파스", 5_500, "에피타이저"),
    CAESAR_SALAD("시저샐러드", 8_000, "에피타이저"),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, "메인"),
    BBQ_RIBS("바비큐립", 54_000, "메인"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "메인"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "메인"),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, "디저트"),
    ICE_CREAM("아이스크림", 5_000, "디저트"),

    // 음료
    ZERO_COLA("제로콜라", 3_000, "음료"),
    RED_WINE("레드와인", 60_000, "음료"),
    CHAMPAGNE("샴페인", 25_000, "음료");

    private final String name;
    private final int price;
    private final String type;

    Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public static int getPriceByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getPrice();
            }
        }
        return 0;
    }

    public static String getTypeByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getType();
            }
        }
        return null;
    }
}
