package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {
    private static final String EMPTY = "";
    @DisplayName("입력이 비었을 때 예외가 발생한다.")
    @Test
    void ensureNoEmptyInputTest() {
        // Given
        DateValidator dateValidator = new DateValidator();

        // When, Then
        assertThatThrownBy(() -> dateValidator.ensureNoEmptyInput(EMPTY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력값이 숫자가 아닌 값(문자, 공백)을 포함할 때 예외가 발생한다.")
    @ValueSource(strings = {"a", "1a", "1 2", " "})
    @ParameterizedTest
    void ensureOnlyNumberTest(String input) {
        // Given
        DateValidator dateValidator = new DateValidator();

        // When, Then
        assertThatThrownBy(() -> dateValidator.ensureOnlyNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("숫자가 1~31 사이의 수가 아닐 때 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    void ensureDateInRangeTest(int number) {
        // Given
        DateValidator dateValidator = new DateValidator();

        // When, Then
        assertThatThrownBy(() -> dateValidator.ensureDateInRange(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}