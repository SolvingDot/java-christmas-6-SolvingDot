package christmas.controller;

import christmas.model.event.ChristmasDdayDiscount;
import christmas.model.event.GiftawayEvent;
import christmas.model.event.SpecialDiscount;
import christmas.model.event.WeekdayDiscount;
import christmas.model.event.WeekendDiscount;
import christmas.model.event.constant.EventName;
import java.util.HashMap;
import java.util.Map;

public class EventController {
    final ChristmasDdayDiscount christmasDdayDiscount = new ChristmasDdayDiscount();
    final WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
    final WeekendDiscount weekendDiscount = new WeekendDiscount();
    final SpecialDiscount specialDiscount = new SpecialDiscount();
    final GiftawayEvent giftawayEvent = new GiftawayEvent();

    public Map<EventName, Integer> makeBenifitSheet(int date, Map<String, Integer> orderSheet, int totalAmount) {
        // 이벤트 혜택 결과
        Map<EventName, Integer> benefitSheet = new HashMap<>();
        benefitSheet.put(EventName.CHRISTMAS_DISCOUNT, christmasDdayDiscount.apply(date));
        benefitSheet.put(EventName.WEEKDAY_DISCOUNT, weekdayDiscount.apply(date, orderSheet));
        benefitSheet.put(EventName.WEEKEND_DISCOUNT, weekendDiscount.apply(date, orderSheet));
        benefitSheet.put(EventName.SPECIAL_DISCOUNT, specialDiscount.apply(date));
        benefitSheet.put(EventName.GIFTAWAY_EVENT, giftawayEvent.apply(totalAmount));
        return benefitSheet;
    }
}
