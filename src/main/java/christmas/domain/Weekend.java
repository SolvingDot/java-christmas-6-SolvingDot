package christmas.domain;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;

public class Weekend {
    public int calculateDiscountAmount(int date, String menu, int numberOfMenu) {
        if (isWeekend(date) && isMainDish(menu)) {
            return numberOfMenu * DiscountAmount.WEEKEND.getAmount();
        }
        return DiscountAmount.NO_DISCOUNT.getAmount();
    }

    private boolean isWeekend(int date) {
        return EventDates.WEEKEND.contains(date);
    }

    private boolean isMainDish(String menu) {
        return menu.equals("MainDish");
    }
}
