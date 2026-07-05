import java.util.Random;

public class Main {
    private static final Random RAND = new Random();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String genTruePalindrome() {
        int len = 3 + RAND.nextInt(8);
        StringBuilder sb = new StringBuilder(len);
        int half = (len + 1) / 2;
        for (int i = 0; i < half; i++) {
            char c = ALPHABET.charAt(RAND.nextInt(ALPHABET.length()));
            sb.append(c);
        }
        String firstHalf = sb.toString();
        StringBuilder secondHalf = new StringBuilder(firstHalf);
        if (len % 2 == 1)
            secondHalf.deleteCharAt(secondHalf.length() - 1);
        sb.append(secondHalf.reverse().toString());
        return sb.toString();
    }

    public static String genFalsePalindrome() {
        String candidate;
        do {
            int len = 3 + RAND.nextInt(8);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++)
                sb.append(ALPHABET.charAt(RAND.nextInt(ALPHABET.length())));
            candidate = sb.toString();
        } while (isPalindromeSafe(candidate));
        return candidate;
    }

    private static boolean isPalindromeSafe(String s) {
        try {
            return isPalindrome(s);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPalindrome(String s) throws Exception {
        Deq<Character> deq = new Deq<>(s.length());
        for (char c : s.toCharArray()) {
            deq.pushBack(c);
        }
        while (deq.size > 1) {
            char first = deq.popFront();
            char last = deq.popBack();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n=== Сгенерированные палиндромы (5 штук) ===");
        for (int i = 0; i < 5; i++) {
            String p = genTruePalindrome();
            boolean result = isPalindrome(p);
            System.out.printf("Палиндром: \"%s\" -> %b (должен быть true)%n", p, result);
        }
        System.out.println("\n=== Сгенерированные не палиндромы (5 штук) ===");
        for (int i = 0; i < 5; i++) {
            String np = genFalsePalindrome();
            boolean result = isPalindrome(np);
            System.out.printf("Не палиндром: \"%s\" -> %b (должен быть false)%n", np, result);
        }
    }
}