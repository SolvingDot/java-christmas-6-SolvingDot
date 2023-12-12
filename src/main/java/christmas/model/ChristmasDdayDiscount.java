package christmas.model;

public class ChristmasDdayDiscount {
    private static final int CHRISTMAS = 25;
    private static final int NO_DISCOUNT = 0;
    private static final int BASIC_DISCOUNT = 1000;
    private static final int AMOUNT_INCREASED = 100;
    private static final int TODAY = 1;

    public int apply(int date) {
        if (date > CHRISTMAS) {
            return NO_DISCOUNT;
        }
        int discount = BASIC_DISCOUNT + AMOUNT_INCREASED * (date - TODAY);
        return discount;
    }
}
