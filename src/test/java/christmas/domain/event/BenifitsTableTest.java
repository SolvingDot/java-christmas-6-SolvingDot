package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBenefits;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BenifitsTableTest {
    private static final int CHRISTMAS = 25;
    private static final int ONE_MENU = 1;

    @DisplayName("총 주문 금액이 10,000원 미만이면 이벤트를 적용하지 않는다.")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "아이스크림", "제로콜라"}) // 10,000원 미만 메뉴들
    @ParameterizedTest
    void createTotalOrderAmountIsLessThanTenThousands(String menuName) {
        // Given
        BenifitsTable event = new BenifitsTable();

        // When
        Map<String, Integer> benefitsTable = event.applyEvents(CHRISTMAS, Map.of(menuName, ONE_MENU));

        // Then
        assertThat(benefitsTable.values()).containsOnly(EventBenefits.NOTHING.getBenefit());
    }
}