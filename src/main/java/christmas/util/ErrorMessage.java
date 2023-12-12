package christmas.util;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않은 주문입니다."),
    ONLY_DRINK("음료만 주문 시, 주문할 수 없습니다."),
    MAXIMUM_ORDER("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");

    public static final String BASE_MESSAGE = "[ERROR] %s 다시 입력해 주세요.";
    private final String message;

    ErrorMessage(String message) {
        this.message = String.format(BASE_MESSAGE, message);
    }

    public String getMessage() {
        return message;
    }
}
