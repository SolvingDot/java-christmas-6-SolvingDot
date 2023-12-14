package christmas.util;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static List<String> splitByComma(String input) {
        return Arrays.asList(input.split(Regex.COMMA.regex));
    }

    public static List<String> splitByDash(String input) {
        return Arrays.asList(input.split(Regex.DASH.regex));
    }


    public enum Regex {
        COMMA(","), DASH("-");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }

    private Util() {
    }
}
