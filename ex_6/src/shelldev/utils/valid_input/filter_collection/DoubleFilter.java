package shelldev.utils.valid_input.filter_collection;

import shelldev.utils.valid_input.Filter;

public class DoubleFilter extends Filter {

    @Override
    public boolean validate(String line) {
        if (line == null) {
            return false;
        }
        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}