package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Menu;
import christmas.model.event.constant.EventName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CashDeskTest {
    @DisplayName("예상 결제 금액은 총 주문 금액에서 할인 금액을 제한 금액으로 증정품 가격은 반영하지 않는다.")
    @Test
    void 예상_결제_금액_기능_테스트() {
        // Given
        CashDesk cashDesk = new CashDesk();
        int totalAmount = 120_000;
        Map<EventName, Integer> benefitSheet =
                Map.of(EventName.CHRISTMAS_DISCOUNT, 1000,
                        EventName.WEEKDAY_DISCOUNT, 2023,
                        EventName.WEEKEND_DISCOUNT, 2023,
                        EventName.SPECIAL_DISCOUNT, 1000,
                        EventName.GIFTAWAY_EVENT, Menu.CHAMPAGNE.getPrice());

        // When
        int amountToPay = cashDesk.calculateAmountToPay(totalAmount, benefitSheet);

        // Then
        assertThat(amountToPay).isEqualTo(totalAmount - 1000 - 2023 - 2023 - 1000);
    }

    @DisplayName("총 혜택 금액은 할인 금액과 증정품 가격을 합한 금액이다.")
    @Test
    void 총_혜택_금액_기능_테스트() {
        // Given
        CashDesk cashDesk = new CashDesk();
        Map<EventName, Integer> benefitSheet =
                Map.of(EventName.CHRISTMAS_DISCOUNT, 1000,
                        EventName.WEEKDAY_DISCOUNT, 2023,
                        EventName.WEEKEND_DISCOUNT, 2023,
                        EventName.SPECIAL_DISCOUNT, 1000,
                        EventName.GIFTAWAY_EVENT, Menu.CHAMPAGNE.getPrice());

        // When
        int amountToPay = cashDesk.calculateTotalBenefit(benefitSheet);

        // Then
        assertThat(amountToPay).isEqualTo(1000 + 2023 + 2023 + 1000 + Menu.CHAMPAGNE.getPrice());
    }
}