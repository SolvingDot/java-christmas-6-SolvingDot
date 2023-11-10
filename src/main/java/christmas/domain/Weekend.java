package christmas.domain;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;

public class Weekend {
    public int checkForDiscount(int date, String menu, int numberOfMenu) {
        if (isWeekend(date) && isMainDish(menu)) {
            return calcuateDiscountAmount(numberOfMenu);
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    private boolean isWeekend(int date) {
        return EventDates.WEEKEND.contains(date);
    }

    private boolean isMainDish(String menu) {
        return menu.equals("MainDish");
    }

    private int calcuateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKEND_DISCOUNT.getBenefit();
    }
}
