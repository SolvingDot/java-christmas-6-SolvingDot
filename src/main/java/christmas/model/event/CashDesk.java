package christmas.model.event;

import christmas.model.Menu;
import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventName;
import java.util.Map;

public class CashDesk {
    public int calculateTotalBenefit(Map<EventName, Integer> benefitSheet) {
        int totalBenefit = DiscountAmount.NO_DISCOUNT;
        for (int benefit : benefitSheet.values()) {
            totalBenefit += benefit;
        }
        return totalBenefit;
    }

    public int calculateAmountToPay(int totalAmount, Map<EventName, Integer> benefitSheet) {
        int totalDiscount = DiscountAmount.NO_DISCOUNT;
        for (int discount : benefitSheet.values()) {
            if (discount == Menu.CHAMPAGNE.getPrice()) {
                continue;
            }
            totalDiscount += discount;
        }
        return totalAmount - totalDiscount;
    }
}
