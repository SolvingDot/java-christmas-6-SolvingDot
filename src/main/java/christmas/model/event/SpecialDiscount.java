package christmas.model.event;

import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventDate;

public class SpecialDiscount {

    public int apply(int date) {
        if (!EventDate.SPECIAL_DAY.contains(date)) {
            return DiscountAmount.NO_DISCOUNT;
        }
        return DiscountAmount.SPECIAL_DISCOUNT;
    }
}
