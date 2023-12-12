package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.constants.Menu;
import christmas.constants.MenuType;

public class Weekend {
    public int checkForDiscount(int date, String menuName, int numberOfMenu) {
        if (isWeekend(date) && isMainDish(menuName)) {
            return calculateDiscountAmount(numberOfMenu);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isWeekend(int date) {
        return EventDates.WEEKEND.contains(date);
    }

    private boolean isMainDish(String menuName) {
        return Menu.getTypeByName(menuName).equals(MenuType.MAIN_DISH.getType());
    }

    private int calculateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKEND_DISCOUNT.getBenefit();
    }
}
