package christmas.controller;

import christmas.model.AmountOfOrder;
import christmas.model.Date;
import christmas.model.Order;
import christmas.model.event.Badge;
import christmas.model.event.CashDesk;
import christmas.model.event.constant.EventName;
import christmas.util.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        outputView.printGameStart();
        int date = readDate();
        Map<String, Integer> orderSheet = readOrder();
        int totalAmount = sumAmountOfOrder(orderSheet);
        
        Map<EventName, Integer> benefitSheet = collectEventResults(date, orderSheet, totalAmount);

        // 총 혜택 금액, 예상 결제 금액 계산
        CashDesk cashDesk = new CashDesk();
        int totalBenefit = cashDesk.calculateTotalBenefit(benefitSheet);
        int amountToPay = cashDesk.calculateAmountToPay(totalAmount, benefitSheet);

        // 배지
        Badge badge = new Badge();
        String badgeName = badge.give(totalBenefit);

        // 이벤트 결과 출력
        outputView.printPreviewStart();
        
        outputView.printMenuDetails(orderSheet);
        outputView.printOrderAmount(totalAmount);
        
        outputView.printGiftaway(benefitSheet);
        outputView.printBenefitDetails(benefitSheet);
        outputView.printTotalBenefit(totalBenefit);
        outputView.printAmountToPay(amountToPay);
        outputView.printBadge(badgeName);
    }

    private Map<EventName, Integer> collectEventResults(int date, Map<String, Integer> orderSheet, int totalAmount) {
        EventController eventController = new EventController();
        return eventController.makeBenefitSheet(date, orderSheet, totalAmount);
    }

    private int sumAmountOfOrder(Map<String, Integer> orderSheet) {
        AmountOfOrder amountOfOrder = new AmountOfOrder();
        return amountOfOrder.calculate(orderSheet);
    }

    private int readDate() {
        Date date = new Date();
        while (true) {
            try {
                return date.read(inputView.readDate());
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            }
        }
    }

    private Map<String, Integer> readOrder() {
        Order order = new Order();
        while (true) {
            try {
                return order.read(inputView.readOrder());
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.INVALID_ORDER.getMessage());
            }
        }
    }
}
