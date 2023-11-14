package christmas.domain.order;

import christmas.constants.Menu;
import java.util.Map;

public class OrderCalculator {
    public int calculateTotalOrderAmount(Map<String, Integer> orderTable) {
        int totalOrderAmount = 0;
        for (String menuName : orderTable.keySet()) {
            int price = Menu.getPriceByName(menuName);
            int numberOfMenuItems = orderTable.get(menuName);
            totalOrderAmount += calculateOrderAmountByEachMenu(price, numberOfMenuItems);
        }
        return totalOrderAmount;
    }

    private int calculateOrderAmountByEachMenu(int price, int numberOfMenuItems) {
        return price * numberOfMenuItems;
    }
}
