package christmas;

import christmas.controller.EventPlannerController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView input = new InputView();
        OutputView output = new OutputView();

        EventPlannerController eventPlannerController = new EventPlannerController(input, output);
        eventPlannerController.runPlanner();
    }
}
