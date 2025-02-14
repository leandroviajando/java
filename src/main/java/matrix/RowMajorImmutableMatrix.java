package matrix;

/**
 * Implementation of ImmutableMatrix that stores elements in row-major order.
 */
public class RowMajorImmutableMatrix implements ImmutableMatrix {
    private final int height;
    private final int width;
    private final double[] elements;

    /**
     * Creates a new matrix with the given dimensions and elements in row-major order.
     * 
     * @param width    number of columns
     * @param height   number of rows
     * @param elements array containing elements in row-major order
     * @throws IllegalArgumentException if dimensions don't match elements length
     */
    public RowMajorImmutableMatrix(int width, int height, double[] elements) {
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
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(elements, i * width, result[i], 0, width);
        }
        return result;
    }

    @Override
    public double get(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        return elements[row * width + col];
    }

    @Override
    public ImmutableMatrix plus(ImmutableMatrix other) {
        if (height != other.height() || width != other.width()) {
            throw new IllegalArgumentException("Matrix dimensions must match");
        }
        double[] result = new double[height * width];
        for (int i = 0; i < height * width; i++) {
            result[i] = elements[i] + other.toRowMajor()[i];
        }
        return new RowMajorImmutableMatrix(width, height, result);
    }

    @Override
    public ImmutableMatrix times(ImmutableMatrix other) {
        if (width != other.height()) {
            throw new IllegalArgumentException("Matrix dimensions must match");
        }
        double[] result = new double[height * other.width()];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < other.width(); j++) {
                double sum = 0;
                for (int k = 0; k < width; k++) {
                    sum += get(i, k) * other.get(k, j);
                }
                result[i * other.width() + j] = sum;
            }
        }
        return new RowMajorImmutableMatrix(other.width(), height, result);
    }
}
