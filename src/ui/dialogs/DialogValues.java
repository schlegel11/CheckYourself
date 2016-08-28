package ui.dialogs;

import java.awt.*;

/**
 * The {@link DialogValues} represents specific values for the
 * {@link ColorActionChooserDialog}.
 */
public class DialogValues {
    private final Color taskColor;
    private final String taskName;

    public DialogValues(Color taskColor, String taskName) {
        this.taskColor = taskColor;
        this.taskName = taskName;
    }

    public Color getTaskColor() {
        return taskColor;
    }

    public String getTaskName() {
        return taskName;
    }
}
