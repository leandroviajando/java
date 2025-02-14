package shapes;

import java.util.Locale;

/**
 * Represents a rectangle defined by its width and height.
 */
public class Rectangle implements Shape {
    private final double width;
    private final double height;

    /**
     * Creates a new rectangle with the given width and height.
     * 
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @throws IllegalArgumentException if width or height is negative
     */
    public Rectangle(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height cannot be negative");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of this rectangle.
     * 
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of this rectangle.
     * 
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public double getCircumference() {
        return 2 * (width + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Rectangle))
            return false;
        Rectangle other = (Rectangle) obj;
        return Double.compare(width, other.width) == 0
                && Double.compare(height, other.height) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(width) * 31 + Double.hashCode(height);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Rectangle(width=%.2f, height=%.2f)", width, height);
    }
}
