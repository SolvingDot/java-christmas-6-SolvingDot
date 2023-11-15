package christmas;

import christmas.domain.event.BenifitsTable;
import christmas.domain.order.OrderTable;
import christmas.validator.Validator;
import christmas.view.InputVeiw;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        String input_date = "25";
        String input_order = "양송이수프-2,티본스테이크-1,제로콜라-2";

        InputVeiw input = new InputVeiw();
        input.askDateToVisit();

        Validator validator = new Validator();
        OrderTable order = new OrderTable(validator);
        Map<String, Integer> orderTable = order.makeOrderTable(input_order);

        BenifitsTable manager = new BenifitsTable();


        System.out.println(orderTable);
    }
}
