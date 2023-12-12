package christmas.util;

import java.util.regex.Pattern;

public class Validator {
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

    private enum Range{
        MIN_RANGE(1), MAX_RANGE(31);

        private final int value;

        Range(int value) {
            this.value = value;
        }
    }
}
