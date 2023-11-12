package christmas.domain;

import christmas.constants.Menu;
import java.util.Map;

public class OrderCalculator {
    public int calculateTotalOrderAmount(Map<String, Integer> orderDetails) {
        int totalOrderAmount = 0;
        for (String menuName : orderDetails.keySet()) {
            int price = readPriceOfMenu(menuName);
            int numberOfMenuItems = orderDetails.get(menuName);
            totalOrderAmount += calculateOrderAmountByEachMenu(price, numberOfMenuItems);
        }
        return totalOrderAmount;
    }

    private int readPriceOfMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getPrice();
            }
        }
        throw new IllegalArgumentException("메뉴판에 없는 메뉴입니다.");
    }

    private int calculateOrderAmountByEachMenu(int price, int numberOfMenuItems) {
        return price * numberOfMenuItems;
    }
}
