import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    private static int size(L1List list) {
        int count = 0;
        list.toFront();
        while (!list.end()) {
            count++;
            try { list.forward(); } catch (Exception e) { break; }
        }
        return count;
    }

    private static int findIdByName(L1List list, HashMap<Integer, String> idToName, String name) {
        list.toFront();
        while (!list.end()) {
            try {
                int id = list.after();
                if (idToName.get(id).equals(name)) {
                    return id;
                }
                list.forward();
            } catch (Exception e) { break; }
        }
        return -1;
    }

    private static void moveToFrontById(L1List list, int id) {
        if (list.empty()) return;
        list.toFront();
        try {
            int headId = list.after();
            if (headId == id) return;
        } catch (Exception e) { return; }
        list.toFront();
        int prevId = -1;
        int curId = -1;
        boolean found = false;
        while (!list.end()) {
            try {
                curId = list.after();
                if (curId == id) {
                    found = true;
                    break;
                }
                prevId = curId;
                list.forward();
            } catch (Exception e) { break; }
        }
        if (!found) return;
        try {
            list.erase();
        } catch (Exception e) { return; }
        try {
            list.toFront();
            list.insert(id);
        } catch (Exception e) {}
    }

    private static void pushFront(L1List list, int id) {
        try {
            list.toFront();
            list.insert(id);
        } catch (Exception e) {}
    }

    private static int popFront(L1List list) {
        try {
            list.toFront();
            return list.erase();
        } catch (Exception e) {
            return -1;
        }
    }

    private static void runCommand(L1List apps, HashMap<Integer, String> idToName, String command, int[] nextIdRef) {
        String name = command.substring(4).trim();
        int id = findIdByName(apps, idToName, name);
        if (id != -1) {
            moveToFrontById(apps, id);
        } else {
            id = nextIdRef[0]++;
            idToName.put(id, name);
            pushFront(apps, id);
        }
        System.out.println(name);
    }

    private static void deleteCommand(L1List apps, HashMap<Integer, String> idToName, String command, int[] nextIdRef) {
        if (!apps.empty()) {
            int id = popFront(apps);
            idToName.remove(id);
        }
    }

    private static void reChangePosition(L1List apps, HashMap<Integer, String> idToName, String command, int[] nextIdRef) {
        int n = size(apps);
        if (n == 0) return;
        int k = 0;
        String[] parts = command.split(" ");
        for (String part : parts) {
            if (part.equals("Tab")) k++;
        }
        int pos = (k % n) + 1;
        int selectedId = -1;
        apps.toFront();
        int curPos = 1;
        while (!apps.end()) {
            try {
                if (curPos == pos) {
                    selectedId = apps.after();
                    break;
                }
                apps.forward();
                curPos++;
            } catch (Exception e) { break; }
        }
        if (selectedId == -1) return;
        moveToFrontById(apps, selectedId);
        System.out.println(idToName.get(selectedId));
    }

    private static void execCommand(L1List apps, HashMap<Integer, String> idToName, String command, int[] nextIdRef) {
        if (command == null || command.trim().isEmpty()) return;
        command = command.trim();
        if (command.startsWith("Run ")) {
            runCommand(apps, idToName, command, nextIdRef);
        } else if (command.startsWith("Alt Delete")) {
            deleteCommand(apps, idToName, command, nextIdRef);
        } else if (command.startsWith("Alt Tab")) {
            reChangePosition(apps, idToName, command, nextIdRef);
        }
    }

    private static String[] splitCommands(String program) {
        if (program == null || program.isEmpty()) {
            return new String[0];
        }
        return program.split("\n", -1);
    }

    private static String loadProgramFromFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static void runProgram(String program) {
        L1List apps = new L1List();
        HashMap<Integer, String> idToName = new HashMap<>();
        int[] nextId = {0};
        String[] commands = splitCommands(program);
        for (String command : commands) {
            if (!command.trim().isEmpty()) {
                execCommand(apps, idToName, command, nextId);
            }
        }
    }

    private static void runTest(String[] testsPath){
        int index = 1;
        for (String testPath : testsPath){
            String program;
            try {
                program = loadProgramFromFile(testPath);
                System.out.println("--- Тест " + index + " ---");
                System.out.println("Пример ввода:");
                System.out.println(program);
                System.out.println("Вывод:");
                runProgram(program);
                String addLine = "";
                if (index >= 10){
                    addLine = "-";
                }
                System.out.println("--------------" + addLine);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            index += 1;
        }
    }

    public static void main(String[] args) {
        runTest(new String[] {
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
            "tests/test10.txt",
        });
    }
}