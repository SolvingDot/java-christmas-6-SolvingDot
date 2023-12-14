package christmas.model;

import christmas.util.Validator;

public class Date {
    private final Validator validator = new Validator();

    public int read(String inputDate) {
        validateInputDate(inputDate);
        int date = convertToDate(inputDate);
        validateDateRange(date);
        return date;
    }

    private void validateInputDate(String inputDate) {
        validator.validateEmpty(inputDate);
        validator.validateNumeric(inputDate);
    }

    private int convertToDate(String inputDate) {
        return Integer.parseInt(inputDate);
    }

    private void validateDateRange(int date) {
        validator.validateNumberRange(date);
    }
}
