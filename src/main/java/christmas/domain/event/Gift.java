package christmas.domain.event;

import christmas.constants.EventBenefits;

public class Gift {
    private static final int GIFT_CONDITION = 120_000;

    public int checkForGift(int totalOrderAmount) {
        if (totalOrderAmount >= GIFT_CONDITION) {
            return EventBenefits.FREE_CHAMPAGNE.getBenefit();
        }
        return EventBenefits.NOTHING.getBenefit();
    }
}
