package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBadge;
import christmas.domain.event.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {
    @DisplayName("총 혜택 금액이 2만원 이상이면 산타 배지를 증정한다.")
    @ValueSource(ints = {20_000, 20_001})
    @ParameterizedTest
    void presentSantaBadge_WhenBenefitAmountIsInRequiredRange(int benefitAmount) {
        // Given
        Badge badge = new Badge();

        // When
        String badgeType = badge.checkForBadge(benefitAmount);

        // Then
        assertThat(badgeType).isEqualTo(EventBadge.SANTA.getBadge());
    }

    @DisplayName("총 혜택 금액이 1만원 이상, 2만원 미만이면 트리 배지를 증정한다.")
    @ValueSource(ints = {10_000, 10_001, 19_999})
    @ParameterizedTest
    void presentTreeBadge_WhenBenefitAmountIsInRequiredRange(int benefitAmount) {
        // Given
        Badge badge = new Badge();

        // When
        String badgeType = badge.checkForBadge(benefitAmount);

        // Then
        assertThat(badgeType).isEqualTo(EventBadge.TREE.getBadge());
    }

    @DisplayName("총 혜택 금액이 5천원 이상, 1만원 미만이면 별 배지를 증정한다.")
    @ValueSource(ints = {5_000, 5_001, 9_999})
    @ParameterizedTest
    void presentStarBadge_WhenBenefitAmountIsInRequiredRange(int benefitAmount) {
        // Given
        Badge badge = new Badge();

        // When
        String badgeType = badge.checkForBadge(benefitAmount);

        // Then
        assertThat(badgeType).isEqualTo(EventBadge.STAR.getBadge());
    }

    @DisplayName("총 혜택 금액이 5천원 미만이면 배지를 증정하지 않는다.")
    @ValueSource(ints = {0, 1, 4_999})
    @ParameterizedTest
    void presentNothing_WhenBenefitAmountIsLessThanMinimumRequiredAmount(int benefitAmount) {
        // Given
        Badge badge = new Badge();

        // When
        String badgeType = badge.checkForBadge(benefitAmount);

        // Then
        assertThat(badgeType).isEqualTo(EventBadge.NOTHING.getBadge());
    }
}