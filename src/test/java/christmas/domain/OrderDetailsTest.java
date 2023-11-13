package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.OrderDetails;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailsTest {
    @DisplayName("예외가 발생한다.")
    @Test
    void createEmptyInputBetweenComma() {
        // Given
        OrderDetails order = new OrderDetails();

        // When, Then
        assertThatThrownBy(() -> order.splitInputToNameAndNumber("양송이수프-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("콤마가 없을 때 어떻게 되나?")
    @Test
    void createNoComma() {
        // Given
        OrderDetails order = new OrderDetails();

        // When
        List<String> splitInput = order.splitInputToNameAndNumber("양송이수프-1");

        // Then
        assertThat(splitInput).isEqualTo(Arrays.asList("양송이수프-1",""));
    }

    @DisplayName("또 다른 예외가 발생한다.")
    @Test
    void createEmptyInputBetweenDash() {
        // Given
        OrderDetails order = new OrderDetails();

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable("양송이수프--1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}