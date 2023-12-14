package christmas.model.event;

import christmas.model.Menu;
import christmas.model.MenuType;
import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventDate;
import java.util.Map;

public class WeekendDiscount {
    public int apply(int date, Map<String, Integer> orderSheet) {
        // 주말이 아니면 할인 없음
        if (!EventDate.WEEKEND.contains(date)) {
            return DiscountAmount.NO_DISCOUNT;
        }
        // 주말이면 메인 요리 개수만큼 할인
        int numberOfMain = countMainCourse(orderSheet);
        return DiscountAmount.WEEKEND_DISCOUNT * numberOfMain;
    }

    private static int countMainCourse(Map<String, Integer> orderSheet) {
        int numberOfMain = DiscountAmount.NO_DISCOUNT;
        for (String menuName : orderSheet.keySet()) {
            if (Menu.getTypeByName(menuName).equals(MenuType.MAIN_COURSE)) {
                numberOfMain += orderSheet.get(menuName);
            }
        }
        return numberOfMain;
    }
}
