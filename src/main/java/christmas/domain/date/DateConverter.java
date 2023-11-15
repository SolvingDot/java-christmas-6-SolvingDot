package christmas.domain.date;

import christmas.validator.Validator;

public class DateConverter {
    private final Validator validator;

    public DateConverter(Validator validator) {
        this.validator = validator;
    }
    public int convertToDate(String input) {
        validator.ensureNoEmptyInput(input);
        validator.ensureOnlyNumber(input);
        return Integer.parseInt(input);
    }
}
