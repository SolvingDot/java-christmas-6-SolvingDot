package christmas.domain.order;

import christmas.constants.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetails {
    // 메뉴 2개 이상 주문 했을 때(1라도 상관 없음 - test 확인)
    public Map<String, Integer> makeOrderTable(String input) {
        List<String> nameAndNumbers = splitInputToNameAndNumber(input);

        Map<String, Integer> orderTable = new HashMap<>();
        for (String element : nameAndNumbers) {

            List<String> nameAndNumber = splitInputByDashIncludingEmpty(element);
            exceptEmptyPlace(nameAndNumber);

            String menuName = nameAndNumber.get(0);
            checkNameOnTheMenu(menuName);

            String number = nameAndNumber.get(1);
            isOnlyNumber(number);
            int numberOfMenu = Integer.parseInt(number);
            exceptNumberOfMenuLessThanOne(numberOfMenu);

            orderTable.put(menuName, numberOfMenu);
        }
        return orderTable;
    }

    public List<String> splitInputToNameAndNumber(String input) {
        exceptEmpty(input);
        exceptNoDash(input);
        List<String> nameAndNumbers = splitInputByCommaIncludingEmpty(input);
        exceptEmptyPlace(nameAndNumbers);
        return nameAndNumbers;
    }
    
    private void exceptEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("아무 입력이 없음");
        }
    }

    private void exceptNoDash(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException("'-'가 없음");
        }
    }

    private List<String> splitInputByDashIncludingEmpty(String input) {
        return Arrays.asList(input.split("-", -1));
    }

    private void checkNameOnTheMenu(String menuName) {
        if (!isOnTheMenu(menuName)) {
            throw new IllegalArgumentException("메뉴판에 없는 경우 제외");
        }
    }

    private boolean isOnTheMenu(String menuName) {
        return Arrays.stream(Menu.values()).anyMatch(menu -> menu.getName().equals(menuName));
    }

    private void isOnlyNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("숫자가 아닐 경우 제외");
        }
    }

    private boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }

    private void exceptNumberOfMenuLessThanOne(int numberOfMenu) {
        if (numberOfMenu < 1) {
            throw new IllegalArgumentException("1보다 작을 경우 제외");
        }
    }

    private List<String> splitInputByCommaIncludingEmpty(String input) {
        return Arrays.asList(input.split(",", -1));
    }

    private void exceptEmptyPlace(List<String> splitInput) {
        if (containsEmptyPlace(splitInput)) {
            throw new IllegalArgumentException("기호(, or -) 전 또는 후에 아무 입력이 없음");
        }
    }

    private boolean containsEmptyPlace(List<String> splitInput) {
        return splitInput.stream().anyMatch(String::isEmpty);
    }

}
