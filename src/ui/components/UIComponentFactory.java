package ui.components;

import core.entities.Task;
import ui.EntityWrapper;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Factory {@link UIComponentFactory} for creating specific UI elements.
 */
public final class UIComponentFactory {

    public static MonthButton createMonthButton(Month month) {
        return new MonthButton(month, month.getDisplayName(TextStyle.FULL, Locale.getDefault()));
    }

    public static TaskButton createTaskButton(EntityWrapper<Task> values) {
        return new TaskButton(values);
    }

    public static TaskButton createNamedTaskButton(EntityWrapper<Task> values) {
        return new TaskButton(values, values.getEntity().getName());
    }

    public static DateLabel createDateOrTodayDateLabel(LocalDate localDate, String text, boolean isTodayDateLabel) {
        return isTodayDateLabel ? createTodayDateLabel(localDate, text) : createDateLabel(localDate, text);
    }

    public static DateLabel createDateLabel(LocalDate localDate, String text) {
        return new DateLabel(new JLabel(text));
    }

    public static DateLabel createTodayDateLabel(LocalDate localDate, String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 2, Color.GRAY));
        label.setBackground(Color.CYAN);
        label.setOpaque(true);
        return new DateLabel(label);
    }
}
