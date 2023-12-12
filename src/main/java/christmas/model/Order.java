package christmas.model;

import christmas.util.Util;
import christmas.util.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private static final int INDEX_OF_MENU = 0;
    private static final int INDEX_OF_NUMBER = 1;

    private final Validator validator = new Validator();

    public Map<String, Integer> read(String inputOrder) {
        validator.validateEmpty(inputOrder);
        validator.validateHasDash(inputOrder);
        List<String> eachOrder = splitOrdersByComma(inputOrder);
        return makeOrderSheet(eachOrder);
    }

    private List<String> splitOrdersByComma(String inputOrder) {
        List<String> eachOrder = Util.splitByComma(inputOrder);
        eachOrder.forEach(validator::validateEmpty);
        return eachOrder;
    }

    private Map<String, Integer> makeOrderSheet(List<String> eachOrder) {
        Map<String, Integer> orderSheet = new HashMap<>();
        for (String order : eachOrder) {
            List<String> menuAndNumber = Util.splitByDash(order);
            rewriteOrdersToOrderSheet(menuAndNumber, orderSheet);
        }
        return orderSheet;
    }

    private void rewriteOrdersToOrderSheet(List<String> menuAndNumber, Map<String, Integer> orderSheet) {
        String menu = menuAndNumber.get(INDEX_OF_MENU);
        String number = menuAndNumber.get(INDEX_OF_NUMBER);
        validateMenuName(orderSheet, menu);
        int amount = readAmountOfMenu(number);
        orderSheet.put(menu, amount);
    }

    public void validateMenuName(Map<String, Integer> orderSheet, String menu) {
        validator.validateEmpty(menu);
        validator.validateMenuName(menu);
        validator.validateDuplicatedMenu(orderSheet, menu);
    }

    private int readAmountOfMenu(String number) {
        validator.validateEmpty(number);
        validator.validateNumeric(number);
        int amount = Integer.parseInt(number);
        validator.validateOrderAmount(amount);
        return amount;
    }
}
