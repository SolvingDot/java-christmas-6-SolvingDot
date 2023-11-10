package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.constants.EventDates;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayTest {
    @DisplayName("날짜가 주말이면 평일 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void calculateDiscountAmount_WhenDateIsInWeekendRange(int date) {
        assumeTrue(EventDates.WEEKEND.contains(date));
        // Given
        final Weekday weekday = new Weekday();

        // When
        int discountAmount = weekday.calculateDiscountAmount(date, "Dessert", 3);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("날짜가 평일이지만, 메뉴가 디저트가 아니면 평일 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void calculateDiscountAmount_WhenMenuIsNotDessert(int date) {
        assumeTrue(!EventDates.WEEKEND.contains(date));
        // Given
        final Weekday weekday = new Weekday();

        // When
        int discountAmount = weekday.calculateDiscountAmount(date, "MainDish", 3);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("날짜가 평일이고 메뉴가 디저트이면 평일 이벤트 할인 금액을 계산한다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void calculateDiscountAmount_WhenDateIsInWeekdayRange(int date) {
        assumeTrue(!EventDates.WEEKEND.contains(date));
        // Given
        final Weekday weekday = new Weekday();

        // When
        int discountAmount = weekday.calculateDiscountAmount(date, "Dessert", 3);

        // Then
        assertThat(discountAmount).isEqualTo(3 * 2023);
    }

    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(1, 31);
    }
}