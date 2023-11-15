package christmas.validator;

import christmas.constants.Menu;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderValidator {
    public void ensureNoEmptyInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void ensureInputHasDash(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void ensureNoEmptyPlace(List<String> splitInput) {
        if (containsEmptyPlace(splitInput)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean containsEmptyPlace(List<String> splitInput) {
        return splitInput.stream().anyMatch(String::isEmpty);
    }

    public void ensureOnlyNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }

    public void ensureNumberOfMenuMoreThanOne(int numberOfMenu) {
        if (numberOfMenu < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void ensureNameIsOnTheMenu(String menuName) {
        if (!isOnTheMenu(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isOnTheMenu(String menuName) {
        return Arrays.stream(Menu.values()).anyMatch(menu -> menu.getName().equals(menuName));
    }

    public void ensureNameIsNotDuplicated(String menuName, Map<String, Integer> orderTable) {
        if (orderTable.containsKey(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void ensureTotalNumberOfMenuIsLimitedTwenty(int totalNumberOfMenu) {
        if (totalNumberOfMenu > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }

    public void ensureThereAreNotOnlyDrinks(List<String> menuTypes) {
        if (menuTypes.stream().allMatch(type -> type.equals(Menu.ZERO_COLA.getType()))) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");
        }
    }
}
