package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import christmas.constants.EventBenefits;
import christmas.constants.EventDates;
import christmas.constants.Menu;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WeekdayTest {
    private static final int THREE = 3;

    @DisplayName("날짜가 주말이면 평일 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void isNoDiscount_WhenDateIsInWeekendRange(int date) {
        assumeTrue(EventDates.WEEKEND.contains(date));
        // Given
        final Weekday weekday = new Weekday();

        // When
        int discountAmount = weekday.checkForDiscount(date, Menu.CHOCOLATE_CAKE.getName(), THREE);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("날짜가 평일이지만, 메뉴가 디저트 메뉴가 아니면 평일 할인 금액은 0원이다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void isNoDiscount_WhenMenuIsNotDessert(int date) {
        assumeTrue(!EventDates.WEEKEND.contains(date));
        // Given
        final Weekday weekday = new Weekday();

        // When
        int discountAmount = weekday.checkForDiscount(date, Menu.SEAFOOD_PASTA.getName(), THREE);

        // Then
        assertThat(discountAmount).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("날짜가 평일이고 메뉴가 디저트 메뉴이면 평일 이벤트 할인 금액을 계산한다.")
    @MethodSource("provideAllDatesInDecember")
    @ParameterizedTest
    void applyWeekdayDiscount_WhenDateIsInWeekdayRange(int date) {
        assumeTrue(!EventDates.WEEKEND.contains(date));
        // Given
        final Weekday weekday = new Weekday();

        // When
        int discountAmount = weekday.checkForDiscount(date, Menu.CHOCOLATE_CAKE.getName(), THREE);

        // Then
        assertThat(discountAmount).isEqualTo(THREE * EventBenefits.WEEKDAY_DISCOUNT.getBenefit());
    }

    private static IntStream provideAllDatesInDecember() {
        return IntStream.rangeClosed(EventDates.FIRST_DATE, EventDates.LAST_DATE);
    }
}