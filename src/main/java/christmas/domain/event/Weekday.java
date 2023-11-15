package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.constants.Menu;

public class Weekday {
    private static final String DESSERT = "디저트";

    public int checkForDiscount(int date, String menuName, int numberOfMenu) {
        if (isWeekday(date) && isDessert(menuName)) {
            return calculateDiscountAmount(numberOfMenu);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isWeekday(int date) {
        return !EventDates.WEEKEND.contains(date);
    }

    private boolean isDessert(String menuName) {
        return Menu.getTypeByName(menuName).equals(DESSERT);
    }

    private int calculateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKDAY_DISCOUNT.getBenefit();
    }
}
