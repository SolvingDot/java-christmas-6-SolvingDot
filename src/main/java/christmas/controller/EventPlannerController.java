package christmas.controller;

import christmas.domain.date.Date;
import christmas.domain.event.BenifitsTable;
import christmas.domain.order.OrderTable;
import christmas.validator.DateValidator;
import christmas.validator.OrderValidator;
import christmas.view.InputVeiw;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlannerController {
    private final InputVeiw input;
    private final OutputView output;

    public EventPlannerController(InputVeiw input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void runPlanner() {
        DateValidator dateValidator = new DateValidator();
        OrderValidator orderValidator = new OrderValidator();

        input.introducePlanner();

        Date date = readExpectedVisitDate(dateValidator);
        Map<String, Integer> orderTable = takeOrder(orderValidator);
        Map<String, Integer> benefitTable = applyEveryEvent(date, orderTable);

        output.printPlannerOutView(date, orderTable, benefitTable);
    }

    private Date readExpectedVisitDate(DateValidator dateValidator) {
        Date date = null;
        return input.askToDateToVisit(input, date, dateValidator);
    }

    private Map<String, Integer> takeOrder(OrderValidator orderValidator) {
        OrderTable order = new OrderTable(orderValidator);
        Map<String, Integer> orderTable = null;
        return input.askToOrder(input, order, orderTable);
    }

    private static Map<String, Integer> applyEveryEvent(Date date, Map<String, Integer> orderTable) {
        BenifitsTable benifitsTable = new BenifitsTable();
        return benifitsTable.applyEvents(date.getDate(), orderTable);
    }
}
