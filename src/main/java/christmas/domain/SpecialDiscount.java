package christmas.domain;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class SpecialDiscount {
    public int checkForDiscount(int date) {
        if (hasStarInCalender(date)) {
            return EventBenefits.SPECIAL_DISCOUNT.getBenefit();
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean hasStarInCalender(int date) {
        return EventDates.SPECIAL.contains(date);
    }
}
