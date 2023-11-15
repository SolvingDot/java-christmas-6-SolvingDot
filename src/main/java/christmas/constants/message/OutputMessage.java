package christmas.constants.message;

public enum OutputMessage {
    PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    ORDER_PRICE("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    BENEFIT_AMOUNT("<총혜택 금액>"),
    PAYMENT_AMOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),
    MENU_FORM("%s %d개"),
    KRW_FORM("%s원"),
    EACH_BENEFIT_FORM("%s: %s원"),
    NO_BENEFIT("없음"),
    NO_BENEFIT_PRICE("0원"),
    GIFT_CHAMPAGNE("샴페인 1개");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
