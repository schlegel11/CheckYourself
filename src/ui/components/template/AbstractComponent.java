package ui.components.template;

import java.awt.*;

/**
 * The {@link AbstractComponent} contains methods for creating a class that
 * contains a {@link Component} for composition.
 *
 * @param <TComponent> The {@link Component} for composition.
 */
public abstract class AbstractComponent<TComponent extends Component> {

    protected final TComponent component;

    protected AbstractComponent(TComponent component) {
        this.component = component;
    }

    protected abstract void initialize();

    public TComponent getComponent() {
        return component;
    }

}
