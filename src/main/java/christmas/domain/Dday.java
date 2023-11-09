package christmas.domain;

public class Dday {
    public int calculateDiscountAmount(int date) {
        int discountAmount = 1000 + 100 * (date - 1);
        return checkDateOverRange(date, discountAmount);
    }

    private int checkDateOverRange(int date, int discountAmout) {
        if (date > 25) {
            return 0;
        }
        return discountAmout;
    }
}
