package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class Weekday {
    public int checkForDiscount(int date, String menuName, int numberOfMenu) {
        if (isWeekday(date) && isDessert(menuName)) {
            return calcuateDiscountAmount(numberOfMenu);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isWeekday(int date) {
        return !EventDates.WEEKEND.contains(date);
    }

    private boolean isDessert(String menuName) {
        return menuName.equals("Dessert");
    }

    private int calcuateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKDAY_DISCOUNT.getBenefit();
    }
}
