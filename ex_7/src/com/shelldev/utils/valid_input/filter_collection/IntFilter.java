package com.shelldev.utils.valid_input.filter_collection;

import com.shelldev.utils.valid_input.Filter;

public class IntFilter extends Filter {

    @Override
    public boolean validate(String line) {
        if (line == null) {
            return false;
        }
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}