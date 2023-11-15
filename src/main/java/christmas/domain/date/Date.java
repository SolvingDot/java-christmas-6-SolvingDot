package christmas.domain.date;

import christmas.validator.DateValidator;

public class Date {
    private final int date;

    public Date(int date, DateValidator dateValidator) {
        dateValidator.ensureDateInRange(date);
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
