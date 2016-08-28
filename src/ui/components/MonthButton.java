package ui.components;

import ui.components.template.AbstractComponent;
import ui.components.template.DataButton;

import java.awt.*;
import java.time.Month;

/**
 * Specific {@link MonthButton} that can contain {@link Month} object.
 */
public class MonthButton extends AbstractComponent<DataButton<Month>> {

    MonthButton(Month month, String text) {
        super(new DataButton<>(month, text));
        initialize();
    }

    public Month getMonth() {
        return component.getData();
    }

    public void setMonth(Month month) {
        component.setData(month);
    }

    @Override
    protected void initialize() {
        component.setMaximumSize(new Dimension(100, 200));
    }

}
