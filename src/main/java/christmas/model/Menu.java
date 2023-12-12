package christmas.model;

import java.util.Arrays;

public enum Menu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN_COURSE),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN_COURSE),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN_COURSE),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN_COURSE),

    // 디저트
    CHOCO_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    // 음료
    ZERO_COLA("제로콜라", 3_000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuType.BEVERAGE);

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
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

    public MenuType getType() {
        return type;
    }
}
