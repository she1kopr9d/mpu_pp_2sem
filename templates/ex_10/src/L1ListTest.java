import java.util.Objects;

public class L1ListTest {
    public void runAllTests() {
        System.out.println("=== Запуск тестов L1List ===\n");
        testAppend();
        testFind();
        testGet();
        testInsert();
        testRemove();
        testCombined();
        System.out.println("\n=== Все тесты завершены ===");
    }

    private <T> Node<T> node(T value) {
        return new Node<>(value);
    }

    private <T> void assertListEquals(L1List<T> list, T... expected) {
        int actualSize = list.size();
        if (actualSize != expected.length) {
            throw new AssertionError("Ожидаемый размер " + expected.length + ", получен " + actualSize);
        }
        for (int i = 0; i < expected.length; i++) {
            Node<T> node = list.get(i);
            if (node == null) {
                throw new AssertionError("Узел по индексу " + i + " равен null");
            }
            if (!Objects.equals(expected[i], node.value)) {
                throw new AssertionError("По индексу " + i + " ожидалось " + expected[i] + ", получено " + node.value);
            }
        }
        if (list.get(expected.length) != null) {
            throw new AssertionError("get(size()) должен возвращать null, а вернул не null");
        }
        if (expected.length == 0) {
            if (list.get(0) != null) {
                throw new AssertionError("Пустой список: get(0) должен быть null");
            }
        } else {
            Node<T> first = list.get(0);
            if (first == null || !Objects.equals(expected[0], first.value)) {
                throw new AssertionError("Первый элемент не совпадает: ожидалось " + expected[0] + ", получено " + (first == null ? "null" : first.value));
            }
            Node<T> last = list.get(expected.length - 1);
            if (last == null || !Objects.equals(expected[expected.length - 1], last.value)) {
                throw new AssertionError("Последний элемент не совпадает: ожидалось " + expected[expected.length - 1] + ", получено " + (last == null ? "null" : last.value));
            }
        }
    }

