package christmas.domain.order;

import christmas.validator.OrderValidator;
import java.util.List;

public class Order {
    private static final int INDEX_OF_NAME = 0;
    private static final int INDEX_OF_NUMBER = 1;

    private final String menuName;
    private final int numberOfMenu;

    public Order(List<String> nameAndNumber, OrderValidator orderValidator) {
        orderValidator.ensureNoEmptyPlace(nameAndNumber);
        this.menuName = nameAndNumber.get(INDEX_OF_NAME);
        orderValidator.ensureNameIsOnTheMenu(menuName);
        this.numberOfMenu = convertNumberOfMenu(nameAndNumber.get(INDEX_OF_NUMBER), orderValidator);
    }

    private int convertNumberOfMenu(String number, OrderValidator orderValidator) {
        orderValidator.ensureOnlyNumber(number);
        int numberOfMenu = Integer.parseInt(number);
        orderValidator.ensureNumberOfMenuMoreThanOne(numberOfMenu);
        return numberOfMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getNumberOfMenu() {
        return numberOfMenu;
    }
}
