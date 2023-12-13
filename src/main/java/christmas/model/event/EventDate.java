package christmas.model.event;

import java.util.Arrays;
import java.util.List;

public class EventDate {
    // 날짜
    public static final int FIRST_DAY = 1;
    public static final int LAST_DAY = 31;
    public static final int CHRISTMAS = 25;

    // 할인 기간
    public static final List<Integer> WEEKEND = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    public static final List<Integer> SPECIAL_DAY = Arrays.asList(3, 10, 17, 24, 25, 31);
}
