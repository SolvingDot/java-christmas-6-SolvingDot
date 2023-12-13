package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GiftawayEventTest {
    @DisplayName("할인 전 총주문 금액이 12만원 미만이면 혜택 금액은 0원이다.")
    @ValueSource(ints = {10_000, 100_000, 119_999})
    @ParameterizedTest
    void 혜택_미적용_테스트(int ints) {
        // Given
        GiftawayEvent giftawayEvent = new GiftawayEvent();

        // When
        int benefitAmount = giftawayEvent.apply(ints);

        // Then
        assertThat(benefitAmount).isEqualTo(0);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 혜택 금액은 샴페인 1개 가격이다.")
    @ValueSource(ints = {120_000, 120_001, 300_000})
    @ParameterizedTest
    void 혜택_적용_테스트(int ints) {
        // Given
        GiftawayEvent giftawayEvent = new GiftawayEvent();

        // When
        int benefitAmount = giftawayEvent.apply(ints);

        // Then
        assertThat(benefitAmount).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }
}