package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.constants.Menu;

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
        return readTypeOfMenu(menuName).equals("메인");
    }

    private String readTypeOfMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getType();
            }
        }
        throw new IllegalArgumentException("메뉴판에 없는 메뉴입니다.");
    }

    private int calcuateDiscountAmount(int numberOfMenu) {
        return numberOfMenu * EventBenefits.WEEKEND_DISCOUNT.getBenefit();
    }
}
