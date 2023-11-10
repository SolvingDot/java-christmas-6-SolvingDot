package christmas.domain;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class Dday {
    public int checkForDiscount(int date) {
        if (isInDdayRange(date)) {
            return calcuateDiscountAmount(date);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isInDdayRange(int date) {
        return !(date > EventDates.CHRISTMAS);
    }

    private int calcuateDiscountAmount(int date) {
        return EventBenefits.DDAY_DISCOUNT.getBenefit() + EventBenefits.DDAY_DISCOUNT_INCREASE.getBenefit() * (date - 1);
    }
}