    private void assertThrows(Runnable runnable, Class<? extends Exception> expectedException) {
        try {
            runnable.run();
            throw new AssertionError("Ожидалось исключение " + expectedException.getSimpleName() + ", но ничего не выброшено");
        } catch (Exception e) {
            if (!expectedException.isInstance(e)) {
                throw new AssertionError("Ожидалось " + expectedException.getSimpleName() + ", получено " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    private void testAppend() {
        System.out.println("--- testAppend ---");
        L1List<Integer> list = new L1List<>();
        list.append(node(10));
        assertListEquals(list, 10);
        System.out.println("  Добавление в пустой: OK");
        list.append(node(20));
        list.append(node(30));
        assertListEquals(list, 10, 20, 30);
        System.out.println("  Добавление нескольких: OK");
        System.out.println("  Целостность ссылок: OK (проверено косвенно)");
        System.out.println();
    }

    private void testFind() {
        System.out.println("--- testFind ---");
        L1List<Integer> list = new L1List<>();
        if (list.find(node(5)) != -1) throw new AssertionError("Поиск в пустом списке должен возвращать -1");
        System.out.println("  Поиск в пустом: OK");
        list.append(node(10));
        list.append(node(20));
        list.append(node(30));
        if (list.find(node(10)) != 0) throw new AssertionError("Ожидался индекс 0 для значения 10");
        if (list.find(node(20)) != 1) throw new AssertionError("Ожидался индекс 1 для значения 20");
        if (list.find(node(30)) != 2) throw new AssertionError("Ожидался индекс 2 для значения 30");
        if (list.find(node(99)) != -1) throw new AssertionError("Ожидался -1 для отсутствующего значения");
        list.append(node(20));
        if (list.find(node(20)) != 1) throw new AssertionError("Ожидалось первое вхождение по индексу 1");
        if (list.find(null) != -1) throw new AssertionError("find(null) должен возвращать -1");
        System.out.println("  Все случаи поиска: OK");
        System.out.println();
    }

    private void testGet() {
        System.out.println("--- testGet ---");
        L1List<Integer> list = new L1List<>();
        if (list.get(0) != null) throw new AssertionError("get(0) для пустого должен быть null");
        if (list.get(-1) != null) throw new AssertionError("get(-1) для пустого должен быть null");
        list.append(node(5));
        list.append(node(10));
        Node<Integer> n0 = list.get(0);
        if (n0 == null || n0.value != 5) throw new AssertionError("get(0) должен вернуть 5");
        Node<Integer> n1 = list.get(1);
        if (n1 == null || n1.value != 10) throw new AssertionError("get(1) должен вернуть 10");
        if (list.get(2) != null) throw new AssertionError("get(2) вне границ должен вернуть null");
        if (list.get(-1) != null) throw new AssertionError("get(-1) вне границ должен вернуть null");
        System.out.println("  Все случаи get: OK");
        System.out.println();
    }

    private void testInsert() {
        System.out.println("--- testInsert ---");
        L1List<Integer> list = new L1List<>();
        assertThrows(() -> list.insert(null, 0), IllegalArgumentException.class);
        System.out.println("  Вставка null выбрасывает: OK");
        Node<Integer> n1 = node(10);
        list.insert(n1, 0);
        assertListEquals(list, 10);
        System.out.println("  Вставка в пустой: OK");
        Node<Integer> n0 = node(0);
        list.insert(n0, 0);
        assertListEquals(list, 0, 10);
        System.out.println("  Вставка в начало: OK");
        Node<Integer> nEnd = node(20);
        list.insert(nEnd, list.size());
        assertListEquals(list, 0, 10, 20);
        System.out.println("  Вставка в конец: OK");
        Node<Integer> nMid = node(5);
        list.insert(nMid, 1);
        assertListEquals(list, 0, 5, 10, 20);
        System.out.println("  Вставка в середину: OK");
        assertThrows(() -> list.insert(node(30), -1), IndexOutOfBoundsException.class);
        assertThrows(() -> list.insert(node(30), list.size() + 1), IndexOutOfBoundsException.class);
        System.out.println("  Проверка границ: OK");
        System.out.println();
    }

    private void testRemove() {
        System.out.println("--- testRemove ---");
        L1List<Integer> list = new L1List<>();
        assertThrows(() -> list.remove(0), IllegalStateException.class);
        System.out.println("  Удаление из пустого выбрасывает: OK");
        list.append(node(10));
        list.append(node(20));
        list.append(node(30));
        list.append(node(40));
        list.remove(0);
        assertListEquals(list, 20, 30, 40);
        System.out.println("  Удаление первого: OK");
        list.remove(list.size() - 1);
        assertListEquals(list, 20, 30);
        System.out.println("  Удаление последнего: OK");
        list.remove(1);
        assertListEquals(list, 20);
        System.out.println("  Удаление среднего: OK");
        list.remove(0);
        assertListEquals(list);
        System.out.println("  Удаление единственного: OK");
        list.append(node(1));
        assertThrows(() -> list.remove(-1), IndexOutOfBoundsException.class);
        assertThrows(() -> list.remove(list.size()), IndexOutOfBoundsException.class);
        System.out.println("  Проверка границ: OK");
        System.out.println();
    }

    private void testCombined() {
        System.out.println("--- testCombined ---");
        L1List<Integer> list = new L1List<>();
        list.insert(node(1), 0);
        list.insert(node(3), 1);
        list.insert(node(2), 1);
        list.insert(node(0), 0);
        list.insert(node(4), 4);
        assertListEquals(list, 0, 1, 2, 3, 4);
        System.out.println("  Сложная последовательность вставок: OK");
        list.remove(2);
        assertListEquals(list, 0, 1, 3, 4);
        list.remove(0);
        assertListEquals(list, 1, 3, 4);
        list.remove(2);
        assertListEquals(list, 1, 3);
        list.remove(1);
        assertListEquals(list, 1);
        list.remove(0);
        assertListEquals(list);
        System.out.println("  Сложная последовательность удалений: OK");
        System.out.println("  Комбинированные операции: все пройдены");
        System.out.println();
    }
}