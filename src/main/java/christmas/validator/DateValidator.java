package christmas.validator;

import christmas.constants.EventDates;
import christmas.constants.message.ErrorMessage;

public class DateValidator {
    private static final String NUMERIC = "^[0-9]*$";

    public void ensureNoEmptyInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public void ensureOnlyNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    private boolean isNumeric(String input) {
        return input.matches(NUMERIC);
    }

    public void ensureDateInRange(int date) {
        if (date < EventDates.FIRST_DATE || date > EventDates.LAST_DATE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
