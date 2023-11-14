package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.OrderDetails;
import christmas.domain.order.OrderValidator;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderDetailsTest {
    @DisplayName("예외가 발생한다.")
    @Test
    void createEmptyInputBetweenComma() {
        // Given
        OrderValidator validator = new OrderValidator();
        OrderDetails order = new OrderDetails(validator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable("양송이수프-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("콤마가 없을 때 어떻게 되나?")
    @Test
    void createNoComma() {
        // Given
        OrderValidator validator = new OrderValidator();
        OrderDetails order = new OrderDetails(validator);

        // When
        Map<String, Integer> orderTable = order.makeOrderTable("양송이수프-1");

        // Then
        assertThat(orderTable).isEqualTo(Arrays.asList("양송이수프-1",""));
    }

    @DisplayName("makeOrderTable테스트")
    @ValueSource(strings = {"", "양송이수프1", "양송이수프-1,", "양송이수프-1,,제로콜라-1", "양송이수프--1",
            "양송이수프-0", "양송이수프-a", "양송이수프-", "양송이수프- ", "호박수프-1", "양송이 수프-1",
            "제로콜라-1,레드와인-1", "양송이수프-10,티본스테이크-10,아이스크림-1", "시저샐러드-1,시저샐러드-1"})
    @ParameterizedTest
    void createErrorCase(String input) {
        // Given
        OrderValidator validator = new OrderValidator();
        OrderDetails order = new OrderDetails(validator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}