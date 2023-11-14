package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.domain.order.OrderCalculator;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private static final int MINIMUM_AMOUNT = 10_000;

    public Map<String, Integer> applyEvents(int date, Map<String, Integer> orderTable) {
        if (meetEventApplicableCondition(readOrderAmount(orderTable))) {
            return makeTotalBenefitsTable(date, orderTable);
        }
        return makeNoBenefitTable();
    }

    private boolean meetEventApplicableCondition(int totalOrderAmount) {
        return totalOrderAmount >= MINIMUM_AMOUNT;
    }

    private int readOrderAmount(Map<String, Integer> orderTable) {
        OrderCalculator calculator = new OrderCalculator();
        return calculator.calculateTotalOrderAmount(orderTable);
    }

    private Map<String, Integer> makeNoBenefitTable() {
        Map<String, Integer> benefitsTable = new HashMap<>();
        benefitsTable.put(EventBenefits.DDAY_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit());
        benefitsTable.put(EventBenefits.WEEKDAY_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit());
        benefitsTable.put(EventBenefits.WEEKEND_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit());
        benefitsTable.put(EventBenefits.SPECIAL_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit());
        benefitsTable.put(EventBenefits.FREE_CHAMPAGNE.getDetail(), EventBenefits.NOTHING.getBenefit());
        return benefitsTable;
    }

    private Map<String, Integer> makeTotalBenefitsTable(int date, Map<String, Integer> orderTable) {
        Map<String, Integer> benefitsTable = new HashMap<>();
        benefitsTable.put(EventBenefits.DDAY_DISCOUNT.getDetail(), readDdayBenefit(date));
        benefitsTable.put(EventBenefits.WEEKDAY_DISCOUNT.getDetail(), readWeekdayBenefit(date, orderTable));
        benefitsTable.put(EventBenefits.WEEKEND_DISCOUNT.getDetail(), readWeekendBenefit(date, orderTable));
        benefitsTable.put(EventBenefits.SPECIAL_DISCOUNT.getDetail(), readSpecialBenefit(date));
        benefitsTable.put(EventBenefits.FREE_CHAMPAGNE.getDetail(), readGiftBenefit(orderTable));
        return benefitsTable;
    }

    // 디데이 이벤트 결과
    private int readDdayBenefit(int date) {
        Dday dday = new Dday();
        return dday.checkForDiscount(date);
    }

    // 평일 할인 이벤트 결과
    private int readWeekdayBenefit(int date, Map<String, Integer> orderTable) {
        Weekday weekday = new Weekday();
        int weekdayBenefit = EventBenefits.NOTHING.getBenefit();
        for (String menuName : orderTable.keySet()) {
            weekdayBenefit += weekday.checkForDiscount(date, menuName, orderTable.get(menuName));
        }
        return weekdayBenefit;
    }

    // 주말 할인 이벤트 결과
    private int readWeekendBenefit(int date, Map<String, Integer> orderTable) {
        Weekend weekend = new Weekend();
        int weekendBenefit = EventBenefits.NOTHING.getBenefit();
        for (String menuName : orderTable.keySet()) {
            weekendBenefit += weekend.checkForDiscount(date, menuName, orderTable.get(menuName));
        }
        return weekendBenefit;
    }

    private int readSpecialBenefit(int date) {
        SpecialDiscount special = new SpecialDiscount();
        return special.checkForDiscount(date);
    }

    private int readGiftBenefit(Map<String, Integer> orderTable) {
        Gift gift = new Gift();
        return gift.checkForGift(readOrderAmount(orderTable));
    }
}
