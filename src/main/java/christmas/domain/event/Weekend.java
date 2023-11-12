package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class Weekend {
    public int checkForDiscount(int date, String menuName, int numberOfMenu) {
        if (isWeekend(date) && isMainDish(menuName)) {
            return calcuateDiscountAmount(numberOfMenu);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isWeekend(int date) {
        return EventDates.WEEKEND.contains(date);
    }

    private boolean isMainDish(String menuName) {
        return menuName.equals("MainDish");
    }

    private int calcuateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKEND_DISCOUNT.getBenefit();
    }
}
