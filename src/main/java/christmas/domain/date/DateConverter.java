package christmas.domain.date;

import christmas.validator.DateValidator;

public class DateConverter {
    private final DateValidator dateValidator;

    public DateConverter(DateValidator orderValidator) {
        this.dateValidator = orderValidator;
    }

    public int convertToDate(String input) {
        dateValidator.ensureNoEmptyInput(input);
        dateValidator.ensureOnlyNumber(input);
        return Integer.parseInt(input);
    }
}
