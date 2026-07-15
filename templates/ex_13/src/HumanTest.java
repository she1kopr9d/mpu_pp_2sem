public class HumanTest {
    public static void start() {
        int errors = 0;
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        Human h2 = new Human("Петров", "Пётр", "Петрович", 30);
        if (!check(h1.compareTo(h2) < 0, "Фамилия Иванов должна быть меньше Петров")) errors++;
        Human h3 = new Human("Иванов", "Алексей", "Иванович", 20);
        Human h4 = new Human("Иванов", "Иван", "Иванович", 25);
        if (!check(h3.compareTo(h4) < 0, "Имя Алексей должно быть меньше Иван при одинаковой фамилии")) errors++;
        Human h5 = new Human("Иванов", "Иван", "Алексеевич", 30);
        Human h6 = new Human("Иванов", "Иван", "Иванович", 25);
        if (!check(h5.compareTo(h6) < 0, "Отчество Алексеевич должно быть меньше Иванович")) errors++;
        Human h7 = new Human("Иванов", "Иван", "Иванович", 22);
        Human h8 = new Human("Иванов", "Иван", "Иванович", 25);
        if (!check(h7.compareTo(h8) < 0, "Возраст 22 должен быть меньше 25 при одинаковом ФИО")) errors++;
        Human h9 = new Human("Иванов", "Иван", "Иванович", 25);
        Human h10 = new Human("Иванов", "Иван", "Иванович", 25);
        if (!check(h9.compareTo(h10) == 0, "Объекты с одинаковыми полями должны быть равны")) errors++;
        if (!check(h2.compareTo(h1) > 0, "Обратное сравнение должно давать противоположный знак")) errors++;
        if (errors == 0) {
            System.out.println("Все тесты пройдены успешно.");
        } else {
            System.out.println("Обнаружено ошибок: " + errors);
        }
    }

    private static boolean check(boolean condition, String message) {
        if (!condition) {
            System.out.println("FAIL: " + message);
            return false;
        } else {
            System.out.println("OK: " + message);
            return true;
        }
    }
}