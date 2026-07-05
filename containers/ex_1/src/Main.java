import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MIN_SIZE_TEST = 8;
    private static final int MAX_SIZE_TEST = 32;
    private static final Random random = new Random();

    public static boolean checkBrackets(String s) {
        Stack<Character> stack = new Stack<>(s.length());
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                try {
                    stack.push(ch);
                } catch (Exception e) {
                    return false;
                }
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.empty()) {
                    return false;
                }
                char open;
                try{
                    open = stack.pop();
                } catch (Exception e) {
                    return false;
                }
                if (!isMatchingPair(open, ch))
                    return false;
            }
        }
        return stack.empty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
    private static char randomNonBracketChar() {
        if (random.nextBoolean()) {
            return (char) ('a' + random.nextInt(26));
        } else {
            return (char) ('0' + random.nextInt(10));
        }
    }

    private static void appendRandomNonBrackets(StringBuilder sb, int maxCount) {
        int count = random.nextInt(maxCount + 1);
        for (int i = 0; i < count; i++) {
            sb.append(randomNonBracketChar());
        }
    }

    public static String generateBracketTrueTest() {
        int size = random.nextInt(MAX_SIZE_TEST - MIN_SIZE_TEST + 1) + MIN_SIZE_TEST;
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>(size);
        try {
            appendRandomNonBrackets(sb, 3);
            for (int i = 0; i < size; i++) {
                if (stack.empty() || random.nextBoolean()) {
                    char open = randomOpenBracket();
                    if (random.nextDouble() < 0.3) {
                        appendRandomNonBrackets(sb, 2);
                    }
                    sb.append(open);
                    stack.push(open);
                    if (random.nextDouble() < 0.2) {
                        appendRandomNonBrackets(sb, 2);
                    }
                } else {
                    char open = stack.pop();
                    if (random.nextDouble() < 0.3) {
                        appendRandomNonBrackets(sb, 2);
                    }
                    sb.append(closeForOpen(open));
                    if (random.nextDouble() < 0.2) {
                        appendRandomNonBrackets(sb, 2);
                    }
                }
            }
            while (!stack.empty()) {
                char open = stack.pop();
                sb.append(closeForOpen(open));
                if (random.nextDouble() < 0.2) {
                    appendRandomNonBrackets(sb, 2);
                }
            }
            appendRandomNonBrackets(sb, 3);
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public static String generateBracketFalseTest() {
        int size = random.nextInt(MAX_SIZE_TEST - MIN_SIZE_TEST + 1) + MIN_SIZE_TEST;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (random.nextDouble() < 0.4) {
                char[] brackets = {'(', ')', '[', ']', '{', '}'};
                sb.append(brackets[random.nextInt(brackets.length)]);
            } else {
                sb.append(randomNonBracketChar());
            }
        }
        sb.append(')');
        return sb.toString();
    }

    private static char randomOpenBracket() {
        char[] opens = {'(', '[', '{'};
        return opens[random.nextInt(opens.length)];
    }

    private static char closeForOpen(char open) {
        switch (open) {
            case '(': return ')';
            case '[': return ']';
            case '{': return '}';
            default: throw new IllegalArgumentException("Неизвестная открывающая скобка");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строки для проверки (пустая строка завершает ввод):");
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            boolean result = checkBrackets(line);
            System.out.println("Строка: \"" + line + "\" -> " + (result ? "Правильно" : "Неправильно"));
        }
        scanner.close();

        System.out.println("\nСгенерированные тестовые примеры (с символами):");
        for (int i = 0; i < 5; i++) {
            String trueTest = generateBracketTrueTest();
            System.out.println(trueTest + " -> " + checkBrackets(trueTest));
        }
        for (int i = 0; i < 5; i++) {
            String falseTest = generateBracketFalseTest();
            System.out.println(falseTest + " -> " + checkBrackets(falseTest));
        }
    }
}