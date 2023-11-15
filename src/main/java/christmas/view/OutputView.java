package christmas.view;

import christmas.constants.EventBenefits;
import christmas.domain.calculator.Calculator;
import christmas.domain.event.Badge;
import christmas.domain.event.Gift;
import christmas.domain.order.OrderPrice;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String NO_BENEFIT = "없음";
    private static final String NO_BENEFIT_PRICE = "0원";
    private static final String GIFT_CHAMPAGNE = "샴페인 1개";
    private static final String PRICE_FORMAT = "###,###";
    private static final String DISCOUNT_FORMAT = "-###,###";


    public void printOrderedMenu(Map<String, Integer> orderTable) {
        System.out.println("<주문 메뉴>");
        for (String menuName : orderTable.keySet()) {
            System.out.println(String.format("%s %d개", menuName, orderTable.get(menuName)));
        }
        System.out.println();
    }

    public void printTotalOrderPrice(Map<String, Integer> orderTable) {
        OrderPrice orderPrice = new OrderPrice();
        int totalOrderPrice = orderPrice.calculateTotalOrderPrice(orderTable);
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%s원", convertPriceFormat(totalOrderPrice)));
        System.out.println();
    }

    public void printGiftBenefit(Map<String, Integer> benefitTable) {
        Gift gift = new Gift();
        System.out.println("<증정 메뉴>");
        if (!gift.receiveChampagne(benefitTable)) {
            printNoBenefit();
            return;
        }
        System.out.println(GIFT_CHAMPAGNE);
        System.out.println();
    }

    public void printBenefitDetails(Map<String, Integer> benefitTable) {
        System.out.println("<혜택 내역>");
        if (hasNotAnyBenefit(benefitTable)) {
            printNoBenefit();
            return;
        }
        printBenefits(benefitTable);
        System.out.println();
    }

    public void printTotalBenefitAmount(Map<String, Integer> benefitTable) {
        System.out.println("<총혜택 금액>");
        if (hasNotAnyBenefit(benefitTable)) {
            printNoBenefitPrice();
            return;
        }
        printBenefitPrice(benefitTable);
    }

    public void printPaymentAmountAfterDiscount(Map<String, Integer> orderTable, Map<String, Integer> benefitsTable) {
        Calculator calculator = new Calculator();
        int paymentAmount = calculator.calculatePaymentAmount(orderTable, benefitsTable);
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%s원", convertPriceFormat(paymentAmount)));
        System.out.println();
    }

    public void printEventBadge(Map<String, Integer> benefitTable) {
        Badge badge = new Badge();
        Calculator calculator = new Calculator();
        int totalBenefitAmount = calculator.calculateTotalBenefitAmount(benefitTable);
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.checkForBadge(totalBenefitAmount));
    }

    private void printBenefits(Map<String, Integer> benefitTable) {
        for (String event : benefitTable.keySet()) {
            int benefit = benefitTable.get(event);
            if (benefit != EventBenefits.NOTHING.getBenefit()) {
                System.out.println(String.format("%s: %s원", event, convertDiscountFormat(benefit)));
            }
        }
    }

    private void printBenefitPrice(Map<String, Integer> benefitTable) {
        Calculator calculator = new Calculator();
        int totalBenefitAmount = calculator.calculateTotalBenefitAmount(benefitTable);
        System.out.println(String.format("%s원", convertDiscountFormat(totalBenefitAmount)));
        System.out.println();
    }

    private String convertPriceFormat(int totalOrderPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        return decimalFormat.format(totalOrderPrice);
    }

    private String convertDiscountFormat(int benefit) {
        DecimalFormat decimalFormat = new DecimalFormat(DISCOUNT_FORMAT);
        return decimalFormat.format(benefit);
    }

    private boolean hasNotAnyBenefit(Map<String, Integer> benefitTable) {
        return benefitTable.values().stream()
                .allMatch(element -> element.equals(EventBenefits.NOTHING.getBenefit()));
    }

    private void printNoBenefit() {
        System.out.println(NO_BENEFIT);
        System.out.println();
    }

    private void printNoBenefitPrice() {
        System.out.println(NO_BENEFIT_PRICE);
        System.out.println();
    }
}
