package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.Menu;
import java.util.Map;
import org.junit.jupiter.api.Test;

class OrderCalculaorTest {
    @Test
    void calculate() {
        // Given
        OrderCalculaor orderCalculaor = new OrderCalculaor();

        // When
        int totalOrderAmount = orderCalculaor.calculateTotalOrderAmount(Map.of("양송이수프", 2, "티본스테이크", 1));

        // Then
        assertThat(totalOrderAmount)
                .isEqualTo(Menu.MUSHROOM_SOUP.getPrice() * 2 + Menu.T_BONE_STEAK.getPrice() * 1);
    }
}