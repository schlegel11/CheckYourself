package ui;

import core.CalendarSheet;
import core.entities.CalendarDay;
import core.entities.Task;
import ui.components.DateLabel;
import ui.components.MonthButton;
import ui.components.TaskButton;
import ui.components.UIComponentFactory;
import ui.dialogs.ColorActionChooserDialog;
import ui.dialogs.DialogValues;
import ui.initialize.MenuTaskValues;
import ui.initialize.MonthButtonValues;
import ui.initialize.YearSliderValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * Class that represents the MainView.
 * Contains all the UI Elements.
 */
public class MainView implements IMainView {
    private final Collection<MonthButton> monthButtons = new ArrayList<>();
    private JFrame frame;
    private JMenuBar menue;
    private JPanel boxPanel;
    private JPanel box2Panel;
    private JPanel gridPanel;
    private JPanel flowPanel;
    private JMenuItem createTask;
    private JMenuItem exit;
    private JSlider yearSlider;
    private JButton todayButton;
    private Consumer<EntityWrapper<Task>> eventTaskButtonClicked;
    private Consumer<DialogValues> eventColorActionDialogOkClicked;

    // Erzeugt ein Fenster ud befüllt dieses.
    public void openFrame() {
        frame = new JFrame();
        frame.setBounds(400, 300, 900, 700);
        frame.setTitle("CheckYourself");
        frame.setVisible(true);

    }

    // Erzeugt die Menubar am oberen Rand des Fensters
    public void menue() {
        menue = new JMenuBar();
        frame.setJMenuBar(menue);
        JMenu dateiMenu = new JMenu("Datei");
        menue.add(dateiMenu);
        createTask = new JMenuItem("Aktion wählen");
        dateiMenu.add(createTask);
        exit = new JMenuItem("Beenden");
        dateiMenu.add(exit);
    }

