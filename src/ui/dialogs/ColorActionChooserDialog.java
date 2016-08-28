package ui.dialogs;

import core.entities.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Consumer;

/**
 * {@link ColorActionChooserDialog} that represents a dialog for creating the
 * {@link Task}s and define a name and color for it.
 */
public final class ColorActionChooserDialog extends JDialog {

    private final JColorChooser colorChooser = new JColorChooser();
    private final JTextField actionNameTextField = new JTextField();
    private final JButton buttonOk = new JButton("OK");
    private final JButton buttonCancel = new JButton("Abbrechen");
    public ColorActionChooserDialog(String title, int width, int height, int posX, int posY) {
        initialize(title, width, height, posX, posY);
    }

    public ColorActionChooserDialog(String title, int width, int height, JFrame positionOrientationFrame) {
        int posX = ((positionOrientationFrame.getWidth() - width) / 2) + positionOrientationFrame.getX();
        int posY = ((positionOrientationFrame.getHeight() - height) / 2) + positionOrientationFrame.getY();
        initialize(title, width, height, posX, posY);
    }

    private void initialize(String title, int width, int height, int posX, int posY) {
        setProperties(title, width, height, posX, posY);
        addComponents();
    }

    private void setProperties(String title, int width, int height, int posX, int posY) {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle(title);
        setBounds(posX, posY, width, height);
        setResizable(false);
        setModal(true);
    }

    private void addComponents() {
        add(colorChooser);
        add(actionNameTextField);
        Panel buttonPanel = new Panel();
        buttonPanel.add(buttonOk);
        buttonPanel.add(buttonCancel);
        add(buttonPanel);
    }

    private void eraseTextFieldContentOnInitFocus() {
        actionNameTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void focusGained(FocusEvent e) {
                actionNameTextField.setText("");
                actionNameTextField.removeFocusListener(this);
            }
        });
    }

    public void buttonAction(ButtonAction buttonActionCondition, Consumer<ColorActionChooserDialog> consumer) {
        buttonOk.addActionListener(l -> {
            if (buttonActionCondition == ButtonAction.OK || buttonActionCondition == ButtonAction.ANY) {
                consumer.accept(this);
            }
            dispose();
        });
        buttonCancel.addActionListener(l -> {
            if (buttonActionCondition == ButtonAction.CANCEL || buttonActionCondition == ButtonAction.ANY) {
                consumer.accept(this);
            }
            dispose();
        });
    }

    public void buttonAction(Consumer<ColorActionChooserDialog> consumer) {
        buttonAction(ButtonAction.ANY, consumer);
    }

    public void open() {
        if (!isVisible()) {
            actionNameTextField.setText("Aktion eingeben");
            eraseTextFieldContentOnInitFocus();
            setVisible(true);
        }
    }

    public DialogValues getValues() {
        return new DialogValues(colorChooser.getColor(), actionNameTextField.getText());
    }

    public enum ButtonAction {
        OK, CANCEL, ANY
    }
}
