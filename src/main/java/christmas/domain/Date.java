package christmas.domain;

import christmas.constants.EventDates;

public class Date {
    private final int date;

    public Date(int date) {
        checkRange(date);
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    private void checkRange(int date) {
        if (date < EventDates.FIRST_DATE || date > EventDates.LAST_DATE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
