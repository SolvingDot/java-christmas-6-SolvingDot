package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.Menu;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderCalculatorTest {
    @DisplayName("메뉴 이름이 메뉴판에 없으면 예외를 발생시킨다.")
    @Test
    void createMenuNameIsNotInMenu() {
        // Given
        OrderCalculator calculator = new OrderCalculator();

        // When, Then
        assertThatThrownBy(() ->
                calculator.calculateTotalOrderAmount(Map.of("야채수프", 2, "찹스테이크", 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문할 메뉴 이름과 개수에 따라 총 주문 금액을 계산한다.")
    @Test
    void calculateTotalOrderAmount_WhenMenuNameIsInMenu() {
        // Given
        OrderCalculator calculator = new OrderCalculator();

        // When
        int totalOrderAmount = calculator.calculateTotalOrderAmount(
                Map.of(Menu.MUSHROOM_SOUP.getName(), 2, Menu.T_BONE_STEAK.getName(), 1)
        );

        // Then
        assertThat(totalOrderAmount)
                .isEqualTo(Menu.MUSHROOM_SOUP.getPrice() * 2 + Menu.T_BONE_STEAK.getPrice() * 1);
    }
}