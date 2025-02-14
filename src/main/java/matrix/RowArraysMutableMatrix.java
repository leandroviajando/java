package matrix;

/**
 * Implementation of MutableMatrix that stores elements as array of row arrays.
 */
public class RowArraysMutableMatrix implements MutableMatrix {
    private final double[][] rows;

    /**
     * Creates a new matrix with the given dimensions and elements in row-major
     * order.
     * 
     * @param width    number of columns
     * @param height   number of rows
     * @param elements array containing elements in row-major order
     * @throws IllegalArgumentException if dimensions don't match elements length
     */
    public RowArraysMutableMatrix(int width, int height, double[] elements) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        if (elements == null || elements.length != width * height) {
            throw new IllegalArgumentException("Elements array must match dimensions");
        }

        this.rows = new double[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(elements, i * width, rows[i], 0, width);
        }
    }

    @Override
    public int height() {
        return rows.length;
    }

    @Override
    public int width() {
        return rows[0].length;
    }

    @Override
    public double[] toRowMajor() {
        int height = height();
        int width = width();
        double[] result = new double[height * width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(rows[i], 0, result, i * width, width);
        }
        return result;
    }

    @Override
    public double[][] toRowArrays() {
        double[][] result = new double[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i].clone();
        }
        return result;
    }

    @Override
    public double get(int row, int col) {
        if (row < 0 || row >= rows.length || col < 0 || col >= rows[0].length) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        return rows[row][col];
    }

    @Override
    public void set(int row, int col, double value) {
        if (row < 0 || row >= rows.length || col < 0 || col >= rows[0].length) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        rows[row][col] = value;
    }

    @Override
    public void add(MutableMatrix other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
