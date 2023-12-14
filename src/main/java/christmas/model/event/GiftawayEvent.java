package christmas.model.event;

import christmas.model.Menu;
import christmas.model.event.constant.DiscountAmount;

public class GiftawayEvent {
    private static final int EVENT_CONDITION = 120_000;

    public int apply(int totalAmount) {
        if (totalAmount < EVENT_CONDITION) {
            return DiscountAmount.NO_DISCOUNT;
        }
        return Menu.CHAMPAGNE.getPrice();
    }
}
