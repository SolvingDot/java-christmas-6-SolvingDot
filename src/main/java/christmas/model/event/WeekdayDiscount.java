package christmas.model.event;

import christmas.model.Menu;
import christmas.model.MenuType;
import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventDate;
import java.util.Map;

public class WeekdayDiscount {
    public int apply(int date, Map<String, Integer> orderSheet) {
        // 주말이면 할인 없음
        if (EventDate.WEEKEND.contains(date)) {
            return DiscountAmount.NO_DISCOUNT;
        }
        // 평일이면 디저트 개수만큼 할인
        int numberOfDessert = countDessert(orderSheet);
        return DiscountAmount.WEEKDAY_DISCOUNT * numberOfDessert;
    }

    private static int countDessert(Map<String, Integer> orderSheet) {
        int numberOfDessert = DiscountAmount.NO_DISCOUNT;
        for (String menuName : orderSheet.keySet()) {
            if (Menu.getTypeByName(menuName).equals(MenuType.DESSERT)) {
                numberOfDessert += orderSheet.get(menuName);
            }
        }
        return numberOfDessert;
    }
}
