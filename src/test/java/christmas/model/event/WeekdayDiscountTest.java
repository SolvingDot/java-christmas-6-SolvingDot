package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.model.event.constant.DiscountAmount;
import christmas.model.event.constant.EventDate;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WeekdayDiscountTest {
    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(EventDate.FIRST_DAY, EventDate.LAST_DAY);
    }

    @DisplayName("주말이면 할인 금액은 0원이다")
    @MethodSource("provideAllDatesInDecember") // 전체 12월 날짜 중에
    @ParameterizedTest
    void 할인_미적용_날짜_테스트(int date) {
        assumeTrue(EventDate.WEEKEND.contains(date)); // 주말인 경우만 테스트
        // Given
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        Map<String, Integer> orderSheet = Map.of("초코케이크", 1);

        // When
        int discountAmount = weekdayDiscount.apply(date, orderSheet);

        // Then
        assertThat(discountAmount).isEqualTo(DiscountAmount.NO_DISCOUNT);
    }

    @DisplayName("평일이라도 디저트가 없으면 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember") // 전체 12월 날짜 중에
    @ParameterizedTest
    void 할인_미적용_메뉴_테스트(int date) {
        assumeTrue(!EventDate.WEEKEND.contains(date)); // 평일인 경우만 테스트
        // Given
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        Map<String, Integer> orderSheet = Map.of("양송이수프", 1, "레드와인", 1);

        // When
        int discountAmount = weekdayDiscount.apply(date, orderSheet);

        // Then
        assertThat(discountAmount).isEqualTo(DiscountAmount.NO_DISCOUNT);
    }

    @DisplayName("평일이면 디저트 개수만큼 할인한다.")
    @MethodSource("provideAllDatesInDecember") // 전체 12월 날짜 중에
    @ParameterizedTest
    void 할인_적용_테스트(int date) {
        assumeTrue(!EventDate.WEEKEND.contains(date)); // 평일인 경우만 테스트
        // Given
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        Map<String, Integer> orderSheet = Map.of("양송이수프", 1, "초코케이크", 1, "아이스크림", 1);

        // When
        int discountAmount = weekdayDiscount.apply(date, orderSheet);

        // Then
        assertThat(discountAmount).isEqualTo(DiscountAmount.WEEKDAY_DISCOUNT * 2);
    }
}