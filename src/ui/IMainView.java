package ui;

import core.CalendarSheet;
import core.entities.Task;
import ui.dialogs.DialogValues;
import ui.initialize.MenuTaskValues;
import ui.initialize.MonthButtonValues;
import ui.initialize.YearSliderValues;

import java.time.Month;
import java.time.Year;
import java.util.function.Consumer;

/**
 * Interface for delegate the UI calls to a specific logic class.
 * Holds all the UI event delegates and methods.
 */
public interface IMainView {
    void eventMonthButtonClicked(Consumer<Month> consumer);

    void eventTodayButtonClicked(Consumer<Void> consumer);

    void eventCreateTaskButtonClicked(Consumer<Void> consumer);

    void eventTaskButtonClicked(Consumer<EntityWrapper<Task>> consumer);

    void eventColorActionDialogOkClicked(Consumer<DialogValues> consumer);

    void eventExitButtonClicked(Consumer<Void> consumer);

    void eventYearSliderChanged(Consumer<Year> consumer);

    void eventWindowClosing(Consumer<Void> consumer);

    void openColorActionChooserDialog();

    void createMenuTaskButton(EntityWrapper<Task> values);

    void updateCalendarSheetDays(EntityWrapper<CalendarSheet> calendarSheet);

    void updateYearSlider(Year year);

    void open(MonthButtonValues monthValues, YearSliderValues yearValues, MenuTaskValues taskValues);

    void close();
}
