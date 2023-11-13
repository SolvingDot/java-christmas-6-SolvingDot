package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.constants.Menu;

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
        return readTypeOfMenu(menuName).equals("디저트");
    }

    private String readTypeOfMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getType();
            }
        }
        throw new IllegalArgumentException("메뉴판에 없는 메뉴입니다.");
    }

    private int calculateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKDAY_DISCOUNT.getBenefit();
    }
}
