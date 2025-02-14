package shapes;

import java.util.Locale;

/**
 * Represents a circle defined by its radius.
 */
public class Circle implements Shape {
    private final double radius;

    /**
     * Creates a new circle with the given radius.
     * 
     * @param radius the radius of the circle
     * @throws IllegalArgumentException if radius is negative
     */
    public Circle(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        this.radius = radius;
    }

    /**
     * Returns the radius of this circle.
     * 
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Circle))
            return false;
        Circle other = (Circle) obj;
        return Double.compare(radius, other.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Circle(radius=%.2f)", radius);
    }
}
