package christmas.model;

import java.util.Arrays;
import java.util.Map;

public class AmountOfOrder {
    public int calculate(Map<String, Integer> orderSheet) {
        int totalAmount = 0;
        for (String menuName : orderSheet.keySet()) {
            totalAmount += getPriceByName(menuName) * orderSheet.get(menuName);
        }
        return totalAmount;
    }

    private int getPriceByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .map(Menu::getPrice)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
