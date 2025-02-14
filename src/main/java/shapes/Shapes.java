package shapes;

/**
 * Utility class for operations on arrays of shapes.
 */
public class Shapes {
    private Shapes() {
    } // Prevent instantiation

    /**
     * Returns the total circumference of all shapes in the array.
     * 
     * @param shapes array of shapes
     * @return sum of circumferences
     * @throws IllegalArgumentException if shapes is null
     */
    public static double totalCircumference(Shape[] shapes) {
        if (shapes == null) {
            throw new IllegalArgumentException("Shapes array cannot be null");
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the total area of all shapes in the array.
     * 
     * @param shapes array of shapes
     * @return sum of areas
     * @throws IllegalArgumentException if shapes is null
     */
    public static double totalArea(Shape[] shapes) {
        if (shapes == null) {
            throw new IllegalArgumentException("Shapes array cannot be null");
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
