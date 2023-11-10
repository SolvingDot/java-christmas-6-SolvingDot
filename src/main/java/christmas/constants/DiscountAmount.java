package christmas.constants;

public enum DiscountAmount {
    NO_DISCOUNT(0),
    DDAY(1000),
    DDAY_INCREASED(100),
    WEEKDAY(2023),
    WEEKEND(2023),
    STAR(1000);

    private final int amount;

    DiscountAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

}
