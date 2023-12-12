package christmas.controller;

import christmas.model.Date;
import christmas.util.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        int date = saveDate();
        System.out.println(date);

        // 주문 입력, 검증, 변환 (예외 발생시 반복)
        String inputOrder = inputView.readOrder();
    }

    private int saveDate() {
        Date date = new Date();
        while (true) {
            try {
                return date.read(inputView.readDate());
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            }
        }
    }
}