    // Erstelle verschiedene Layouts für die Kalender Bereiche
    public void createLayout() {

        // Haupt Layout fuer das gesamte Fenster
        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout(30, 20));
        // FlowPanel fuer die Ueberschrift
        flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());

        // BoxPanel fue den Slider
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        // FlowPanel fuer die Kalender unterschrift
        JPanel flow2Panel = new JPanel();
        flow2Panel.setLayout(new FlowLayout());

        // BoxPanel fuer die Monats Buttons
        box2Panel = new JPanel();
        box2Panel.setLayout(new BoxLayout(box2Panel, BoxLayout.Y_AXIS));

        // GridPanel mit 7 Spalten fuer die Monatstage
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 7));

        frame.add(borderPanel);

        borderPanel.add(BorderLayout.NORTH, flowPanel);
        borderPanel.add(BorderLayout.WEST, boxPanel);
        borderPanel.add(BorderLayout.EAST, box2Panel);
        borderPanel.add(BorderLayout.CENTER, gridPanel);
        borderPanel.add(BorderLayout.SOUTH, flow2Panel);

        todayButton = new JButton("Heute");
        flow2Panel.add(todayButton);


    }

    // Hier wird die Ueberschrift erstellt
    public void createHeader(Year year) {
        flowPanel.removeAll();
        flowPanel.add(new JLabel("CheckYourself " + year.getValue()));
    }

    public void createDaysOfWeek(String name) {
        JButton tag = new JButton(name);
        tag.setOpaque(false);
        tag.setBackground(Color.LIGHT_GRAY);
        tag.setBorderPainted( false );
        tag.setFocusPainted( false );
        tag.setHorizontalAlignment(JLabel.CENTER);
        gridPanel.add(tag);
    }

    // Erstelle Slider zum aendern des jahres
    private void erstelleJahresSlider(YearSliderValues values) {
        yearSlider = new JSlider(JSlider.VERTICAL, values.getMinYearValue(), values.getMaxYearValue(),
                values.getInitYearValue());

        yearSlider.setMajorTickSpacing(10);
        yearSlider.setMinorTickSpacing(1);
        yearSlider.setPaintTicks(true);
        yearSlider.setPaintLabels(true);

        box2Panel.add(yearSlider);
    }

    @Override
    public void eventMonthButtonClicked(Consumer<Month> consumer) {

        for (MonthButton monatsButton : monthButtons) {
            Month month = monatsButton.getMonth();
            monatsButton.getComponent().addActionListener(arg0 -> consumer.accept(month));
        }
    }

    @Override
    public void eventTodayButtonClicked(Consumer<Void> consumer) {
        todayButton.addActionListener(e -> consumer.accept(null));
    }

    @Override
    public void eventCreateTaskButtonClicked(Consumer<Void> consumer) {
        createTask.addActionListener(l -> consumer.accept(null));
    }

    @Override
    public void eventTaskButtonClicked(Consumer<EntityWrapper<Task>> consumer) {
        eventTaskButtonClicked = consumer;
    }

    @Override
    public void createMenuTaskButton(EntityWrapper<Task> values) {
        TaskButton taskButton = UIComponentFactory.createNamedTaskButton(values);
        Component taskButtonComponent = taskButton.getComponent();
        taskButton.getComponent().addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!SwingUtilities.isRightMouseButton(e)) {
                    eventTaskButtonClicked.accept(taskButton.getTask());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
        menue.add(new JLabel(" | "));
        menue.add(taskButtonComponent);
        menue.updateUI();
    }

    @Override
    public void eventColorActionDialogOkClicked(Consumer<DialogValues> consumer) {
        eventColorActionDialogOkClicked = consumer;
    }

    @Override
    public void openColorActionChooserDialog() {
        ColorActionChooserDialog colorActionChooserDialog = new ColorActionChooserDialog("Aktion", 632, 460, frame);
        colorActionChooserDialog.buttonAction(ColorActionChooserDialog.ButtonAction.OK,
                dialog -> eventColorActionDialogOkClicked.accept(dialog.getValues()));
        colorActionChooserDialog.open();
    }

    @Override
    public void eventExitButtonClicked(Consumer<Void> consumer) {
        exit.addActionListener(l -> consumer.accept(null));
    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public void open(MonthButtonValues monthValues, YearSliderValues yearValues, MenuTaskValues taskValues) {
        openFrame();
        menue();
        createLayout();
        erstelleJahresSlider(yearValues);
        createHeader(yearValues.getInitYear());
        erstelleMonate(monthValues);
        taskValues.getTasks().forEach(this::createMenuTaskButton);
    }

    @Override
    public void eventYearSliderChanged(Consumer<Year> consumer) {
        yearSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            createHeader(Year.of(source.getValue()));
            consumer.accept(Year.of(source.getValue()));
        });
    }

    @Override
    public void eventWindowClosing(Consumer<Void> consumer) {
        frame.addWindowListener(new WindowListener() {

            public void windowClosing(WindowEvent e) {
                consumer.accept(null);
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

        });

    }

    // Erstelle Seitenleiste mit den Monats Buttons
    private void erstelleMonate(MonthButtonValues values) {
        for (Month month : values.getMonths()) {
            MonthButton monthButton = UIComponentFactory.createMonthButton(month);
            monthButtons.add(monthButton);
            boxPanel.add(monthButton.getComponent());
        }
    }

    @Override
    public void updateCalendarSheetDays(EntityWrapper<CalendarSheet> calendarSheet) {
        gridPanel.removeAll();

        createDaysOfWeek("Montag");
        createDaysOfWeek("Dienstag");
        createDaysOfWeek("Mittwoch");
        createDaysOfWeek("Donnerstag");
        createDaysOfWeek("Freitag");
        createDaysOfWeek("Samstag");
        createDaysOfWeek("Sonntag");

        for (CalendarDay day : calendarSheet.getEntity()) {
            DateLabel kalenderTag = UIComponentFactory.createDateOrTodayDateLabel(day.getDate(), day.toString(),
                    day.isToday());
            if (day.hasTasks()) {
                day.getTasks().forEach(task -> kalenderTag.addTaskValues(EntityWrapper.newEntityWrapper(task)));
            }
            gridPanel.add(kalenderTag.getComponent());
        }

        gridPanel.updateUI();
    }

    @Override
    public void updateYearSlider(Year year) {
        yearSlider.setValue(year.getValue());
        yearSlider.updateUI();
    }
}