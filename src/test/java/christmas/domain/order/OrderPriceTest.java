package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.Menu;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderPriceTest {
    @DisplayName("주문할 메뉴 이름과 개수에 따라 총 주문 금액을 계산한다.")
    @Test
    void calculateTotalOrderAmount_WhenMenuNameIsInMenu() {
        // Given
        OrderPrice calculator = new OrderPrice();

        // When
        int totalOrderAmount = calculator.calculateTotalOrderPrice(
                Map.of(Menu.MUSHROOM_SOUP.getName(), 2, Menu.T_BONE_STEAK.getName(), 1)
        );

        // Then
        assertThat(totalOrderAmount)
                .isEqualTo(Menu.MUSHROOM_SOUP.getPrice() * 2 + Menu.T_BONE_STEAK.getPrice() * 1);
    }
}