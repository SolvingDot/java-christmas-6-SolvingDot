package christmas.view;

import christmas.model.Menu;
import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventName;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String FORMAT_MENU = "%s %d개";
    private static final String FORMAT_BENEFIT_DETAILS = "%s: %s";
    private static final String FORMAT_PRICE = "###,###원";
    private static final String FORMAT_DISCOUNT = "-###,###원";

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance(){
        return instance;
    }

    private OutputView() {
    }

    public void printGameStart() {
        System.out.println(Message.OUTPUT_GAME_START.message);
    }

    public void printPreviewStart() {
        System.out.println(Message.OUTPUT_PREVIEW_START.message);
    }

    public void printMenuDetails(Map<String, Integer> orderSheet) {
        System.out.println(Message.OUTPUT_MENU_DETAILS.message);
        for (String menuName : orderSheet.keySet()) {
            System.out.println(String.format(FORMAT_MENU, menuName, orderSheet.get(menuName)));
        }
    }

    public void printOrderAmount(int totalAmount) {
        System.out.println(Message.OUTPUT_ORDER_AMOUNT.message);
        System.out.println(applyPriceFormat(totalAmount));
    }

    public void printGiftaway(Map<EventName, Integer> benefitSheet) {
        System.out.println(Message.OUTPUT_GIFTAWAY.message);
        if (benefitSheet.get(EventName.GIFTAWAY_EVENT).equals(DiscountAmount.NO_DISCOUNT)) {
            System.out.println(Message.OUTPUT_NO_BENEFIT.message);
        }
        if (benefitSheet.get(EventName.GIFTAWAY_EVENT).equals(Menu.CHAMPAGNE.getPrice())) {
            System.out.println(Message.OUTPUT_GIFTAWAY_DETAILS.message);
        }
    }

    public void printBenefitDetails(Map<EventName, Integer> benefitSheet) {
        System.out.println(Message.OUTPUT_BENEFIT_DETAILS.message);
        for (EventName event : EventName.values()) {
            if (benefitSheet.get(event).equals(DiscountAmount.NO_DISCOUNT)) {
                continue;
            }
            System.out.println(String.format(FORMAT_BENEFIT_DETAILS,
                    event.getName(), applyDiscountFormat(benefitSheet.get(event))));
        }
    }

    private String applyPriceFormat(int price) {
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT_PRICE);
        return decimalFormat.format(price);
    }

    private String applyDiscountFormat(int discountAmount) {
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT_DISCOUNT);
        return decimalFormat.format(discountAmount);
    }

    private enum Message {
        OUTPUT_GAME_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
        OUTPUT_PREVIEW_START("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
        OUTPUT_MENU_DETAILS("<주문 메뉴>"),
        OUTPUT_ORDER_AMOUNT("<할인 전 총주문 금액>"),
        OUTPUT_GIFTAWAY("<증정 메뉴>"),
        OUTPUT_GIFTAWAY_DETAILS("샴페인 1개"),
        OUTPUT_BENEFIT_DETAILS("<혜택 내역>"),
        OUTPUT_BENEFIT_AMOUNT("<총혜택 금액>"),
        OUTPUT_PAYMENT_AMOUNT("<할인 후 예상 결제 금액>"),
        OUTPUT_EVENT_BADGE("<12월 이벤트 배지>"),
        OUTPUT_NO_BENEFIT("없음");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
