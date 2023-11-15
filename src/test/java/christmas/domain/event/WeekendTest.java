package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.domain.event.Weekend;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WeekendTest {
    @DisplayName("날짜가 평일이면 주말 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void isNoDiscount_WhenDateIsInWeekdayRange(int date) {
        assumeTrue(!EventDates.WEEKEND.contains(date));
        // Given
        final Weekend weekend = new Weekend();

        // When
        int discountAmount = weekend.checkForDiscount(date, "해산물파스타", 3);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("날짜가 주말이지만, 메뉴가 메인 메뉴가 아니면 주말 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void isNoDiscount_WhenMenuIsNotMainDish(int date) {
        assumeTrue(EventDates.WEEKEND.contains(date));
        // Given
        final Weekend weekend = new Weekend();

        // When
        int discountAmount = weekend.checkForDiscount(date, "초코케이크", 3);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("날짜가 주말이고 메뉴가 메인 메뉴이면 주말 이벤트 할인 금액을 계산한다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void applyWeekendDiscount_WhenDateIsInWeekendRange(int date) {
        assumeTrue(EventDates.WEEKEND.contains(date));
        // Given
        final Weekend weekend = new Weekend();

        // When
        int discountAmount = weekend.checkForDiscount(date, "해산물파스타", 3);

        // Then
        assertThat(discountAmount).isEqualTo(3 * EventBenefits.WEEKEND_DISCOUNT.getBenefit());
    }

    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(EventDates.FIRST_DATE, EventDates.LAST_DATE);
    }

}