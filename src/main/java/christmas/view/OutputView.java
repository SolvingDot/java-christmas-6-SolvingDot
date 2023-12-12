package christmas.view;

public class OutputView {
    private static final OutputView instance = new OutputView();

    public static OutputView getInstance(){
        return instance;
    }

    private OutputView() {
    }

    public void printGameStart() {
        System.out.println(Message.OUTPUT_GAME_START.message);
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    private enum Message {
        OUTPUT_GAME_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
