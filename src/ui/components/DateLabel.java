package ui.components;

import core.entities.Task;
import ui.EntityWrapper;
import ui.components.template.AbstractComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Specific {@link DateLabel} that can contain {@link Task} objects.
 */
public final class DateLabel extends AbstractComponent<JLabel> {
    private final JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    DateLabel(JLabel dataCollectionLabel) {
        super(dataCollectionLabel);
        initialize();
    }

    public void addTaskValues(EntityWrapper<Task> values) {
        TaskButton labelActionButton = UIComponentFactory.createTaskButton(values);
        labelActionButton.setMaximumSize(new Dimension(10, 10));
        labelActionButton.setToolTipText(values.getEntity().getName());
        taskPanel.add(labelActionButton.getComponent());
    }

    @Override
    protected void initialize() {
        component.setLayout(new BorderLayout());
        component.add(BorderLayout.SOUTH, taskPanel);
        component.setHorizontalAlignment(JLabel.CENTER);
        taskPanel.setOpaque(false);
    }

}
