package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.constants.Menu;
import christmas.constants.MenuType;

public class Weekday {
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
        return Menu.getTypeByName(menuName).equals(MenuType.DESSERT.getType());
    }

    private int calculateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKDAY_DISCOUNT.getBenefit();
    }
}
