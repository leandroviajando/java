package matrix;

/**
 * An immutable representation of a mathematical matrix with double elements.
 */
public interface ImmutableMatrix {
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
     * Creates a new matrix that is the sum of this matrix and the given matrix.
     * 
     * @param other matrix to add
     * @return new matrix containing the sum
     * @throws IllegalArgumentException if matrices have different dimensions
     */
    ImmutableMatrix plus(ImmutableMatrix other);

    /**
     * Creates a new matrix that is the product of this matrix and the given matrix.
     * 
     * @param other matrix to multiply with
     * @return new matrix containing the product
     * @throws IllegalArgumentException if matrices have incompatible dimensions
     */
    ImmutableMatrix times(ImmutableMatrix other);
}
