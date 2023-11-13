package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.domain.Date;
import christmas.domain.OrderCalculator;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    // 날짜, 메뉴
    int date = new Date(24).getDate();
    String menuName = "양송이수프";
    int numberOfMenu = 2;

    // 전체 주문 금액
    public int readOrderAmount(Map<String, Integer> orderDetails) {
        OrderCalculator calculator = new OrderCalculator();
        return calculator.calculateTotalOrderAmount(Map.of("양송이수프", 2));
    }

    // 모든 이벤트 결과 합 = 총 혜택 금액
    public int calculateTotalBenefitAmount(Map<String, Integer> benefitsTable) {
        int totalBenefitAmount = 0;
        for (String event : benefitsTable.keySet()) {
            totalBenefitAmount += benefitsTable.get(event);
        }
        return totalBenefitAmount;
    }

    private Map<String, Integer> makeOrderBenefitsTable(int date, String menuName, int numberOfMenu) {
        Map<String, Integer> benefitsTable = new HashMap<>();
        benefitsTable.put(EventBenefits.DDAY_DISCOUNT.getDetail(), readDdayBenefit(date));
        benefitsTable.put(EventBenefits.WEEKDAY_DISCOUNT.getDetail(),
                readWeekdayBenefit(date, menuName, numberOfMenu));
        benefitsTable.put(EventBenefits.WEEKEND_DISCOUNT.getDetail(),
                readWeekendBenefit(date, menuName, numberOfMenu));
        benefitsTable.put(EventBenefits.SPECIAL_DISCOUNT.getDetail(), readSpecialBenefit(date));
        benefitsTable.put(EventBenefits.FREE_CHAMPAGNE.getDetail(), 0);
        return benefitsTable;
    }

    // 디데이 이벤트 결과
    private int readDdayBenefit(int date) {
        Dday dday = new Dday();
        return dday.checkForDiscount(date);
    }

    // 평일 할인 이벤트 결과
    private int readWeekdayBenefit(int date, String menuName, int numberOfMenu) {
        Weekday weekday = new Weekday();
        return weekday.checkForDiscount(date, menuName, numberOfMenu);
    }

    // 주말 할인 이벤트 결과
    private int readWeekendBenefit(int date, String menuName, int numberOfMenu) {
        Weekend weekend = new Weekend();
        return weekend.checkForDiscount(date, menuName, numberOfMenu);
    }

    // 특별 할인 이벤트 결과
    private int readSpecialBenefit(int date) {
        SpecialDiscount special = new SpecialDiscount();
        return special.checkForDiscount(date);
    }

    // 증정 이벤트 결과
    private int readGiftBenefit(int totalOrderAmount) {
        Gift gift = new Gift();
        return gift.checkForGift(totalOrderAmount);
    }
}
