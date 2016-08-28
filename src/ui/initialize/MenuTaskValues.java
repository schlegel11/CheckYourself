package ui.initialize;

import core.entities.Task;
import ui.EntityWrapper;

import java.util.Collection;

/**
 * Class which holds initialize values for the UI.
 */
public class MenuTaskValues {

    private final Collection<EntityWrapper<Task>> tasks;

    public MenuTaskValues(Collection<EntityWrapper<Task>> tasks) {
        this.tasks = tasks;
    }

    public Collection<EntityWrapper<Task>> getTasks() {
        return tasks;
    }
}
