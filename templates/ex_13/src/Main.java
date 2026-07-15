import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static void ex_1(){
        List<Human> people = new LinkedList<>();
        people.add(new Human("Иванов", "Иван", "Иванович", 25));
        people.add(new Human("Петров", "Пётр", "Петрович", 30));
        people.add(new Human("Иванов", "Алексей", "Иванович", 20));
        people.add(new Human("Иванов", "Иван", "Иванович", 22));
        Collections.sort(people);
        System.out.println(people);
    }

    private static void ex_2(){
        HumanTest.start();
    }

    public static void main(String[] args) {
        System.out.println("=== Задание 1 (сортировка) ===");
        ex_1();
        System.out.println("=== Задание 2 (тесты работы класса Human) ===");
        ex_2();
    }
}
