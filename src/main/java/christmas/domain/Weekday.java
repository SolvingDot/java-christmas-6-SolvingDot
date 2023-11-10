package christmas.domain;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;

public class Weekday {
    public int checkForDiscount(int date, String menu, int numberOfMenu) {
        if (isWeekday(date) && isDessert(menu)) {
            return calcuateDiscountAmount(numberOfMenu);
        }
        return DiscountAmount.NO_DISCOUNT.getAmount();
    }

    private boolean isWeekday(int date) {
        return !EventDates.WEEKEND.contains(date);
    }

    private boolean isDessert(String menu) {
        return menu.equals("Dessert");
    }

    private int calcuateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * DiscountAmount.WEEKDAY.getAmount();
    }
}
