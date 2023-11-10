package christmas.domain;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;

public class Dday {
    public int checkForDiscount(int date) {
        if (isInDdayRange(date)) {
            return calcuateDiscountAmount(date);
        }
        return DiscountAmount.NO_DISCOUNT.getAmount();
    }

    private boolean isInDdayRange(int date) {
        return !(date > EventDates.CHRISTMAS);
    }

    private int calcuateDiscountAmount(int date) {
        return DiscountAmount.DDAY.getAmount() + DiscountAmount.DDAY_INCREASED.getAmount() * (date - 1);
    }
}
