package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DdayTest {
    Dday dday = new Dday();

    @DisplayName("1~25일 사이의 날짜에 대한 디데이 이벤트 할인 금액을 계산한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25})
    @ParameterizedTest
    void calculateDiscountAmount_WhenDateIsInDdayRange(int date) {
        // Given
        final Dday dday = new Dday();

        // When
        int discountAmount = dday.calculateDiscountAmount(date);

        // Then
        assertThat(discountAmount).isEqualTo(1000 + 100 * (date - 1));
    }

    @DisplayName("날짜가 1~25일이 아니면 할인 금액은 0원이다.")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    void isNoDiscount_WhenDateIsOutsideDdayRange(int date) {
        // Given
        final Dday dday = new Dday();

        // When
        int discountAmount = dday.calculateDiscountAmount(date);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }
}