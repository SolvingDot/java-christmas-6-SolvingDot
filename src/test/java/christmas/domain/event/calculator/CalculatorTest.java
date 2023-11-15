package christmas.domain.event.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventBenefits;
import christmas.domain.order.OrderPrice;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @DisplayName("샴페인 증정 안 받았을 때 : 할인 후 예상 결제 금액 = 총 주문 금액 - 혜택 금액")
    @Test
    void createPaymentAfterDiscountWhenNoGift() {
        // Given
        Calculator calculator = new Calculator();
        OrderPrice orderPrice = new OrderPrice();
        Map<String, Integer> orderTable = Map.of("양송이수프", 1, "티본스테이크", 1, "초코케이크", 1);
        Map<String, Integer> benefitsTable = Map.of(
                EventBenefits.DDAY_DISCOUNT.getDetail(), EventBenefits.DDAY_DISCOUNT.getBenefit(),
                EventBenefits.WEEKDAY_DISCOUNT.getDetail(), EventBenefits.WEEKDAY_DISCOUNT.getBenefit(),
                EventBenefits.WEEKEND_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit(),
                EventBenefits.SPECIAL_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit(),
                EventBenefits.FREE_CHAMPAGNE.getDetail(), EventBenefits.NOTHING.getBenefit()
        );
        int totalOrderAmount = orderPrice.calculateTotalOrderPrice(orderTable);

        // When
        int benefitsAmount = calculator.calculateTotalBenefitAmount(benefitsTable);
        int paymentAmount = calculator.calculatePaymentAmount(orderTable, benefitsTable);

        // Then
        assertThat(paymentAmount).isEqualTo(totalOrderAmount - benefitsAmount);
    }

    @DisplayName("샴페인 증정 받았을 때 : 할인 후 예상 결제 금액 = 총 주문 금액 - 할인 금액(혜택 금액에서 샴페인 가격 제외)")
    @Test
    void createPaymentAfterDiscountWithGift() {
        // Given
        Calculator calculator = new Calculator();
        OrderPrice orderPrice = new OrderPrice();
        Map<String, Integer> orderTable = Map.of("양송이수프", 1, "티본스테이크", 3, "초코케이크", 1);
        Map<String, Integer> benefitsTable = Map.of(
                EventBenefits.DDAY_DISCOUNT.getDetail(), EventBenefits.DDAY_DISCOUNT.getBenefit(),
                EventBenefits.WEEKDAY_DISCOUNT.getDetail(), EventBenefits.WEEKDAY_DISCOUNT.getBenefit(),
                EventBenefits.WEEKEND_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit(),
                EventBenefits.SPECIAL_DISCOUNT.getDetail(), EventBenefits.NOTHING.getBenefit(),
                EventBenefits.FREE_CHAMPAGNE.getDetail(), EventBenefits.FREE_CHAMPAGNE.getBenefit()
        );
        int totalOrderAmount = orderPrice.calculateTotalOrderPrice(orderTable);

        // When
        int benefitsAmount = calculator.calculateTotalBenefitAmount(benefitsTable);
        int paymentAmount = calculator.calculatePaymentAmount(orderTable, benefitsTable);

        // Then
        assertThat(paymentAmount)
                .isEqualTo(totalOrderAmount - benefitsAmount + EventBenefits.FREE_CHAMPAGNE.getBenefit());
    }

    @DisplayName("모든 이벤트 혜택 금액을 더하면 총 이벤트 혜택 금액이다.")
    @Test
    void totalBenefitAmountTest() {
        // Given
        Calculator calculator = new Calculator();
        Map<String, Integer> benefitsTable = Map.of(
                EventBenefits.DDAY_DISCOUNT.getDetail(), EventBenefits.DDAY_DISCOUNT.getBenefit(),
                EventBenefits.WEEKDAY_DISCOUNT.getDetail(), EventBenefits.WEEKDAY_DISCOUNT.getBenefit(),
                EventBenefits.WEEKEND_DISCOUNT.getDetail(), EventBenefits.WEEKEND_DISCOUNT.getBenefit(),
                EventBenefits.SPECIAL_DISCOUNT.getDetail(), EventBenefits.SPECIAL_DISCOUNT.getBenefit(),
                EventBenefits.FREE_CHAMPAGNE.getDetail(), EventBenefits.FREE_CHAMPAGNE.getBenefit()
        );

        // When
        int benefitsAmount = calculator.calculateTotalBenefitAmount(benefitsTable);

        // Then
        assertThat(benefitsAmount).isEqualTo(EventBenefits.DDAY_DISCOUNT.getBenefit()
                + EventBenefits.WEEKDAY_DISCOUNT.getBenefit()
                + EventBenefits.WEEKEND_DISCOUNT.getBenefit()
                + EventBenefits.SPECIAL_DISCOUNT.getBenefit()
                + EventBenefits.FREE_CHAMPAGNE.getBenefit());
    }
}