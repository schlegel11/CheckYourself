package core;

import core.entities.CalendarDay;
import core.entities.Task;
import core.util.Serializables;
import ui.EntityWrapper;
import ui.IMainView;
import ui.dialogs.DialogValues;
import ui.initialize.MenuTaskValues;
import ui.initialize.MonthButtonValues;
import ui.initialize.YearSliderValues;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@link Presenter} holds all the logic for the UI.
 */
public class Presenter {

    private final List<CalendarDay> days = new ArrayList<>();
    private final IMainView mainView;
    private Year year;
    private Month month;

    public Presenter(IMainView mainView) {
        this.mainView = mainView;

        loadSaveFile();
        initializeCalendarValues();
        bindEvents();
    }

    private void initializeCalendarValues() {
        MonthButtonValues monthButtonValues = new MonthButtonValues(createMonths());
        YearSliderValues yearSliderValues = new YearSliderValues(2016, 2099, 2016);

        Set<Task> tasks = days.stream().map(CalendarDay::getTasks).flatMap(Collection::stream).collect(Collectors.toSet());
        MenuTaskValues taskValues = new MenuTaskValues(EntityWrapper.entitiesToNewEntityWrapperCollection(tasks));

        year = yearSliderValues.getInitYear();
        month = monthButtonValues.getFirstMonth();

        mainView.open(monthButtonValues, yearSliderValues, taskValues);
        updateCalendarSheet(month, year, days);
    }

    private void bindEvents() {
        mainView.eventCreateTaskButtonClicked(v -> eventCreateTaskButtonClicked());
        mainView.eventExitButtonClicked(v -> exitButtonClicked());
        mainView.eventMonthButtonClicked(this::monthButtonClicked);
        mainView.eventTodayButtonClicked(v -> todayButtonClicked());
        mainView.eventWindowClosing(v -> windowClosing());
        mainView.eventYearSliderChanged(this::yearSliderChanged);
        mainView.eventColorActionDialogOkClicked(this::eventColorActionDialogOkClicked);
        mainView.eventTaskButtonClicked(this::eventTaskButtonClicked);
    }

    private void loadSaveFile() {
        if (Files.exists(Paths.get("checkyourself.sav"))) {
            days.addAll(Serializables.deserializeFromFile(Paths.get("checkyourself.sav")));
        }
    }

    private void updateCalendarSheet(Month month, Year year, Collection<CalendarDay> days) {
        CalendarSheet calendarSheet = new CalendarSheet(month, year);
        calendarSheet.replaceDays(days);
        updateCalendarSheet(calendarSheet);
    }

    private void updateCalendarSheet(CalendarSheet calendarSheet) {
        mainView.updateCalendarSheetDays(EntityWrapper.entitiesToNewEntityWrapperCollection(calendarSheet.getDays()));
    }

    private void eventCreateTaskButtonClicked() {
        mainView.openColorActionChooserDialog();
    }

    private void eventTaskButtonClicked(EntityWrapper<Task> values) {
        CalendarDay calendarDay = new CalendarDay(LocalDate.now());
        calendarDay = days.contains(calendarDay) ? days.stream().filter(p -> p.equals(new CalendarDay(LocalDate.now()))).findFirst().get() : calendarDay;
        if (!calendarDay.containsTask(values.getEntity())) {
            calendarDay.addTask(values.getEntity());
            days.add(calendarDay);
            updateCalendarSheet(month, year, days);
        }
    }

    private void exitButtonClicked() {
        Serializables.serializeToFile(Paths.get("checkyourself.sav"), days);
        mainView.close();
    }

    private void monthButtonClicked(Month month) {
        this.month = month;
        updateCalendarSheet(month, year, days);
    }

    private void todayButtonClicked() {
        LocalDate date = LocalDate.now();
        month = date.getMonth();
        year = Year.of(date.getYear());
        updateCalendarSheet(month, year, days);
        mainView.updateYearSlider(year);
    }

    private void windowClosing() {
        Serializables.serializeToFile(Paths.get("checkyourself.sav"), days);
        mainView.close();
    }

    private void yearSliderChanged(Year year) {
        this.year = year;
        updateCalendarSheet(month, year, days);
    }

    private void eventColorActionDialogOkClicked(DialogValues values) {
        Task task = new Task(values.getTaskName(), values.getTaskColor());
        mainView.createMenuTaskButton(EntityWrapper.newEntityWrapper(task));
    }

    private Collection<Month> createMonths() {
        List<Month> monate = new ArrayList<>(Arrays.asList(Month.values()));
        Collections.rotate(monate, -LocalDate.now().getMonthValue() + 1);
        return monate;
    }

}
