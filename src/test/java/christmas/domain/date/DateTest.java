package christmas.domain.date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.validator.DateValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {
    @DisplayName("날짜가 1~31일 범위를 벗어나면 예외를 발생시킨다.")
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    void createDateOverTheRange(int dateOfVisit) {
        // Given
        DateValidator dateValidator = new DateValidator();

        // When, Then
        assertThatThrownBy(() -> new Date(dateOfVisit, dateValidator))
                .isInstanceOf(IllegalArgumentException.class);
    }
}