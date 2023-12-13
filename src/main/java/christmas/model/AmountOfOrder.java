package christmas.model;

import java.util.Map;

public class AmountOfOrder {
    public int calculate(Map<String, Integer> orderSheet) {
        int totalAmount = 0;
        for (String menuName : orderSheet.keySet()) {
            totalAmount += Menu.getPriceByName(menuName) * orderSheet.get(menuName);
        }
        return totalAmount;
    }
}
