package christmas.domain.calculator;

import christmas.constants.EventBenefits;
import christmas.domain.order.OrderCalculator;
import java.util.Map;

public class Calculator {
    public int calculatePaymentAmount(Map<String, Integer> orderTable, Map<String, Integer> benefitsTable) {
        OrderCalculator calculator = new OrderCalculator();
        return calculator.calculateTotalOrderAmount(orderTable) - calculateTotalDiscountAmount(benefitsTable);
    }

    public int calculateTotalBenefitAmount(Map<String, Integer> benefitsTable) {
        int totalBenefitAmount = EventBenefits.NOTHING.getBenefit();
        for (String event : benefitsTable.keySet()) {
            totalBenefitAmount += benefitsTable.get(event);
        }
        return totalBenefitAmount;
    }

    private int calculateTotalDiscountAmount(Map<String, Integer> benefitsTable) {
        if (receiveChampagne(benefitsTable)) {
            return calculateTotalBenefitAmount(benefitsTable) - EventBenefits.FREE_CHAMPAGNE.getBenefit();
        }
        return calculateTotalBenefitAmount(benefitsTable);
    }

    private boolean receiveChampagne(Map<String, Integer> benefitsTable) {
        return benefitsTable.get(EventBenefits.FREE_CHAMPAGNE.getDetail())
                .equals(EventBenefits.FREE_CHAMPAGNE.getBenefit());
    }
}
