package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SpecialDiscountTest {
    // 파라미터로 사용할 전체 범위 설정
    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(EventDate.FIRST_DAY, EventDate.LAST_DAY);
    }

    @DisplayName("별이 있는 날짜가 아니면 할인 금액은 0원이다")
    @MethodSource("provideAllDatesInDecember") // 전체 12월 날짜 중에
    @ParameterizedTest
    void 할인_미적용_테스트(int date) {
        assumeTrue(!EventDate.SPECIAL_DAY.contains(date)); // 스페셜 데이에 포함되지 않는 경우만 테스트
        // Given
        SpecialDiscount specialDiscount = new SpecialDiscount();

        // When
        int discountAmount = specialDiscount.apply(date);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("별이 있는 날짜에는 할인 금액이 1000원이다.")
    @MethodSource("provideAllDatesInDecember") // 전체 12월 날짜 중에
    @ParameterizedTest
    void 할인_적용_테스트(int date) {
        assumeTrue(EventDate.SPECIAL_DAY.contains(date));
        // Given
        SpecialDiscount specialDiscount = new SpecialDiscount();

        // When
        int discountAmount = specialDiscount.apply(date);

        // Then
        assertThat(discountAmount).isEqualTo(1000);
    }
}