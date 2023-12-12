package christmas.domain.date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.validator.DateValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateConverterTest {
    private static final String CHRISTMAS_DATE_TEXT = "25";
    private static final int CHRISTMAS_DATE = 25;

    @DisplayName("방문할 날짜에 숫자가 아닌 값을 입력하면 예외를 발생한다")
    @ValueSource(strings = {"", " ", "a"})
    @ParameterizedTest
    void createNonNumericValue(String input) {
        // Given
        DateValidator dateValidator = new DateValidator();
        DateConverter converter = new DateConverter(dateValidator);

        // When, Then
        assertThatThrownBy(() -> converter.convertToDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("문자열로 입력된 날짜를 Date클래스에서 활용할 수 있게 숫자로 변환한다.")
    @Test
    void convertToDateTest() {
        // Given
        DateValidator dateValidator = new DateValidator();
        DateConverter converter = new DateConverter(dateValidator);

        // When
        int date = converter.convertToDate(CHRISTMAS_DATE_TEXT);

        // Then
        assertThat(date).isEqualTo(CHRISTMAS_DATE);
    }
}