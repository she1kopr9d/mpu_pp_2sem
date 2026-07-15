import java.util.regex.Pattern;

public class Main {
    private static final String NATURAL_PATTERN = "^(0|[1-9][0-9]*)$";
    private static final Pattern NATURAL = Pattern.compile(NATURAL_PATTERN);

    private static final String REAL_PATTERN = "^(-?[1-9][0-9]*|0)(\\.[0-9]*[1-9])?$";
    private static final Pattern REAL = Pattern.compile(REAL_PATTERN);

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidNaturalNumber(String input) {
        if (input == null) return false;
        return NATURAL.matcher(input).matches();
    }

    public static boolean isValidRealNumber(String input) {
        if (input == null) return false;
        return REAL.matcher(input).matches();
    }

    public static boolean isValidEmail(String input) {
        if (input == null) return false;
        return EMAIL.matcher(input).matches();
    }

    public static void main(String[] args) {
        System.out.println("=== Задание 1 (Множество натуральных чисел) ===");
        String[] naturalTests = {"0", "5", "10", "007", "-5", "01"};
        for (String s : naturalTests) {
            System.out.println(s + " => натуральное? " + isValidNaturalNumber(s));
        }
        System.out.println("=== Задание 2 (Множество действительных чисел) ===");
        String[] realTests = {"0", "0.5", "-3.14", "2.0", "-0", "1.", ".5", "0.10"};
        for (String s : realTests) {
            System.out.println(s + " => действительное? " + isValidRealNumber(s));
        }
        System.out.println("=== Задание 3 (Корректные адреса электронной почты) ===");
        String[] emailTests = {"user@example.com", "name.surname@domain.co.uk", "invalid@domain", "missing@.com", "user@domain.c"};
        for (String s : emailTests) {
            System.out.println(s + " => email? " + isValidEmail(s));
        }
    }
}