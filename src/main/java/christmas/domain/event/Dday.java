package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class Dday {
    public int checkForDiscount(int date) {
        if (isInDdayRange(date)) {
            return calculateDiscountAmount(date);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isInDdayRange(int date) {
        return !(date > EventDates.CHRISTMAS);
    }

    private int calculateDiscountAmount(int date) {
        return EventBenefits.DDAY_DISCOUNT.getBenefit()
                + EventBenefits.DDAY_DISCOUNT_INCREASE.getBenefit() * (date - 1);
    }
}
