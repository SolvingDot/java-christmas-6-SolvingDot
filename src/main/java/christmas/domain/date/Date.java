package christmas.domain.date;

import christmas.constants.EventDates;
import christmas.validator.DateValidator;

public class Date {
    private final int date;
    private final DateValidator dateValidator;

    public Date(int date, DateValidator dateValidator) {
        this.dateValidator = dateValidator;
        dateValidator.ensureDateInRange(date);
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
