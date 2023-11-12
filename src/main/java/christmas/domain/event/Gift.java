package christmas.domain.event;

import christmas.constants.EventBenefits;

public class Gift {
    public int checkForGift(int totalOrderAmount) {
        if (totalOrderAmount >= 120_000) {
            return EventBenefits.FREE_CHAMPAGNE.getBenefit();
        }
        return EventBenefits.NOTHING.getBenefit();
    }
}
