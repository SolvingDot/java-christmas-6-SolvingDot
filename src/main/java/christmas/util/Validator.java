package christmas.util;

import christmas.model.Menu;
import christmas.util.Util.Regex;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {
    private static final String DASH = "-";
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");

    public void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public void validateNumeric(String input) {
        if (!NUMBER_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public void validateNumberRange(int number) {
        if (number < Range.MIN_RANGE.value || number > Range.MAX_RANGE.value) {
            throw new IllegalArgumentException();
        }
    }

    public void validateHasDash(String input) {
        if (!input.contains(DASH)) {
            throw new IllegalArgumentException();
        }
    }

    public void validateMenuName(String menu) {
        for (Menu eachMenu : Menu.values()) {
            if (eachMenu.getName().equals(menu)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void validateDuplicatedMenu(Map<String, Integer> orderSheet, String menu) {
        if (orderSheet.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
    }

    public void validateOrderAmount(int number) {
        if (number < Range.MIN_AMOUNT.value || number > Range.MAX_AMOUNT.value) {
            throw new IllegalArgumentException();
        }
    }

    private enum Range{
        MIN_RANGE(1), MAX_RANGE(31),
        MIN_AMOUNT(1), MAX_AMOUNT(20);

        private final int value;

        Range(int value) {
            this.value = value;
        }
    }
}
