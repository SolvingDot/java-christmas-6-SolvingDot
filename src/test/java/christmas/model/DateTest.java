package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {
    @DisplayName("빈칸, 숫자 아님, 범위 벗어남 -> 예외 발생")
    @ValueSource(strings = {"", "abc", "1 1", "0", "32"})
    @ParameterizedTest
    void 예외_발생_테스트(String input) {
        // Given
        Date date = new Date();

        // When, Then
        assertThatThrownBy(() -> date.read(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("문자열을 숫자로 변환")
    @Test
    void 기능_테스트() {
        // Given
        Date date = new Date();

        // When
        int visitDate = date.read("25");

        // Then
        assertThat(visitDate).isEqualTo(25);
    }
}