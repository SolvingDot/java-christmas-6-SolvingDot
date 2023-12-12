package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class Dday {
    private static final int DISCOUNT_INCREASE = 100;
    private static final int TODAY = 1;

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
        return EventBenefits.DDAY_DISCOUNT.getBenefit() + DISCOUNT_INCREASE * (date - TODAY);
    }
}
