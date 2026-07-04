public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тесты для R3Vector ===\n");

        System.out.println("--- 1. Конструкторы ---");
        R3Vector v1 = new R3Vector(1.0, 2.0, 3.0);
        R3Vector v2 = new R3Vector(4, 5, 6);
        R3Vector v3 = new R3Vector(1.5f, 2.5f, 3.5f);
        R3Vector v4 = new R3Vector(10L, 20L, 30L);
        System.out.println("v1 (double): " + v1.x + ", " + v1.y + ", " + v1.z);
        System.out.println("v2 (int):    " + v2.x + ", " + v2.y + ", " + v2.z);
        System.out.println("v3 (float):  " + v3.x + ", " + v3.y + ", " + v3.z);
        System.out.println("v4 (long):   " + v4.x + ", " + v4.y + ", " + v4.z);
        System.out.println("ПРОЙДЕНО (конструкторы работают)\n");

        System.out.println("--- 2. Сложение ---");
        R3Vector a = new R3Vector(1, 2, 3);
        R3Vector b = new R3Vector(4, 5, 6);
        R3Vector expectedSum = new R3Vector(5, 7, 9);

        R3Vector sumInstance = a.add(b);
        R3Vector sumStatic = R3Vector.add(a, b);

        boolean sumOk = sumInstance.equals(expectedSum) && sumStatic.equals(expectedSum);
        System.out.println("a + b (экземпляр) = " + vecToString(sumInstance) + "   " + (sumOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a + b (статический) = " + vecToString(sumStatic) + "   " + (sumOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n--- 3. Умножение на скаляр ---");
        double scalar = 2.5;
        R3Vector expectedScaled = new R3Vector(2.5, 5.0, 7.5);

        R3Vector scaledInstance = a.multiply(scalar);
        R3Vector scaledStatic = R3Vector.multiply(a, scalar);

        boolean scaledOk = scaledInstance.equals(expectedScaled) && scaledStatic.equals(expectedScaled);
        System.out.println("a * " + scalar + " (экземпляр) = " + vecToString(scaledInstance) + "   " + (scaledOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a * " + scalar + " (статический) = " + vecToString(scaledStatic) + "   " + (scaledOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n--- 4. Скалярное произведение ---");
        double dotInstance = a.dot(b);
        double dotStatic = R3Vector.dot(a, b);
        double expectedDot = 1*4 + 2*5 + 3*6;
        boolean dotOk = (dotInstance == expectedDot) && (dotStatic == expectedDot);
        System.out.println("a · b (экземпляр) = " + dotInstance + "   " + (dotOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a · b (статический) = " + dotStatic + "   " + (dotOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n--- 5. Векторное произведение ---");
        R3Vector expectedCross = new R3Vector(
            a.y*b.z - a.z*b.y,
            a.z*b.x - a.x*b.z,
            a.x*b.y - a.y*b.x
        );
        R3Vector crossInstance = a.cross(b);
        R3Vector crossStatic = R3Vector.cross(a, b);
        boolean crossOk = crossInstance.equals(expectedCross) && crossStatic.equals(expectedCross);
        System.out.println("a × b (экземпляр) = " + vecToString(crossInstance) + "   " + (crossOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a × b (статический) = " + vecToString(crossStatic) + "   " + (crossOk ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n--- 6. Сравнение ---");
        R3Vector c = new R3Vector(1, 2, 3);
        R3Vector d = new R3Vector(1.0, 2.0, 3.0);
        R3Vector e = new R3Vector(4, 5, 6);

        boolean eq1 = a.equals(c);
        boolean eq2 = a.equals(d);
        boolean eq3 = a.equals(e);
        boolean eq4 = R3Vector.equals(a, c);
        System.out.println("a.equals(c) (одинаковые)           = " + eq1 + "   " + (eq1 ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a.equals(d) (double vs int)        = " + eq2 + "   " + (eq2 ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a.equals(e) (разные)               = " + eq3 + "   " + (!eq3 ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("R3Vector.equals(a, c) (статический)= " + eq4 + "   " + (eq4 ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n--- 7. Краевые случаи ---");
        boolean notNull = !a.equals(null);
        boolean notString = !a.equals("строка");
        System.out.println("a.equals(null) = false   " + (notNull ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));
        System.out.println("a.equals(\"строка\") = false   " + (notString ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n--- 8. Неизменяемость ---");
        R3Vector original = new R3Vector(1, 2, 3);
        original.add(new R3Vector(10, 10, 10));
        original.multiply(5);
        original.cross(new R3Vector(1,1,1));
        boolean unchanged = original.equals(new R3Vector(1,2,3));
        System.out.println("Исходный вектор не изменился: " + (unchanged ? "ПРОЙДЕНО" : "НЕ ПРОЙДЕНО"));

        System.out.println("\n=== Все тесты завершены ===");
    }

    private static String vecToString(R3Vector v) {
        return "(" + v.x + ", " + v.y + ", " + v.z + ")";
    }
}