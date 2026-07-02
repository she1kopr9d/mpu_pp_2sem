package shelldev.utils.valid_input;

import java.util.Scanner;

public class Input {
    public static String getLine(Filter[] filters, String error_message, String input_message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(input_message);
            String line = scanner.nextLine();
            boolean isValid = true;
            if (filters != null) {
                for (Filter filter : filters) {
                    if (!filter.validate(line)) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) {
                return line;
            } else {
                System.out.println(error_message);
            }
        }
    }
}