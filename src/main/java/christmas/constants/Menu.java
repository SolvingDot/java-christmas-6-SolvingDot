package christmas.constants;

public enum Menu {
    // 에피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN_DISH),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN_DISH),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    // 음료
    ZERO_COLA("제로콜라", 3_000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuType.BEVERAGE);

    private static final int NO_ORDER = 0;

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

    private String getType() {
        return type.getType();
    }

    public static int getPriceByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getPrice();
            }
        }
        return NO_ORDER;
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
