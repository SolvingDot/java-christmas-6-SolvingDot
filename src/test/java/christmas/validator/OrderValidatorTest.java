package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderValidatorTest {
    private static final String EMPTY = "";
    @DisplayName("입력이 비었을 때 예외가 발생한다.")
    @Test
    void ensureNoEmptyInputTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureNoEmptyInput(EMPTY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력에 '-'가 포함되지 않을 때 예외가 발생한다.")
    @Test
    void ensureInputHasDashTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureInputHasDash("양송이수프=1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력이 빈 요소가 포함될 때 예외가 발생한다.")
    @Test
    void ensureNoEmptyPlaceTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureNoEmptyPlace(List.of("양송이수프", "", "1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력값이 숫자가 아닌 값(문자, 공백)을 포함할 때 예외가 발생한다.")
    @ValueSource(strings = {"a", "1a", "1 2", " "})
    @ParameterizedTest
    void ensureOnlyNumberTest(String input) {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureOnlyNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력된 숫자가 1보다 작을 때 예외가 발생한다.")
    @ValueSource(ints = {-1, 0})
    @ParameterizedTest
    void ensureNumberOfMenuMoreThanOneTest(int number) {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureNumberOfMenuMoreThanOne(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력값이 메뉴에 포함되지 않을 때 예외가 발생한다.")
    @Test
    void ensureNameIsOnTheMenuTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureNameIsOnTheMenu("호박수프"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력값이 이미 목록에 존재하는 값일 때 예외가 발생한다.")
    @Test
    void ensureNameIsNotDuplicatedTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();
        Map<String, Integer> orderTable = Map.of("시저샐러드", 1, "양송이수프", 2);

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureNameIsNotDuplicated("시저샐러드", orderTable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력된 숫자가 20보다 크면 예외가 발생한다.")
    @Test
    void ensureTotalNumberOfMenuIsLimitedTwentyTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureTotalNumberOfMenuIsLimitedTwenty(21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력된 목록에 '음료'만 있을 때 예외가 발생한다.")
    @Test
    void ensureThereAreNotOnlyDrinksTest() {
        // Given
        OrderValidator orderValidator = new OrderValidator();

        // When, Then
        assertThatThrownBy(() -> orderValidator.ensureThereAreNotOnlyDrinks(List.of("음료", "음료", "음료")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}