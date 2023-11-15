package christmas.domain.event;

import christmas.constants.EventBenefits;
import java.util.Map;

public class Gift {
    private static final int GIFT_CONDITION = 120_000;

    public int checkForGift(int totalOrderPrice) {
        if (totalOrderPrice >= GIFT_CONDITION) {
            return EventBenefits.FREE_CHAMPAGNE.getBenefit();
        }
        return EventBenefits.NOTHING.getBenefit();
    }

    public boolean receiveChampagne(Map<String, Integer> benefitsTable) {
        return benefitsTable.get(EventBenefits.FREE_CHAMPAGNE.getDetail())
                .equals(EventBenefits.FREE_CHAMPAGNE.getBenefit());
    }
}
