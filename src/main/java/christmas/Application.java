package christmas;

import christmas.domain.date.Date;
import christmas.domain.event.BenifitsTable;
import christmas.domain.order.OrderTable;
import christmas.validator.DateValidator;
import christmas.validator.OrderValidator;
import christmas.view.InputVeiw;
import christmas.view.OutputView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputVeiw input = new InputVeiw();
        OutputView output = new OutputView();
        DateValidator dateValidator = new DateValidator();
        OrderValidator orderValidator = new OrderValidator();

        Date date = null;
        date = input.askToDateToVisit(input, date, dateValidator);

        OrderTable order = new OrderTable(orderValidator);
        Map<String, Integer> orderTable = null;
        orderTable = input.askToOrder(input, order, orderTable);
        output.printOrderedMenu(orderTable);
        output.printTotalOrderPrice(orderTable);

        BenifitsTable benifitsTable = new BenifitsTable();
        Map<String, Integer> benefitTable = benifitsTable.applyEvents(date.getDate(), orderTable);

        output.printGiftBenefit(benefitTable);
        output.printBenefitDetails(benefitTable);
        output.printTotalBenefitAmount(benefitTable);
        output.printPaymentAmountAfterDiscount(orderTable, benefitTable);
        output.printEventBadge(benefitTable);
    }
}
