import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    private static void moveToFront(LinkedList<String> list, String name) {
        if (list.isEmpty()) return;
        if (list.getFirst().equals(name)) return;
        int idx = list.indexOf(name);
        if (idx != -1) {
            list.remove(idx);
            list.add(0, name);
        }
    }

    private static void runCommand(LinkedList<String> list, String command) {
        String name = command.substring(4).trim();
        if (list.indexOf(name) != -1) {
            moveToFront(list, name);
        } else {
            list.add(0, name);
        }
        System.out.println(name);
    }

    private static void deleteCommand(LinkedList<String> list) {
        if (!list.isEmpty()) list.remove(0);
    }

    private static int countTabs(String command) {
        String[] parts = command.split(" ");
        int count = 0;
        for (String p : parts) if (p.equals("Tab")) count++;
        return count;
    }

    private static void reChangePosition(LinkedList<String> list, String command) {
        int size = list.size();
        if (size == 0) return;
        int tabs = countTabs(command);
        int position = (tabs % size) + 1;
        String name = list.get(position - 1);
        moveToFront(list, name);
        System.out.println(name);
    }

    private static void execCommand(LinkedList<String> list, String command) {
        if (command == null) return;
        command = command.trim();
        if (command.isEmpty()) return;
        if (command.startsWith("Run ")) {
            runCommand(list, command);
        } else if (command.startsWith("Alt Delete")) {
            deleteCommand(list);
        } else if (command.startsWith("Alt Tab")) {
            reChangePosition(list, command);
        }
    }

    private static String[] splitCommands(String program) {
        if (program == null || program.isEmpty()) return new String[0];
        return program.split("\n", -1);
    }

    private static String loadProgramFromFile(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private static void runProgram(String program) {
        LinkedList<String> list = new LinkedList<>();
        for (String cmd : splitCommands(program)) {
            if (!cmd.trim().isEmpty()) execCommand(list, cmd);
        }
    }

    private static void runTest(String[] filenames) {
        int testNumber = 1;
        for (String filename : filenames) {
            try {
                String program = loadProgramFromFile(filename);
                System.out.println("--- Тест " + testNumber + " ---");
                System.out.println("Пример ввода:");
                System.out.println(program);
                System.out.println("Вывод:");
                runProgram(program);
                System.out.println("--------------" + (testNumber >= 10 ? "-" : ""));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            testNumber++;
        }
    }

    public static void main(String[] args) {
        runTest(new String[]{
            "tests/test0.txt",
            "tests/test1.txt",
            "tests/test2.txt",
            "tests/test3.txt",
            "tests/test4.txt",
            "tests/test5.txt",
            "tests/test6.txt",
            "tests/test7.txt",
            "tests/test8.txt",
            "tests/test9.txt",
            "tests/test10.txt"
        });
    }
}