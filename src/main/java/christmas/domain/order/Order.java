package christmas.domain.order;

import christmas.validator.Validator;
import java.util.List;

public class Order {
    private static final int INDEX_OF_NAME = 0;
    private static final int INDEX_OF_NUMBER = 1;

    private final String menuName;
    private final int numberOfMenu;

    public Order(List<String> nameAndNumber, Validator validator) {
        validator.ensureNoEmptyPlace(nameAndNumber);
        this.menuName = nameAndNumber.get(INDEX_OF_NAME);
        validator.ensureNameIsOnTheMenu(menuName);
        this.numberOfMenu = convertNumberOfMenu(nameAndNumber.get(INDEX_OF_NUMBER), validator);
    }

    private int convertNumberOfMenu(String number, Validator validator) {
        validator.ensureOnlyNumber(number);
        int numberOfMenu = Integer.parseInt(number);
        validator.ensureNumberOfMenuMoreThanOne(numberOfMenu);
        return numberOfMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getNumberOfMenu() {
        return numberOfMenu;
    }
}
