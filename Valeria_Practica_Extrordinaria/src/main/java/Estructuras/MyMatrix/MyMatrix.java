package Estructuras.MyMatrix;

public class MyMatrix<T> {
    private int rows;
    private int cols;
    private Object[][] matrix;

    public MyMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones de la matriz deben ser mayores a 0.");
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = new Object[rows][cols];
    }

    public void set(int r, int c, T element) {
        if (isValid(r, c)) {
            matrix[r][c] = element;
        } else {
            throw new IndexOutOfBoundsException("Intento de acceso fuera de los límites de la habitación.");
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int r, int c) {
        if (isValid(r, c)) {
            return (T) matrix[r][c];
        }
        throw new IndexOutOfBoundsException("Intento de acceso fuera de los límites de la habitación.");
    }

    public boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}