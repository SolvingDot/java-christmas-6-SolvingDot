package christmas.validator;

import christmas.constants.Menu;
import christmas.constants.MenuType;
import christmas.constants.message.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderValidator {
    private static final int MINIMUM_ORDER = 1;
    private static final int MAXIMUM_ORDER = 20;
    private static final String DASH = "-";
    private static final String NUMERIC = "^[0-9]*$";

    public void ensureNoEmptyInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public void ensureInputHasDash(String input) {
        if (!input.contains(DASH)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public void ensureNoEmptyPlace(List<String> splitInput) {
        if (containsEmptyPlace(splitInput)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean containsEmptyPlace(List<String> splitInput) {
        return splitInput.stream().anyMatch(String::isEmpty);
    }

    public void ensureOnlyNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean isNumeric(String input) {
        return input.matches(NUMERIC);
    }

    public void ensureNumberOfMenuMoreThanOne(int numberOfMenu) {
        if (numberOfMenu < MINIMUM_ORDER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public void ensureNameIsOnTheMenu(String menuName) {
        if (!isOnTheMenu(menuName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean isOnTheMenu(String menuName) {
        return Arrays.stream(Menu.values()).anyMatch(menu -> menu.getName().equals(menuName));
    }

    public void ensureNameIsNotDuplicated(String menuName, Map<String, Integer> orderTable) {
        if (orderTable.containsKey(menuName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public void ensureTotalNumberOfMenuIsLimitedTwenty(int totalNumberOfMenu) {
        if (totalNumberOfMenu > MAXIMUM_ORDER) {
            throw new IllegalArgumentException(ErrorMessage.MAXIMUM_ORDER.getMessage());
        }
    }

    public void ensureThereAreNotOnlyDrinks(List<String> menuTypes) {
        if (menuTypes.stream().allMatch(type -> type.equals(MenuType.DESSERT.getType()))) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINKS.getMessage());
        }
    }
}
