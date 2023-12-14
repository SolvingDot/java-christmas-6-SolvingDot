package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final InputView instance = new InputView();

    public static InputView getInstance(){
        return instance;
    }
    private InputView() {
    }

    public String readDate() {
        System.out.println(Message.INPUT_DATE.message);
        return Console.readLine();
    }

    public String readOrder() {
        System.out.println(Message.INPUT_ORDER.message);
        return Console.readLine();
    }

    private enum Message {
        INPUT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        INPUT_ORDER("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
