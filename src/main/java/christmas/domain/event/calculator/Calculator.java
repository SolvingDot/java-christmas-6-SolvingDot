package christmas.domain.event.calculator;

import christmas.constants.EventBenefits;
import christmas.domain.event.Gift;
import christmas.domain.order.OrderPrice;
import java.util.Map;

public class Calculator {
    public int calculatePaymentAmount(Map<String, Integer> orderTable, Map<String, Integer> benefitsTable) {
        OrderPrice calculator = new OrderPrice();
        return calculator.calculateTotalOrderPrice(orderTable) - calculateTotalDiscountAmount(benefitsTable);
    }

    public int calculateTotalBenefitAmount(Map<String, Integer> benefitsTable) {
        int totalBenefitAmount = EventBenefits.NOTHING.getBenefit();
        for (String event : benefitsTable.keySet()) {
            totalBenefitAmount += benefitsTable.get(event);
        }
        return totalBenefitAmount;
    }

    private int calculateTotalDiscountAmount(Map<String, Integer> benefitsTable) {
        Gift gift = new Gift();
        if (gift.receiveChampagne(benefitsTable)) {
            return calculateTotalBenefitAmount(benefitsTable) - EventBenefits.FREE_CHAMPAGNE.getBenefit();
        }
        return calculateTotalBenefitAmount(benefitsTable);
    }
}
