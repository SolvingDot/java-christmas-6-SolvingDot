package christmas.validator;

import christmas.constants.EventDates;

public class DateValidator {
    public void ensureNoEmptyInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void ensureOnlyNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }

    public void ensureDateInRange(int date) {
        if (date < EventDates.FIRST_DATE || date > EventDates.LAST_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
