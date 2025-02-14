package matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MutableMatrixTest {
    private MutableMatrix createMatrix(int width, int height, double[] elements) {
        // Test both implementations
        return new RowMajorMutableMatrix(width, height, elements);
        // Uncomment to test row-arrays implementation:
        // return new RowArraysMutableMatrix(width, height, elements);
    }

    @Test
    void testConstruction() {
        double[] elements = { 1, 2, 3, 4, 5, 6 };
        MutableMatrix matrix = createMatrix(2, 3, elements);
        assertEquals(2, matrix.width());
        assertEquals(3, matrix.height());
        assertArrayEquals(elements, matrix.toRowMajor());
    }

    @Test
    void testInvalidConstruction() {
        assertThrows(IllegalArgumentException.class, () -> createMatrix(2, 3, null));
        assertThrows(IllegalArgumentException.class, () -> createMatrix(2, 3, new double[5]));
        assertThrows(IllegalArgumentException.class, () -> createMatrix(0, 3, new double[0]));
        assertThrows(IllegalArgumentException.class, () -> createMatrix(2, -1, new double[0]));
    }

    @Test
    void testGetSet() {
        MutableMatrix matrix = createMatrix(2, 3, new double[] { 1, 2, 3, 4, 5, 6 });
        assertEquals(1, matrix.get(0, 0));
        matrix.set(0, 0, 10);
        assertEquals(10, matrix.get(0, 0));
    }

    @Test
    void testInvalidGetSet() {
        MutableMatrix matrix = createMatrix(2, 3, new double[] { 1, 2, 3, 4, 5, 6 });
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(3, 0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(0, 2, 1));
    }

    @Test
    void testToRowArrays() {
        MutableMatrix matrix = createMatrix(2, 3, new double[] { 1, 2, 3, 4, 5, 6 });
        double[][] expected = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        assertArrayEquals(expected, matrix.toRowArrays());
    }

    @Test
    void testAdd() {
        MutableMatrix m1 = createMatrix(2, 2, new double[] { 1, 2, 3, 4 });
        MutableMatrix m2 = createMatrix(2, 2, new double[] { 5, 6, 7, 8 });
        m1.add(m2);
        assertArrayEquals(new double[] { 6, 8, 10, 12 }, m1.toRowMajor());
    }

    @Test
    void testInvalidAdd() {
        MutableMatrix m1 = createMatrix(2, 2, new double[] { 1, 2, 3, 4 });
        MutableMatrix m2 = createMatrix(2, 3, new double[] { 5, 6, 7, 8, 9, 10 });
        assertThrows(IllegalArgumentException.class, () -> m1.add(m2));
    }
}
