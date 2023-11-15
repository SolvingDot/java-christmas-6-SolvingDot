package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.OrderTable;
import christmas.validator.OrderValidator;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTableTest {
    @DisplayName("입력된 주문을 메뉴 이름과 주문 개수로 짝 지어진 표로 정리한다.")
    @Test
    void makeOrderTableTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        OrderTable order = new OrderTable(orderValidator);

        // When
        Map<String, Integer> orderTable = order.makeOrderTable("양송이수프-1,티본스테이크-2");

        // Then
        assertThat(orderTable).isEqualTo(Map.of("양송이수프", 1, "티본스테이크", 2));
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외 처리한다.")
    @ValueSource(strings = {"호박수프-1", "양송이스프-1", "등심스테이크-2"})
    @ParameterizedTest
    void createErrorCase(String input) {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        OrderTable order = new OrderTable(orderValidator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("메뉴의 개수는 1 이상의 숫자가 아니면 예외 처리한다.")
    @ValueSource(strings = {"양송이수프-0", "양송이수프-a", "양송이수프-", "양송이수프- "})
    @ParameterizedTest
    void createErrorCase1(String input) {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        OrderTable order = new OrderTable(orderValidator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우 예외 처리한다.")
    @ValueSource(strings = {"", "양송이수프1", "양송이수프-1,", "양송이수프-1,,제로콜라-1", "양송이수프--1",
            "제로콜라-1,레드와인-1", "양송이수프-10,티본스테이크-10,아이스크림-1", "시저샐러드-1,시저샐러드-1"})
    @ParameterizedTest
    void createErrorCase2(String input) {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        OrderTable order = new OrderTable(orderValidator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("음료만 주문할 경우 예외 처리한다.")
    @ValueSource(strings = {"제로콜라-1,레드와인-1", "샴페인-1"})
    @ParameterizedTest
    void createErrorCase3(String input) {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        OrderTable order = new OrderTable(orderValidator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("메뉴가 20개를 초과하면 예외 처리한다.")
    @ValueSource(strings = {"양송이수프-10,티본스테이크-10,아이스크림-1", "시저샐러드-21"})
    @ParameterizedTest
    void createErrorCase4(String input) {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        OrderTable order = new OrderTable(orderValidator);

        // When, Then
        assertThatThrownBy(() -> order.makeOrderTable(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}