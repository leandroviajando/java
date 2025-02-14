package matrix;

/**
 * Implementation of ImmutableMatrix that stores elements in column-major order.
 */
public class ColumnMajorImmutableMatrix implements ImmutableMatrix {
    private final int height;
    private final int width;
    private final double[] elements;

    /**
     * Creates a new matrix with the given dimensions and elements in row-major
     * order.
     * 
     * @param width            number of columns
     * @param height           number of rows
     * @param rowMajorElements array containing elements in row-major order
     * @throws IllegalArgumentException if dimensions don't match elements length
     */
    public ColumnMajorImmutableMatrix(int width, int height, double[] rowMajorElements) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        if (rowMajorElements == null || rowMajorElements.length != width * height) {
            throw new IllegalArgumentException("Elements array must match dimensions");
        }
        this.width = width;
        this.height = height;
        this.elements = new double[width * height];

        // Convert from row-major to column-major
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ImmutableMatrix plus(ImmutableMatrix other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ImmutableMatrix times(ImmutableMatrix other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
