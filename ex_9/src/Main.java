public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тест 1: Сложение ===");
        Matrix a = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix b = new Matrix(new double[][]{
            {5, 6},
            {7, 8}
        });
        Matrix expectedSum = new Matrix(new double[][]{
            {6, 8},
            {10, 12}
        });
        Matrix sum = a.add(b);
        System.out.println("a + b = \n" + sum);
        System.out.println("Сложение экземпляра: " + (sum.equals(expectedSum) ? "Верно" : "Не верно"));

        Matrix staticSum = Matrix.add(a, b);
        System.out.println("Статическое сложение: " + (staticSum.equals(expectedSum) ? "Верно" : "Не верно"));
        System.out.println();

        System.out.println("=== Тест 2: Умножение на число ===");
        double scalar = 2.5;
        Matrix expectedScaled = new Matrix(new double[][]{
            {2.5, 5.0},
            {7.5, 10.0}
        });
        Matrix scaled = a.multiply(scalar);
        System.out.println("a * " + scalar + " = \n" + scaled);
        System.out.println("Умножение экземпляра: " + (scaled.equals(expectedScaled) ? "Верно" : "Не верно"));

        Matrix staticScaled = Matrix.multiply(a, scalar);
        System.out.println("Статическое умножение: " + (staticScaled.equals(expectedScaled) ? "Верно" : "Не верно"));
        System.out.println();

        System.out.println("=== Тест 3: Умножение матриц ===");
        Matrix c = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        Matrix d = new Matrix(new double[][]{
            {7, 8},
            {9, 10},
            {11, 12}
        });
        Matrix expectedProduct = new Matrix(new double[][]{
            {58, 64},
            {139, 154}
        });
        Matrix product = c.multiply(d);
        System.out.println("c * d = \n" + product);
        System.out.println("Умножение экземпляра: " + (product.equals(expectedProduct) ? "Верно" : "Не верно"));

        Matrix staticProduct = Matrix.multiply(c, d);
        System.out.println("Статическое умножение: " + (staticProduct.equals(expectedProduct) ? "Верно" : "Не верно"));
        System.out.println();

        System.out.println("=== Тест 4: Проверка исключений ===");
        try {
            Matrix wrong = new Matrix(2, 3);
            a.add(wrong);
            System.out.println("Ошибка: Сложение не выбросило исключение");
        } catch (IllegalArgumentException e) {
            System.out.println("Сложение разных размеров: " + e.getMessage());
        }

        try {
            Matrix m1 = new Matrix(2, 3);
            Matrix m2 = new Matrix(2, 2); // cols m1 = 3 != rows m2 = 2
            m1.multiply(m2);
            System.out.println("Ошибка: Умножение не выбросило исключение");
        } catch (IllegalArgumentException e) {
            System.out.println("Умножение несовместимых матриц: " + e.getMessage());
        }

        try {
            double[][] badData = {
                {1, 2},
                {3, 4, 5}
            };
            new Matrix(badData);
            System.out.println("Ошибка: Конструктор не выбросил исключение");
        } catch (IllegalArgumentException e) {
            System.out.println("Конструктор с разной длиной строк: " + e.getMessage());
        }
        System.out.println();

        System.out.println("=== Тест 5: Единичная матрица ===");
        Matrix identity = Matrix.identity(3);
        System.out.println("Identity 3x3:\n" + identity);
        System.out.println("Строк: " + identity.getRows() + ", столбцов: " + identity.getCols());
        System.out.println("Элемент [1][1] = " + identity.get(1, 1) + " (ожидается 1.0)");
        System.out.println();

        System.out.println("=== Тест 6: Изменение элемента ===");
        Matrix m = new Matrix(2, 2);
        m.set(0, 1, 42.0);
        System.out.println("После m.set(0,1,42):\n" + m);
        System.out.println("m.get(0,1) = " + m.get(0,1) + " (ожидается 42.0)");
        System.out.println("Все тесты пройдены, если нет сообщений об ошибках выше.");
    }
}