package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBenefits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GiftTest {
    @DisplayName("총 주문 금액이 12만원 미만이면 샴페인을 증정하지 않는다.")
    @Test
    void isNoChampagne_WhenOrderPriceIsLessThanRequiedPrice() {
        // Given
        Gift gift = new Gift();

        // When
        int freeChampagne = gift.checkForGift(119_999);

        // Then
        assertThat(freeChampagne).isEqualTo(EventBenefits.NOTHING.getBenefit());
    }

    @DisplayName("총 주문 금액이 12만원 이상이면 샴페인을 1개(가격: 25,000원) 증정한다.")
    @ValueSource(ints = {120_000, 120_001})
    @ParameterizedTest
    void presentFreeChampagne_WhenOrderPriceIsOrMoreThanRequiedPrice(int totalOrderPrice) {
        // Given
        Gift gift = new Gift();

        // When
        int freeChampagne = gift.checkForGift(totalOrderPrice);

        // Then
        assertThat(freeChampagne).isEqualTo(EventBenefits.FREE_CHAMPAGNE.getBenefit());
    }
}