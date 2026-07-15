import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int findIndexByName(L1List<String> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).value.equals(name)) return i;
        }
        return -1;
    }

    private static void moveToFrontByName(L1List<String> list, String name) {
        if (list.size() == 0) return;
        if (list.get(0).value.equals(name)) return;
        int idx = findIndexByName(list, name);
        if (idx != -1) {
            list.remove(idx);
            list.insert(new Node<>(name), 0);
        }
    }

    private static void pushFront(L1List<String> list, String name) {
        list.insert(new Node<>(name), 0);
    }

    private static String popFront(L1List<String> list) {
        if (list.size() == 0) return null;
        String name = list.get(0).value;
        list.remove(0);
        return name;
    }

    private static void runCommand(L1List<String> list, String command) {
        String name = command.substring(4).trim();
        if (findIndexByName(list, name) != -1) {
            moveToFrontByName(list, name);
        } else {
            pushFront(list, name);
        }
        System.out.println(name);
    }

    private static void deleteCommand(L1List<String> list) {
        if (list.size() > 0) popFront(list);
    }

    private static int countTabs(String command) {
        String[] parts = command.split(" ");
        int count = 0;
        for (String p : parts) {
            if (p.equals("Tab")) count++;
        }
        return count;
    }

    private static void reChangePosition(L1List<String> list, String command) {
        int size = list.size();
        if (size == 0) return;
        int tabs = countTabs(command);
        int position = (tabs % size) + 1;
        Node<String> node = list.get(position - 1);
        if (node == null) return;
        String name = node.value;
        moveToFrontByName(list, name);
        System.out.println(name);
    }

    private static void execCommand(L1List<String> list, String command) {
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
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static void runProgram(String program) {
        L1List<String> list = new L1List<>();
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