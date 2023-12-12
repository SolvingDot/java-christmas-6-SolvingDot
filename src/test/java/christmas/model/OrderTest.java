package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {
    @DisplayName("빈칸, 메뉴 형식이 다른 경우, 메뉴 개수가 1 이상 숫자가 아닌 경우 -> 예외 발생")
    @ValueSource(strings = {"", "양송이수프1", "1-양송이수프", "양송이수프-1, 레드와인-1", "양송이수프-0"})
    @ParameterizedTest
    void 형식_예외_발생_테스트(String input) {
        // Given
        Order order = new Order();

        // When, Then
        assertThatThrownBy(() -> order.read(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 이름, 중복된 메뉴 -> 예외 발생")
    @ValueSource(strings = {"소고기수프-1", "양송이수프-1,양송이수프-1"})
    @ParameterizedTest
    void 내용_예외_발생_테스트(String input) {
        // Given
        Order order = new Order();

        // When, Then
        assertThatThrownBy(() -> order.read(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력값에서 메뉴 이름과 개수를 나누고, 개수를 숫자로 변환")
    @Test
    void 기능_테스트() {
        // Given
        Order order  = new Order();

        // When
        Map<String, Integer> orderSheet = order.read("양송이수프-3");

        // Then
        assertThat(orderSheet).isEqualTo(Map.of("양송이수프", 3));
    }
}