package com.shelldev.utils.valid_input.filter_collection;

public class InSectionFilter extends DoubleFilter {
    private final double min;
    private final double max;

    public InSectionFilter(double min, double max) {
        if (min > max) {
            double temp = min;
            min = max;
            max = temp;
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate(String line) {
        if (!super.validate(line)) {
            return false;
        }
        double value = Double.parseDouble(line);
        return value >= min && value <= max;
    }
}