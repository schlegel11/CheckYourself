package ui.components;

import core.entities.Task;
import ui.EntityWrapper;
import ui.components.template.AbstractComponent;
import ui.components.template.DataButton;

import java.awt.*;

/**
 * Specific {@link TaskButton} that can contain {@link Task} object.
 */
public class TaskButton extends AbstractComponent<DataButton<EntityWrapper<Task>>> {

    TaskButton(EntityWrapper<Task> values) {
        this(values, "");
    }

    TaskButton(EntityWrapper<Task> values, String taskName) {
        super(new DataButton<>(values, taskName));
        initialize();
    }

    public EntityWrapper<Task> getTask() {
        return component.getData();
    }

    public void setTask(EntityWrapper<Task> values) {
        component.setData(values);
    }

    public void setMaximumSize(Dimension maximumSize) {
        component.setMaximumSize(maximumSize);
    }

    public void setToolTipText(String text) {
        component.setToolTipText(text);
    }

    @Override
    protected void initialize() {
        component.setBackground(getTask().getEntity().getFarbe());
        component.setMinimumSize(new Dimension(10, 20));
    }
}
