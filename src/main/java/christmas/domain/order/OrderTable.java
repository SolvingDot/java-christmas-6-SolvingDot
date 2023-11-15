package christmas.domain.order;

import christmas.constants.Menu;
import christmas.validator.OrderValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderTable {
    private static final String COMMA = ",";
    private static final String DASH = "-";
    private static final int INCLUDE_EMPTY = -1;

    private final OrderValidator orderValidator;

    public OrderTable(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public Map<String, Integer> makeOrderTable(String input) {
        List<String> nameAndNumbers = splitInputToNameAndNumber(input);
        Map<String, Integer> orderTable = new HashMap<>();
        nameAndNumbers.stream()
                .map(this::splitInputByDashIncludingEmpty)
                .map(nameAndNumber -> new Order(nameAndNumber, orderValidator))
                .forEach(order -> {
                    orderValidator.ensureNameIsNotDuplicated(order.getMenuName(), orderTable);
                    orderTable.put(order.getMenuName(), order.getNumberOfMenu());
                });
        validateOrderTable(orderTable);
        return orderTable;
    }

    private List<String> splitInputToNameAndNumber(String input) {
        orderValidator.ensureNoEmptyInput(input);
        orderValidator.ensureInputHasDash(input);
        List<String> nameAndNumbers = splitInputByCommaIncludingEmpty(input);
        orderValidator.ensureNoEmptyPlace(nameAndNumbers);
        return nameAndNumbers;
    }

    private List<String> splitInputByCommaIncludingEmpty(String input) {
        return Arrays.asList(input.split(COMMA, INCLUDE_EMPTY));
    }

    private List<String> splitInputByDashIncludingEmpty(String input) {
        return Arrays.asList(input.split(DASH, INCLUDE_EMPTY));
    }

    private void validateOrderTable(Map<String, Integer> orderTable) {
        orderValidator.ensureThereAreNotOnlyDrinks(readMenuTypes(orderTable));
        orderValidator.ensureTotalNumberOfMenuIsLimitedTwenty(sumNumberOfMenu(orderTable));
    }

    private List<String> readMenuTypes(Map<String, Integer> orderTable) {
        List<String> menuTypes = new ArrayList<>();
        for (String menuName : orderTable.keySet()) {
            menuTypes.add(Menu.getTypeByName(menuName));
        }
        return menuTypes;
    }

    private int sumNumberOfMenu(Map<String, Integer> orderTable) {
        return orderTable.values().stream().mapToInt(Integer::intValue).sum();
    }
}
