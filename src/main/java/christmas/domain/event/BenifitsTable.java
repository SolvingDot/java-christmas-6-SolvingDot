package christmas.domain.event;

import christmas.constants.EventBenefits;
import christmas.domain.order.OrderPrice;
import java.util.HashMap;
import java.util.Map;

public class BenifitsTable {
    private static final int MINIMUM_PRICE = 10_000;

    public Map<String, Integer> applyEvents(int date, Map<String, Integer> orderTable) {
        if (meetEventApplicableCondition(readOrderPrice(orderTable))) {
            return makeTotalBenefitsTable(date, orderTable);
        }
        return makeNoBenefitTable();
    }

    private boolean meetEventApplicableCondition(int totalOrderPrice) {
        return totalOrderPrice >= MINIMUM_PRICE;
    }

    private int readOrderPrice(Map<String, Integer> orderTable) {
        OrderPrice calculator = new OrderPrice();
        return calculator.calculateTotalOrderPrice(orderTable);
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

    private int readDdayBenefit(int date) {
        Dday dday = new Dday();
        return dday.checkForDiscount(date);
    }

    private int readWeekdayBenefit(int date, Map<String, Integer> orderTable) {
        Weekday weekday = new Weekday();
        int weekdayBenefit = EventBenefits.NOTHING.getBenefit();
        for (String menuName : orderTable.keySet()) {
            weekdayBenefit += weekday.checkForDiscount(date, menuName, orderTable.get(menuName));
        }
        return weekdayBenefit;
    }

    private int readWeekendBenefit(int date, Map<String, Integer> orderTable) {
        Weekend weekend = new Weekend();
        int weekendBenefit = EventBenefits.NOTHING.getBenefit();
        for (String menuName : orderTable.keySet()) {
            weekendBenefit += weekend.checkForDiscount(date, menuName, orderTable.get(menuName));
        }
        return weekendBenefit;
    }

    private int readSpecialBenefit(int date) {
        Special special = new Special();
        return special.checkForDiscount(date);
    }

    private int readGiftBenefit(Map<String, Integer> orderTable) {
        Gift gift = new Gift();
        return gift.checkForGift(readOrderPrice(orderTable));
    }
}
