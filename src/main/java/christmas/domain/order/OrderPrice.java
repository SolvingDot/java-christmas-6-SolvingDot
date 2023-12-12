package christmas.domain.order;

import christmas.constants.Menu;
import java.util.Map;

public class OrderPrice {
    private static final int NO_ORDER = 0;

    public int calculateTotalOrderPrice(Map<String, Integer> orderTable) {
        int totalOrderPrice = NO_ORDER;
        for (String menuName : orderTable.keySet()) {
            int price = Menu.getPriceByName(menuName);
            int numberOfMenuItems = orderTable.get(menuName);
            totalOrderPrice += calculateOrderPriceByEachMenu(price, numberOfMenuItems);
        }
        return totalOrderPrice;
    }

    private int calculateOrderPriceByEachMenu(int price, int numberOfMenuItems) {
        return price * numberOfMenuItems;
    }
}
