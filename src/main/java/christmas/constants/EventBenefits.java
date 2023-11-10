package christmas.constants;

public enum EventBenefits {
    NOTHING(0),
    DDAY_DISCOUNT(1000),
    DDAY_DISCOUNT_INCREASE(100),
    WEEKDAY_DISCOUNT(2023),
    WEEKEND_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    FREE_CHAMPAGNE(1);

    private final int benefit;

    EventBenefits(int benefit) {
        this.benefit = benefit;
    }

    public int getBenefit() {
        return benefit;
    }

}
