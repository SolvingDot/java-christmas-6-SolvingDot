package christmas;

import christmas.controller.EventPlannerController;
import christmas.view.InputVeiw;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputVeiw input = new InputVeiw();
        OutputView output = new OutputView();

        EventPlannerController eventPlannerController = new EventPlannerController(input, output);
        eventPlannerController.runPlanner();
    }
}
