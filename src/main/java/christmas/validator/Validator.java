package christmas.validator;

import christmas.constants.Menu;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Validator {
    public void ensureNoEmptyInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("아무 입력이 없음");
        }
    }

    public void ensureInputHasDash(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException("'-'가 없음");
        }
    }

    public void ensureNoEmptyPlace(List<String> splitInput) {
        if (containsEmptyPlace(splitInput)) {
            throw new IllegalArgumentException("기호(, or -) 전 또는 후에 아무 입력이 없음");
        }
    }

    private boolean containsEmptyPlace(List<String> splitInput) {
        return splitInput.stream().anyMatch(String::isEmpty);
    }

    public void ensureOnlyNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("숫자가 아닐 경우 제외");
        }
    }

    private boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }

    public void ensureNumberOfMenuMoreThanOne(int numberOfMenu) {
        if (numberOfMenu < 1) {
            throw new IllegalArgumentException("1보다 작을 경우 제외");
        }
    }

    public void ensureNameIsOnTheMenu(String menuName) {
        if (!isOnTheMenu(menuName)) {
            throw new IllegalArgumentException("메뉴판에 없는 경우 제외");
        }
    }

    private boolean isOnTheMenu(String menuName) {
        return Arrays.stream(Menu.values()).anyMatch(menu -> menu.getName().equals(menuName));
    }

    public void ensureNameIsNotDuplicated(String menuName, Map<String, Integer> orderTable) {
        if (orderTable.containsKey(menuName)) {
            throw new IllegalArgumentException("중복된 메뉴 입력은 제외");
        }
    }

    public void ensureTotalNumberOfMenuIsLimitedTwenty(int totalNumberOfMenu) {
        if (totalNumberOfMenu > 20) {
            throw new IllegalArgumentException("메뉴 20개 초과 주문시 예외 발생");
        }
    }

    public void ensureThereAreNotOnlyDrinks(List<String> menuTypes) {
        if (menuTypes.stream().allMatch(type -> type.equals(Menu.ZERO_COLA.getType()))) {
            throw new IllegalArgumentException("음료만 주문할 수 없음");
        }
    }
}
