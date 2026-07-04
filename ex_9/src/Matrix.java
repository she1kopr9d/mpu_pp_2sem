import java.util.Arrays;

public class Matrix {
    private final int rows;
    private final int cols;
    private final double[][] data;

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть положительными");
        }
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public Matrix(double[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        this.rows = data.length;
        this.cols = data[0].length;
        for (int i = 1; i < rows; i++) {
            if (data[i].length != cols) {
                throw new IllegalArgumentException("Все строки должны иметь одинаковую длину");
            }
        }
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public double[][] getData() {
        double[][] copy = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, copy[i], 0, cols);
        }
        return copy;
    }

    public double get(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Индекс вне границ матрицы");
        }
        return data[row][col];
    }

    public void set(int row, int col, double value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Индекс вне границ матрицы");
        }
        data[row][col] = value;
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры для сложения");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return result;
    }

    public Matrix multiply(double scalar) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] * scalar;
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException(
                "Число столбцов первой матрицы (" + this.cols + 
                ") должно равняться числу строк второй (" + other.rows + ")"
            );
        }
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public static Matrix add(Matrix a, Matrix b) {
        return a.add(b);
    }

    public static Matrix multiply(Matrix a, double scalar) {
        return a.multiply(scalar);
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        return a.multiply(b);
    }

    public static Matrix identity(int size) {
        Matrix m = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            m.data[i][i] = 1.0;
        }
        return m;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append(Arrays.toString(data[i]));
            if (i < rows - 1) sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (rows != other.rows || cols != other.cols) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Double.compare(this.data[i][j], other.data[i][j]) != 0)
                    return false;
            }
        }
        return true;
    }
}