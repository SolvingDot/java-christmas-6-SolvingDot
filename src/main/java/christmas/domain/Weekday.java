package christmas.domain;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;

public class Weekday {
    public int calculateDiscountAmount(int date, String menu, int numberOfMenu) {
        if (isWeekday(date) && isDessert(menu)) {
            return numberOfMenu * DiscountAmount.WEEKDAY.getAmount();
        }
        return DiscountAmount.NO_DISCOUNT.getAmount();
    }

    private boolean isWeekday(int date) {
        return !EventDates.WEEKEND.contains(date);
    }

    private boolean isDessert(String menu) {
        return menu.equals("Dessert");
    }
}
