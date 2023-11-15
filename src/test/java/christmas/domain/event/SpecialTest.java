package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SpecialTest {
    @DisplayName("이벤트 달력에 별이 없는 날짜이면 특별 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void isNoDiscount_WhenDateHasNoStarInCalender(int date) {
        assumeTrue(!EventDates.SPECIAL.contains(date));
        // Given
        final Special special = new Special();

        // When
        int discountAmount = special.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("이벤트 달력에 별이 있는 날짜이면 특별 할인 금액을 적용한다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void applySpecialDiscount_WhenDateHasNoStarInCalender(int date) {
        assumeTrue(EventDates.SPECIAL.contains(date));
        // Given
        final Special special = new Special();

        // When
        int discountAmount = special.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.SPECIAL_DISCOUNT.getBenefit());
    }

    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(EventDates.FIRST_DATE, EventDates.LAST_DATE);
    }
}