package christmas.model.event;

public class SpecialDiscount {
    private static final int NO_DISCOUNT = 0;
    private static final int DISCOUNT_AMOUNT = 1000;

    public int apply(int date) {
        if (!EventDate.SPECIAL_DAY.contains(date)) {
            return NO_DISCOUNT;
        }
        return DISCOUNT_AMOUNT;
    }
}
