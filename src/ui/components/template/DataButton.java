package ui.components.template;

import javax.swing.*;

/**
 * {@link DataButton} that represents a base class for build a {@link JButton}
 * with a generic setter and getter.
 *
 * @param <TData> The data type for setter and getter.
 */
public class DataButton<TData> extends JButton {
    private TData data;

    public DataButton(TData data) {
        this.data = data;
    }

    public DataButton(TData data, String text) {
        this(data);
        setText(text);
    }

    public TData getData() {
        return data;
    }

    public void setData(TData data) {
        this.data = data;
    }
}
