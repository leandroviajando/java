package shapes;

import java.util.Locale;

/**
 * Represents a triangle defined by one angle and the lengths of its adjacent
 * sides.
 */
public class Triangle implements Shape {
    private final double angle; // in radians
    private final double side1;
    private final double side2;

    /**
     * Creates a new triangle with the given angle and adjacent sides.
     * 
     * @param angle the angle in radians between side1 and side2
     * @param side1 the length of the first side
     * @param side2 the length of the second side
     * @throws IllegalArgumentException if angle is not in (0,π) or sides are
     *                                  negative
     */
    public Triangle(double angle, double side1, double side2) {
        if (angle <= 0 || angle >= Math.PI) {
            throw new IllegalArgumentException("Angle must be between 0 and π");
        }
        if (side1 < 0 || side2 < 0) {
            throw new IllegalArgumentException("Sides cannot be negative");
        }
        this.angle = angle;
        this.side1 = side1;
        this.side2 = side2;
    }

    /**
     * Returns the angle between the two defining sides.
     * 
     * @return the angle in radians
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Returns the length of the first side.
     * 
     * @return the length of side1
     */
    public double getSide1() {
        return side1;
    }

    /**
     * Returns the length of the second side.
     * 
     * @return the length of side2
     */
    public double getSide2() {
        return side2;
    }

    @Override
    public double getCircumference() {
        return side1 + side2 + Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2)
                - 2 * side1 * side2 * Math.cos(angle));
    }

    @Override
    public double getArea() {
        return 0.5 * side1 * side2 * Math.sin(angle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Triangle))
            return false;
        Triangle other = (Triangle) obj;
        return Double.compare(angle, other.angle) == 0
                && Double.compare(side1, other.side1) == 0
                && Double.compare(side2, other.side2) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(angle) * 31 * 31
                + Double.hashCode(side1) * 31
                + Double.hashCode(side2);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Triangle(angle=%.2f, side1=%.2f, side2=%.2f)",
                angle, side1, side2);
    }
}
