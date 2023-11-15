package christmas.view;

import christmas.constants.EventBenefits;
import christmas.constants.message.OutputMessage;
import christmas.domain.event.calculator.Calculator;
import christmas.domain.date.Date;
import christmas.domain.event.Badge;
import christmas.domain.event.Gift;
import christmas.domain.order.OrderPrice;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String PRICE_FORMAT = "###,###";
    private static final String DISCOUNT_FORMAT = "-###,###";

    public void printPlannerOutView(Date date, Map<String, Integer> orderTable, Map<String, Integer> benefitTable) {
        printPreviewMessage(date);
        printOrderResults(orderTable);
        printEventResults(benefitTable, orderTable);
    }

    private void printPreviewMessage(Date date) {
        System.out.println(String.format(OutputMessage.PREVIEW.getMessage(), date.getDate()));
    }

    private void printOrderResults(Map<String, Integer> orderTable) {
        printOrderedMenu(orderTable);
        printTotalOrderPrice(orderTable);
    }

    private void printEventResults(Map<String, Integer> benefitTable, Map<String, Integer> orderTable) {
        printGiftBenefit(benefitTable);
        printBenefitDetails(benefitTable);
        printTotalBenefitAmount(benefitTable);
        printPaymentAmountAfterDiscount(orderTable, benefitTable);
        printEventBadge(benefitTable);
    }

    private void printOrderedMenu(Map<String, Integer> orderTable) {
        System.out.println(OutputMessage.ORDER_MENU.getMessage());
        for (String menuName : orderTable.keySet()) {
            System.out.println(String.format(OutputMessage.MENU_FORM.getMessage(),
                    menuName, orderTable.get(menuName)));
        }
        System.out.println();
    }

    private void printTotalOrderPrice(Map<String, Integer> orderTable) {
        OrderPrice orderPrice = new OrderPrice();
        int totalOrderPrice = orderPrice.calculateTotalOrderPrice(orderTable);
        System.out.println(OutputMessage.ORDER_PRICE.getMessage());
        System.out.println(String.format(OutputMessage.KRW_FORM.getMessage(),
                convertPriceFormat(totalOrderPrice)));
        System.out.println();
    }

    private void printGiftBenefit(Map<String, Integer> benefitTable) {
        Gift gift = new Gift();
        System.out.println(OutputMessage.GIFT_MENU.getMessage());
        if (!gift.receiveChampagne(benefitTable)) {
            printNoBenefit();
            return;
        }
        System.out.println(OutputMessage.GIFT_CHAMPAGNE.getMessage());
        System.out.println();
    }

    private void printBenefitDetails(Map<String, Integer> benefitTable) {
        System.out.println(OutputMessage.BENEFIT_DETAILS.getMessage());
        if (hasNotAnyBenefit(benefitTable)) {
            printNoBenefit();
            return;
        }
        printBenefits(benefitTable);
        System.out.println();
    }

    private void printTotalBenefitAmount(Map<String, Integer> benefitTable) {
        System.out.println(OutputMessage.BENEFIT_AMOUNT.getMessage());
        if (hasNotAnyBenefit(benefitTable)) {
            printNoBenefitPrice();
            return;
        }
        printBenefitPrice(benefitTable);
    }

    private void printPaymentAmountAfterDiscount(Map<String, Integer> orderTable, Map<String, Integer> benefitsTable) {
        Calculator calculator = new Calculator();
        int paymentAmount = calculator.calculatePaymentAmount(orderTable, benefitsTable);
        System.out.println(OutputMessage.PAYMENT_AMOUNT.getMessage());
        System.out.println(String.format(OutputMessage.KRW_FORM.getMessage(),
                convertPriceFormat(paymentAmount)));
        System.out.println();
    }

    private void printEventBadge(Map<String, Integer> benefitTable) {
        Badge badge = new Badge();
        Calculator calculator = new Calculator();
        int totalBenefitAmount = calculator.calculateTotalBenefitAmount(benefitTable);
        System.out.println(OutputMessage.EVENT_BADGE.getMessage());
        System.out.println(badge.checkForBadge(totalBenefitAmount));
    }

    private void printBenefits(Map<String, Integer> benefitTable) {
        for (String event : benefitTable.keySet()) {
            int benefit = benefitTable.get(event);
            if (benefit != EventBenefits.NOTHING.getBenefit()) {
                System.out.println(String.format(OutputMessage.EACH_BENEFIT_FORM.getMessage(),
                        event, convertDiscountFormat(benefit)));
            }
        }
    }

    private void printBenefitPrice(Map<String, Integer> benefitTable) {
        Calculator calculator = new Calculator();
        int totalBenefitAmount = calculator.calculateTotalBenefitAmount(benefitTable);
        System.out.println(String.format(OutputMessage.KRW_FORM.getMessage(),
                convertDiscountFormat(totalBenefitAmount)));
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
        System.out.println(OutputMessage.NO_BENEFIT.getMessage());
        System.out.println();
    }

    private void printNoBenefitPrice() {
        System.out.println(OutputMessage.NO_BENEFIT_PRICE.getMessage());
        System.out.println();
    }
}
