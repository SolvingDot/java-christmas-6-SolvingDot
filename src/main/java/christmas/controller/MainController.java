package christmas.controller;

import christmas.model.AmountOfOrder;
import christmas.model.Date;
import christmas.model.Order;
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

        // 날짜 입력, 검증, 변환 (예외 발생시 반복)
        int date = readDate();

        // 주문 입력, 검증, 변환 (예외 발생시 반복)
        Map<String, Integer> orderSheet = readOrder();

        // 할인 전 총주문 금액
        AmountOfOrder amountOfOrder = new AmountOfOrder();
        int totalAmount = amountOfOrder.calculate(orderSheet);
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
