package christmas.domain;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;

public class SpecialDiscount {
    public int checkForDiscount(int date) {
        if (hasStarInCalender(date)) {
            return DiscountAmount.SPECIAL.getAmount();
        }
        return DiscountAmount.NO_DISCOUNT.getAmount();
    }

    private boolean hasStarInCalender(int date) {
        return EventDates.SPECIAL.contains(date);
    }
}
