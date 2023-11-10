package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.constants.DiscountAmount;
import christmas.constants.EventDates;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WeekendTest {
    @DisplayName("날짜가 평일이면 주말 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void calculateDiscountAmount_WhenDateIsInWeekdayRange(int date) {
        assumeTrue(!EventDates.WEEKEND.contains(date));
        // Given
        final Weekend weekend = new Weekend();

        // When
        int discountAmount = weekend.calculateDiscountAmount(date, "MainDish", 3);

        // Then
        assertThat(discountAmount).isEqualTo(DiscountAmount.NO_DISCOUNT.getAmount());
    }

    @DisplayName("날짜가 주말이지만, 메뉴가 메인 메뉴가 아니면 주말 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void calculateDiscountAmount_WhenMenuIsNotMainDish(int date) {
        assumeTrue(EventDates.WEEKEND.contains(date));
        // Given
        final Weekend weekend = new Weekend();

        // When
        int discountAmount = weekend.calculateDiscountAmount(date, "Dessert", 3);

        // Then
        assertThat(discountAmount).isEqualTo(DiscountAmount.NO_DISCOUNT.getAmount());
    }

    @DisplayName("날짜가 주말이고 메뉴가 메인 메뉴이면 주말 이벤트 할인 금액을 계산한다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void calculateDiscountAmount_WhenDateIsInWeekendRange(int date) {
        assumeTrue(EventDates.WEEKEND.contains(date));
        // Given
        final Weekend weekend = new Weekend();

        // When
        int discountAmount = weekend.calculateDiscountAmount(date, "MainDish", 3);

        // Then
        assertThat(discountAmount).isEqualTo(3 * DiscountAmount.WEEKEND.getAmount());
    }

    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(1, 31);
    }

}