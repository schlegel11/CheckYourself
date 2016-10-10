package core;

import core.entities.CalendarDay;
import core.entities.Task;
import core.util.Dates;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The {@link CalendarSheet} contains logic for create a {@link CalendarSheet}
 * with {@link CalendarDay}s and {@link Task}s.
 */
public class CalendarSheet {

    private final LocalDate date;
    private List<CalendarDay> days = new ArrayList<>();

    public CalendarSheet(Month month, Year year) {
        this(month, year.getValue());
    }

    public CalendarSheet(Month month, int year) {
        date = LocalDate.of(year, month, 1);
        initialize();
    }

    private void initialize() {
        addEmpty(Dates.getDayOfWeekFromFirstDayOfMonth(getDate()));
        IntStream.rangeClosed(1, Dates.getLengthOfMonth(getDate()))
                .forEach(i -> addDay(new CalendarDay(getYear(), getMonth(), i)));
    }

    private boolean addDay(CalendarDay day) {
        return getDays().add(day);
    }

    private void addEmpty(int emptyDays) {
        IntStream.range(1, emptyDays).forEach(i -> getDays().add(new CalendarDay.EmptyDay()));
    }

    public boolean replaceDay(CalendarDay day) {
        if (day.equals(new CalendarDay(getYear(), getMonth(), day.getDay()))) {
            int emptyDays = Dates.getDayOfWeekFromFirstDayOfMonth(getDate());
            days.set((emptyDays + day.getDay()) - 2, day);
            return true;
        }
        return false;
    }

    public void replaceDays(Collection<CalendarDay> days) {
        days.forEach(this::replaceDay);
    }

    public Collection<CalendarDay> getDaysWithTask() {
        return getDays().stream().filter(CalendarDay::hasTasks).collect(Collectors.toList());
    }

    public Collection<CalendarDay> getDays() {
        return days;
    }

    public LocalDate getDate() {
        return date;
    }

    public Month getMonth() {
        return date.getMonth();
    }

    public int getYear() {
        return date.getYear();
    }
}
