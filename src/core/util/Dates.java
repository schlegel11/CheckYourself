package core.util;

import java.time.LocalDate;

public final class Dates {

    private Dates() {
    }

    public static int getDayOfWeekFromFirstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1).getDayOfWeek().getValue();
    }

    public static int getLengthOfMonth(LocalDate date) {
        return date.getMonth().length(date.isLeapYear());
    }
}
