package christmas;

import christmas.domain.event.EventManager;
import christmas.domain.order.Order;
import christmas.domain.order.OrderCalculator;
import christmas.domain.order.OrderDetails;
import christmas.domain.order.OrderValidator;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        String input_date = "25";
        String input_order = "양송이수프-2,티본스테이크-1,제로콜라-2";

        OrderValidator orderValidator = new OrderValidator();
        OrderDetails order = new OrderDetails(orderValidator);
        Map<String, Integer> orderTable = order.makeOrderTable(input_order);

        EventManager manager = new EventManager();


        System.out.println(orderTable);
    }
}
