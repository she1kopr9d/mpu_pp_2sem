
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тестирование дека Deq ===\n");
        try {
            Deq d = new Deq(5);
            System.out.println("Тест 1: new Deq().empty() = " + d.empty() + " (ожидается true)");
        } catch (Exception e) {
            System.out.println("Тест 1 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.pushBack(10);
            System.out.println("Тест 2: front() = " + d.front() + ", back() = " + d.back() + " (ожидается 10, 10)");
        } catch (Exception e) {
            System.out.println("Тест 2 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.pushBack(10);
            d.pushFront(5);
            System.out.println("Тест 3: front() = " + d.front() + ", back() = " + d.back() + " (ожидается 5, 10)");
        } catch (Exception e) {
            System.out.println("Тест 3 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.pushBack(10);
            d.pushFront(5);
            int popped = d.popFront();
            System.out.println("Тест 4: popFront() = " + popped + ", новый front = " + d.front() + " (ожидается 5, 10)");
        } catch (Exception e) {
            System.out.println("Тест 4 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.pushBack(10);
            d.pushFront(5);
            int popped = d.popBack();
            System.out.println("Тест 5: popBack() = " + popped + ", новый back = " + d.back() + " (ожидается 10, 5)");
        } catch (Exception e) {
            System.out.println("Тест 5 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.pushBack(1);
            d.pushBack(2);
            d.clear();
            System.out.println("Тест 6: после clear, empty() = " + d.empty() + " (ожидается true)");
        } catch (Exception e) {
            System.out.println("Тест 6 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.popFront();
            System.out.println("Тест 7 провален: исключение не было выброшено");
        } catch (Exception e) {
            System.out.println("Тест 7: успешно перехвачено исключение: " + e.getMessage());
        }
        try {
            Deq d = new Deq(3);
            d.pushBack(1);
            d.pushBack(2);
            d.pushBack(3);
            d.pushFront(0);
            System.out.println("Тест 8 провален: исключение не было выброшено");
        } catch (Exception e) {
            System.out.println("Тест 8: успешно перехвачено исключение: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            d.pushFront(10);
            d.pushBack(20);
            d.pushFront(30);
            d.pushBack(40);
            int f = d.popFront();
            int b = d.popBack();
            int f2 = d.popFront();
            int b2 = d.popBack();
            System.out.println("Тест 9: результаты popFront, popBack, popFront, popBack: "
                    + f + ", " + b + ", " + f2 + ", " + b2 + " (ожидается 30, 40, 10, 20)");
            System.out.println("   Дек пуст? " + d.empty() + " (ожидается true)");
        } catch (Exception e) {
            System.out.println("Тест 9 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(4);
            d.pushBack(1);
            d.pushBack(2);
            d.pushBack(3);
            d.pushBack(4);
            d.popFront();
            d.popFront();
            d.pushBack(5);
            d.pushBack(6);
            System.out.println("Тест 10: front = " + d.front() + ", back = " + d.back()
                    + " (ожидается 3, 6), empty = " + d.empty());
        } catch (Exception e) {
            System.out.println("Тест 10 провален: " + e.getMessage());
        }
        try {
            Deq d = new Deq(5);
            for (int i = 1; i <= 5; i++) d.pushBack(i);
            int[] temp = new int[5];
            for (int i = 0; i < 5; i++) {
                temp[i] = d.popFront();
                d.pushBack(temp[i]);
            }
            System.out.print("\nТест 11 (извлечение в прямом порядке): ");
            Deq d2 = new Deq(d);
            for (int i = 0; i < 5; i++) System.out.print(d2.popFront() + " ");
            System.out.print("\nТест 12 (извлечение в обратном порядке): ");
            Deq d3 = new Deq(d);
            for (int i = 0; i < 5; i++) System.out.print(d3.popBack() + " ");
        } catch (Exception e) {
            System.out.println("Тест 11 провален: " + e.getMessage());
        }
        System.out.println("\n=== Тестирование завершено ===");
    }
}