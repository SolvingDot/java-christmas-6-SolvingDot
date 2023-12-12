package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.message.InputMessage;
import christmas.domain.date.Date;
import christmas.domain.date.DateConverter;
import christmas.domain.order.OrderTable;
import christmas.validator.DateValidator;
import java.util.Map;

public class InputView {
    public void introducePlanner() {
        System.out.println(InputMessage.INTRO.getMessage());
    }

    public Date askToDateToVisit(Date date, DateValidator dateValidator) {
        while (true) {
            try {
                date = new Date(askDateToVisitOnce(), dateValidator);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    private int askDateToVisitOnce() {
        System.out.println(InputMessage.ASK_DATE.getMessage());
        DateValidator dateValidator = new DateValidator();
        DateConverter converter = new DateConverter(dateValidator);
        return converter.convertToDate(Console.readLine());
    }

    public Map<String, Integer> askToOrder(OrderTable order, Map<String, Integer> orderTable) {
        while (true) {
            try {
                orderTable = order.makeOrderTable(askToOrderOnce());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderTable;
    }

    private String askToOrderOnce() {
        System.out.println(InputMessage.ASK_TO_ORDER.getMessage());
        return Console.readLine();
    }
}
