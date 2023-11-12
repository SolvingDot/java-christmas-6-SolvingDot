package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.domain.event.SpecialDiscount;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SpecialDiscountTest {
    @DisplayName("이벤트 달력에 별이 없는 날짜이면 특별 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void isNoDiscount_WhenDateHasNoStarInCalender(int date) {
        assumeTrue(!EventDates.SPECIAL.contains(date));
        // Given
        final SpecialDiscount specialDiscount = new SpecialDiscount();

        // When
        int discountAmount = specialDiscount.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("이벤트 달력에 별이 있는 날짜이면 특별 할인 금액을 적용한다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void applySpecialDiscount_WhenDateHasNoStarInCalender(int date) {
        assumeTrue(EventDates.SPECIAL.contains(date));
        // Given
        final SpecialDiscount specialDiscount = new SpecialDiscount();

        // When
        int discountAmount = specialDiscount.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.SPECIAL_DISCOUNT.getBenefit());
    }

    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(1, 31);
    }
}