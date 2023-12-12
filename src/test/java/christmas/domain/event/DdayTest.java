package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DdayTest {
    private static final int TODAY = 1;

    @DisplayName("날짜가 1~25일이 아니면 할인 금액은 0원이다.")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    void isNoDiscount_WhenDateIsOutsideDdayRange(int date) {
        // Given
        final Dday dday = new Dday();

        // When
        int discountAmount = dday.checkForDiscount(date);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
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
                + EventBenefits.DDAY_DISCOUNT_INCREASE.getBenefit() * (date - TODAY));
    }

    private static IntStream provideDatesInDdayRange() {
        return IntStream.rangeClosed(EventDates.FIRST_DATE, EventDates.CHRISTMAS);
    }
}