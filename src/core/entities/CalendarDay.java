package core.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;

public class CalendarDay implements Serializable {

    private final LocalDate date;
    private Collection<Task> tasks = new ArrayList<>();

    public CalendarDay(LocalDate date) {
        this.date = date;
    }

    public CalendarDay(int year, Month month, int dayOfMonth) {
        this(LocalDate.of(year, month, dayOfMonth));
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    public boolean isToday() {
        return date.equals(LocalDate.now());
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return String.valueOf(getDay());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CalendarDay other = (CalendarDay) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        return true;
    }


    public static class EmptyDay extends CalendarDay {

        public EmptyDay() {
            super(null);
        }

        @Override
        public boolean addTask(Task task) {
            return false;
        }

        @Override
        public boolean isToday() {
            return false;
        }

        @Override
        public String toString() {
            return "";
        }

    }
}
