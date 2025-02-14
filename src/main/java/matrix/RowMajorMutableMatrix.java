package matrix;

/**
 * Implementation of MutableMatrix that stores elements in row-major order.
 */
public class RowMajorMutableMatrix implements MutableMatrix {
    private final int height;
    private final int width;
    private final double[] elements;

    /**
     * Creates a new matrix with the given dimensions and elements in row-major
     * order.
     * 
     * @param width    number of columns
     * @param height   number of rows
     * @param elements array containing elements in row-major order
     * @throws IllegalArgumentException if dimensions don't match elements length
     */
    public RowMajorMutableMatrix(int width, int height, double[] elements) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        if (elements == null || elements.length != width * height) {
            throw new IllegalArgumentException("Elements array must match dimensions");
        }
        this.width = width;
        this.height = height;
        this.elements = elements.clone();
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public double[] toRowMajor() {
        return elements.clone();
    }

    @Override
    public double[][] toRowArrays() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public double get(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        return elements[row * width + col];
    }

    @Override
    public void set(int row, int col, double value) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void add(MutableMatrix other) {
        if (height != other.height() || width != other.width()) {
            throw new IllegalArgumentException("Matrix dimensions must match");
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
