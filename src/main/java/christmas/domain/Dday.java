package christmas.domain;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;

public class Dday {
    public int calculateDiscountAmount(int date) {
        int discountAmount = DiscountAmount.DDAY.getAmount()
                + DiscountAmount.DDAY_INCREASED.getAmount() * (date - 1);
        return checkDateOverRange(date, discountAmount);
    }

    private int checkDateOverRange(int date, int discountAmout) {
        if (date > EventDates.CHRISTMAS) {
            return 0;
        }
        return discountAmout;
    }
}
