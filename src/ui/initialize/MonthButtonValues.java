package ui.initialize;

import java.time.Month;
import java.util.Collection;

/**
 * Class which holds initialize values for the UI.
 */
public class MonthButtonValues {
    private final Collection<Month> months;

    public MonthButtonValues(Collection<Month> months) {
        this.months = months;
    }

    public Collection<Month> getMonths() {
        return months;
    }

    public Month getFirstMonth() {
        return getMonths().stream().findFirst().get();
    }
}
