package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDdayDiscountTest {
    @DisplayName("크리스마스 디데이 할인 미적용")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    void 할인_미적용_테스트(int ints) {
        // Given
        ChristmasDdayDiscount discount = new ChristmasDdayDiscount();

        // When
        int discountAmount = discount.apply(ints);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("크리스마스 디데이 할인 테스트")
    @ValueSource(ints = {1, 5, 10, 15, 25})
    @ParameterizedTest
    void 기능_테스트(int ints) {
        // Given
        ChristmasDdayDiscount discount = new ChristmasDdayDiscount();

        // When
        int discountAmount = discount.apply(ints);

        // Then
        assertThat(discountAmount).isEqualTo(1000 + 100 * (ints - 1));
    }
}