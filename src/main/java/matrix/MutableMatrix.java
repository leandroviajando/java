package matrix;

/**
 * A mutable representation of a mathematical matrix with double elements.
 */
public interface MutableMatrix {
    /**
     * Returns the number of rows in the matrix.
     * 
     * @return height of the matrix
     */
    int height();

    /**
     * Returns the number of columns in the matrix.
     * 
     * @return width of the matrix
     */
    int width();

    /**
     * Returns all elements in row-major order.
     * 
     * @return array containing matrix elements in row-major order
     */
    double[] toRowMajor();

    /**
     * Returns elements as array of row arrays.
     * 
     * @return 2D array where first index is row and second is column
     */
    double[][] toRowArrays();

    /**
     * Returns the element at the specified position.
     * 
     * @param row row index (0-based)
     * @param col column index (0-based)
     * @return element at given position
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    double get(int row, int col);

    /**
     * Sets the element at the specified position.
     * 
     * @param row   row index (0-based)
     * @param col   column index (0-based)
     * @param value value to set
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    void set(int row, int col, double value);

    /**
     * Adds the given matrix to this matrix, modifying this matrix.
     * 
     * @param other matrix to add
     * @throws IllegalArgumentException if matrices have different dimensions
     */
    void add(MutableMatrix other);
}
