package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBenefits;
import christmas.domain.event.Gift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GiftTest {
    @DisplayName("총 주문 금액이 12만원 미만이면 샴페인을 증정하지 않는다(0개).")
    @Test
    void isNoChampagne_WhenOrderAmountIsLessThanRequiredAmount() {
        // Given
        Gift gift = new Gift();

        // When
        int freeChampagne = gift.checkForGift(119_999);

        // Then
        assertThat(freeChampagne).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("총 주문 금액이 12만원 이상이면 샴페인을 1개 증정한다.")
    @ValueSource(ints = {120_000, 120_001})
    @ParameterizedTest
    void presentFreeChampagne_WhenOrderAmountIsOrMoreThanRequiredAmount(int totalOrderAmount) {
        // Given
        Gift gift = new Gift();

        // When
        int freeChampagne = gift.checkForGift(totalOrderAmount);

        // Then
        assertThat(freeChampagne).isEqualTo(EventBenefits.FREE_CHAMPAGNE.getBenefit());
    }
}