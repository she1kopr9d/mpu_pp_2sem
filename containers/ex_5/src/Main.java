public class Main {
    public static void main(String[] args) throws Exception {
        testEmpty();
        testInsertBackAndForward();
        testInsertFrontAndBackward();
        testEraseBack();
        testEraseFront();
        testAfterBefore();
        testExceptions();
        testOverflow();
        testClear();
        testMixedOperations();
        testPrintOrder();
        System.out.println("\nВсе тесты пройдены успешно!");
    }

    private static void testEmpty() throws Exception {
        L2List list = new L2List();
        assertTrue(list.empty(), "Список должен быть пустым");
        list.toFront();
        assertTrue(list.begin(), "Курсор должен быть в начале");
        assertTrue(list.end(), "Курсор также в конце, т.к. список пуст");
        list.toBack();
        assertTrue(list.begin(), "toBack на пустом списке оставляет курсор на голове");
        assertTrue(list.end(), "Курсор в конце");
        System.out.println("testEmpty: OK");
    }

    private static void testInsertBackAndForward() throws Exception {
        L2List list = new L2List();
        list.insertBack(10);
        list.toBack();
        list.insertBack(20);
        list.toBack();
        list.insertBack(30);
        list.toFront();
        assertFalse(list.empty(), "Список не должен быть пустым");
        assertEquals(list.after(), 10, "Первый элемент должен быть 10");
        list.forward();
        assertEquals(list.after(), 20, "Второй элемент должен быть 20");
        list.forward();
        assertEquals(list.after(), 30, "Третий элемент должен быть 30");
        list.forward();
        assertTrue(list.end(), "После третьего элемента курсор должен быть в конце");
        System.out.println("testInsertBackAndForward: OK");
    }

    private static void testInsertFrontAndBackward() throws Exception {
        L2List list = new L2List();
        list.insertBack(100);
        list.insertFront(200);
        list.insertFront(300);
        list.toFront();
        assertEquals(list.after(), 300, "Первый элемент должен быть 300");
        list.forward();
        assertEquals(list.after(), 200, "Второй элемент должен быть 200");
        list.forward();
        assertEquals(list.after(), 100, "Третий элемент должен быть 100");
        list.toBack();
        assertEquals(list.before(), 100, "Перед курсором должен быть 100");
        list.backward();
        assertEquals(list.before(), 200, "После backward перед курсором 200");
        list.backward();
        assertEquals(list.before(), 300, "После backward перед курсором 300");
        list.backward();
        assertTrue(list.begin(), "После трёх шагов назад курсор в начале");
        System.out.println("testInsertFrontAndBackward: OK");
    }

    private static void testEraseBack() throws Exception {
        L2List list = new L2List();
        list.insertBack(5);
        list.toBack();
        list.insertBack(15);
        list.toBack();
        list.insertBack(25);
        list.toFront();
        int removed = list.eraseBack();
        assertEquals(removed, 5, "Удалённое значение должно быть 5");
        assertEquals(list.after(), 15, "После удаления первый элемент 15");
        removed = list.eraseBack();
        assertEquals(removed, 15, "Удалённое значение 15");
        list.toFront();
        assertEquals(list.after(), 25, "Остался только 25");
        removed = list.eraseBack();
        assertEquals(removed, 25, "Удалённое значение 25");
        assertTrue(list.empty(), "Список должен быть пустым");
        System.out.println("testEraseBack: OK");
    }

    private static void testEraseFront() throws Exception {
        L2List list = new L2List();
        // Создаём список [10, 20, 30]
        list.insertBack(10);
        list.toBack();
        list.insertBack(20);
        list.toBack();
        list.insertBack(30);

        // Курсор на последнем элементе (30)
        list.toBack();
        // Удаляем элемент перед курсором — это 20
        int removed = list.eraseFront();
        assertEquals(removed, 20, "Удалённое значение должно быть 20");
        // Теперь перед курсором (который всё ещё на 30) должен быть сам 30, т.к. курсор указывает на узел,
        // а позиция находится после этого узла.
        assertEquals(list.before(), 30, "Перед курсором теперь 30");

        // Удаляем элемент перед курсором — теперь это 10
        removed = list.eraseFront();
        assertEquals(removed, 10, "Удалённое значение 10");
        // После удаления 10 перед курсором остаётся 30
        assertEquals(list.before(), 30, "Перед курсором теперь 30");

        // Остался только 30. Удалим его через eraseBack, предварительно поставив курсор в начало
        list.toFront();
        removed = list.eraseBack();
        assertEquals(removed, 30, "Удалённое значение 30");
        assertTrue(list.empty(), "Список пуст");
        System.out.println("testEraseFront: OK");
    }

    private static void testAfterBefore() throws Exception {
        L2List list = new L2List();
        list.insertBack(7);
        list.toBack();
        list.insertBack(14);
        list.toFront();
        assertEquals(list.after(), 7, "after() возвращает 7");
        list.forward();
        assertEquals(list.after(), 14, "after() возвращает 14");
        assertEquals(list.before(), 7, "before() возвращает 7");
        list.forward();
        System.out.println("testAfterBefore: OK");
    }

    private static void testExceptions() throws Exception {
        L2List list = new L2List();
        boolean exceptionCaught;
        exceptionCaught = false;
        try {
            list.forward();
        } catch (Exception e) {
            exceptionCaught = true;
            assertTrue(e.getMessage().contains("Cannot move forward"), "Неверное сообщение исключения");
        }
        assertTrue(exceptionCaught, "forward() на пустом списке должно бросать исключение");
        exceptionCaught = false;
        try {
            list.backward();
        } catch (Exception e) {
            exceptionCaught = true;
            assertTrue(e.getMessage().contains("Cannot move backward"), "Неверное сообщение");
        }
        assertTrue(exceptionCaught, "backward() на пустом списке должно бросать исключение");
        list.insertBack(100);
        list.toFront();
        exceptionCaught = false;
        try {
            list.before();
        } catch (Exception e) {
            exceptionCaught = true;
            assertTrue(e.getMessage().contains("No element before cursor"), "Неверное сообщение");
        }
        assertTrue(exceptionCaught, "before() в начале должно бросать исключение");
        list.toBack();
        exceptionCaught = false;
        try {
            list.after();
        } catch (Exception e) {
            exceptionCaught = true;
            assertTrue(e.getMessage().contains("No element after cursor"), "Неверное сообщение");
        }
        assertTrue(exceptionCaught, "after() в конце должно бросать исключение");
        exceptionCaught = false;
        try {
            list.eraseBack();
        } catch (Exception e) {
            exceptionCaught = true;
            assertTrue(e.getMessage().contains("No element to erase after cursor"), "Неверное сообщение");
        }
        assertTrue(exceptionCaught, "eraseBack() в конце должно бросать исключение");
        list.toFront();
        exceptionCaught = false;
        try {
            list.eraseFront();
        } catch (Exception e) {
            exceptionCaught = true;
            assertTrue(e.getMessage().contains("No element to erase before cursor"), "Неверное сообщение");
        }
        assertTrue(exceptionCaught, "eraseFront() в начале должно бросать исключение");
        System.out.println("testExceptions: OK");
    }

    private static void testOverflow() throws Exception {
        L2List list = new L2List();
        boolean overflow = false;
        try {
            for (int i = 0; i < 11; i++) {
                list.insertBack(i);
                list.toBack();
            }
        } catch (RuntimeException e) {
            overflow = true;
            assertTrue(e.getMessage().contains("overflow"), "Неверное сообщение об overflow");
        }
        assertTrue(overflow, "Должно быть выброшено исключение при превышении размера");
        System.out.println("testOverflow: OK");
    }

    private static void testClear() throws Exception {
        L2List list = new L2List();
        list.insertBack(1);
        list.toBack();
        list.insertBack(2);
        list.clear();
        assertTrue(list.empty(), "После clear список пуст");
        list.insertBack(42);
        list.toFront();
        assertEquals(list.after(), 42, "После clear и вставки элемент должен быть 42");
        System.out.println("testClear: OK");
    }

    private static void testMixedOperations() throws Exception {
        L2List list = new L2List();
        list.insertBack(10);
        list.toBack();
        list.insertBack(20);
        list.insertFront(5);
        list.toFront();
        list.forward();
        list.insertBack(7);
        list.toFront();
        int[] expected = {5,7,10,20};
        for (int val : expected) {
            assertEquals(list.after(), val, "Порядок нарушен");
            list.forward();
        }
        list.toFront();
        list.forward();
        int removed = list.eraseBack();
        assertEquals(removed, 7, "Удалено должно быть 7");
        list.toFront();
        expected = new int[]{5,10,20};
        for (int val : expected) {
            assertEquals(list.after(), val, "После удаления порядок нарушен");
            list.forward();
        }
        System.out.println("testMixedOperations: OK");
    }

    private static void testPrintOrder() throws Exception {
        L2List list = new L2List();
        for (int i = 1; i <= 5; i++) {
            list.insertBack(i);
            list.toBack();
        }
        System.out.print("Прямой порядок (ожидается 1 2 3 4 5): ");
        list.toFront();
        while (!list.end()) {
            System.out.print(list.after() + " ");
            list.forward();
        }
        System.out.println();
        System.out.print("Обратный порядок (ожидается 5 4 3 2 1): ");
        list.toBack();
        while (!list.begin()) {
            System.out.print(list.before() + " ");
            list.backward();
        }
        System.out.println();
        System.out.println("testPrintOrder: OK (проверьте вывод выше)");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("Assertion failed: " + message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " (ожидалось " + expected + ", получено " + actual + ")");
        }
    }
}