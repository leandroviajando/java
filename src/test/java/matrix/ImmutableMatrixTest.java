package matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ImmutableMatrixTest {
    private ImmutableMatrix createMatrix(int width, int height, double[] elements) {
        // Test both implementations
        return new RowMajorImmutableMatrix(width, height, elements);
        // Uncomment to test column-major implementation:
        // return new ColumnMajorImmutableMatrix(width, height, elements);
    }

    @Test
    void testConstruction() {
        double[] elements = { 1, 2, 3, 4, 5, 6 };
        ImmutableMatrix matrix = createMatrix(2, 3, elements);
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
    void testGet() {
        ImmutableMatrix matrix = createMatrix(2, 3, new double[] { 1, 2, 3, 4, 5, 6 });
        assertEquals(1, matrix.get(0, 0));
        assertEquals(2, matrix.get(0, 1));
        assertEquals(3, matrix.get(1, 0));
        assertEquals(6, matrix.get(2, 1));
    }

    @Test
    void testInvalidGet() {
        ImmutableMatrix matrix = createMatrix(2, 3, new double[] { 1, 2, 3, 4, 5, 6 });
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, 2));
    }

    @Test
    void testToRowArrays() {
        ImmutableMatrix matrix = createMatrix(2, 3, new double[] { 1, 2, 3, 4, 5, 6 });
        double[][] expected = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        assertArrayEquals(expected, matrix.toRowArrays());
    }

    @Test
    void testPlus() {
        ImmutableMatrix m1 = createMatrix(2, 2, new double[] { 1, 2, 3, 4 });
        ImmutableMatrix m2 = createMatrix(2, 2, new double[] { 5, 6, 7, 8 });
        ImmutableMatrix result = m1.plus(m2);
        assertArrayEquals(new double[] { 6, 8, 10, 12 }, result.toRowMajor());
    }

    @Test
    void testInvalidPlus() {
        ImmutableMatrix m1 = createMatrix(2, 2, new double[] { 1, 2, 3, 4 });
        ImmutableMatrix m2 = createMatrix(2, 3, new double[] { 5, 6, 7, 8, 9, 10 });
        assertThrows(IllegalArgumentException.class, () -> m1.plus(m2));
    }

    @Test
    void testTimes() {
        ImmutableMatrix m1 = createMatrix(2, 2, new double[] { 1, 2, 3, 4 });
        ImmutableMatrix m2 = createMatrix(2, 2, new double[] { 5, 6, 7, 8 });
        ImmutableMatrix result = m1.times(m2);
        assertArrayEquals(new double[] { 19, 22, 43, 50 }, result.toRowMajor());
    }

    @Test
    void testInvalidTimes() {
        ImmutableMatrix m1 = createMatrix(3, 2, new double[] { 1, 2, 3, 4, 5, 6 });
        ImmutableMatrix m2 = createMatrix(2, 2, new double[] { 7, 8, 9, 10 });
        assertThrows(IllegalArgumentException.class, () -> m1.times(m2));
    }
}
