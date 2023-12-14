package christmas.model.event;

import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventDate;

public class ChristmasDdayDiscount {
    private static final int AMOUNT_INCREASED = 100;

    public int apply(int date) {
        if (date > EventDate.CHRISTMAS) {
            return DiscountAmount.NO_DISCOUNT;
        }
        int discount = DiscountAmount.CHRISTMAS_BASIC + AMOUNT_INCREASED * (date - EventDate.FIRST_DAY);
        return discount;
    }
}
