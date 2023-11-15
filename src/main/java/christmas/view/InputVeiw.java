package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.date.Date;
import christmas.domain.date.DateConverter;
import christmas.domain.order.OrderTable;
import christmas.validator.DateValidator;
import java.util.Map;

public class InputVeiw {
    public Date askToDateToVisit(InputVeiw input, Date date, DateValidator dateValidator) {
        while (true) {
            try {
                date = new Date(input.askDateToVisitOnce(), dateValidator);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    private int askDateToVisitOnce() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        DateValidator dateValidator = new DateValidator();
        DateConverter converter = new DateConverter(dateValidator);
        return converter.convertToDate(Console.readLine());
    }

    public Map<String, Integer> askToOrder(InputVeiw input, OrderTable order, Map<String, Integer> orderTable) {
        while (true) {
            try {
                orderTable = order.makeOrderTable(input.askToOrderOnce());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderTable;
    }

    private String askToOrderOnce() {
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }
}
