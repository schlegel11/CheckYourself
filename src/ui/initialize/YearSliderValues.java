package ui.initialize;

import java.time.Year;

/**
 * Class which holds initialize values for the UI.
 */
public class YearSliderValues {

    private final Year minYear;
    private final Year maxYear;
    private final Year initYear;

    public YearSliderValues(Year minYear, Year maxYear, Year initYear) {
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.initYear = initYear;
    }

    public YearSliderValues(int minYear, int maxYear, int initYear) {
        this(Year.of(minYear), Year.of(maxYear), Year.of(initYear));
    }

    public Year getMinYear() {
        return minYear;
    }

    public Year getMaxYear() {
        return maxYear;
    }

    public Year getInitYear() {
        return initYear;
    }

    public int getMinYearValue() {
        return getMinYear().getValue();
    }

    public int getMaxYearValue() {
        return getMaxYear().getValue();
    }

    public int getInitYearValue() {
        return getInitYear().getValue();
    }
}
