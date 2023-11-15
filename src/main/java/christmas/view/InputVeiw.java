package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.date.DateConverter;
import christmas.validator.Validator;

public class InputVeiw {
    public int askDateToVisit() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        Validator validator = new Validator();
        DateConverter converter = new DateConverter(validator);
        return converter.convertToDate(Console.readLine());
    }
}
