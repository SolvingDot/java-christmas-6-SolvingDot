package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventManagerTest {
    @DisplayName("총 주문 금액이 10,000원 미만이면 이벤트를 적용하지 않는다.")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "아이스크림", "제로콜라"}) // 10,000원 미만 메뉴들
    @ParameterizedTest
    void createTotalOrderAmountIsLessThanTenThousands(String menuName) {
        // Given
        EventManager event = new EventManager();

        // When
        Map<String, Integer> benefitsTable = event.applyEvents(25, Map.of(menuName, 1));

        // Then
        assertThat(benefitsTable.values()).containsOnly(0);
    }
}