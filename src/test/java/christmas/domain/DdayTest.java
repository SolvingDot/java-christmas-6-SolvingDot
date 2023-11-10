package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBenefits;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DdayTest {
    @DisplayName("날짜가 1~25일이 아니면 할인 금액은 0원이다.")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    void isNoDiscount_WhenDateIsOutsideDdayRange(int date) {
        // Given
        final Dday dday = new Dday();

        // When
        int discountAmount = dday.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("1~25일 사이의 날짜에 대한 디데이 이벤트 할인 금액을 계산한다.")
    @MethodSource("provideDatesInDdayRange")
    @ParameterizedTest
    void applyDdayDiscount_WhenDateIsInDdayRange(int date) {
        // Given
        final Dday dday = new Dday();

        // When
        int discountAmount = dday.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.DDAY_DISCOUNT.getBenefit()
                + EventBenefits.DDAY_DISCOUNT_INCREASE.getBenefit() * (date - 1));
    }

    private static IntStream provideDatesInDdayRange() {
        return IntStream.rangeClosed(1, 25);
    }
}