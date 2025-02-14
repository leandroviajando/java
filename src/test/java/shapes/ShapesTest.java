package shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ShapesTest {
  private static final double DELTA = 1e-10;

  @Test
  void testCircleConstruction() {
    Circle c = new Circle(5);
    assertEquals(5, c.getRadius());
    assertThrows(IllegalArgumentException.class, () -> new Circle(-1));
  }

  @Test
  void testCircleCircumference() {
    Circle c = new Circle(2);
    assertEquals(4 * Math.PI, c.getCircumference(), DELTA);
  }

  @Test
  void testCircleArea() {
    Circle c = new Circle(2);
    assertEquals(4 * Math.PI, c.getArea(), DELTA);
  }

  @Test
  void testRectangleConstruction() {
    Rectangle r = new Rectangle(3, 4);
    assertEquals(3, r.getWidth());
    assertEquals(4, r.getHeight());
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 4));
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(3, -1));
  }

  @Test
  void testRectangleCircumference() {
    Rectangle r = new Rectangle(3, 4);
    assertEquals(14, r.getCircumference(), DELTA);
  }

  @Test
  void testRectangleArea() {
    Rectangle r = new Rectangle(3, 4);
    assertEquals(12, r.getArea(), DELTA);
  }

  @Test
  void testTriangleConstruction() {
    Triangle t = new Triangle(Math.PI / 2, 3, 4);
    assertEquals(Math.PI / 2, t.getAngle(), DELTA);
    assertEquals(3, t.getSide1());
    assertEquals(4, t.getSide2());
    assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 3, 4));
    assertThrows(IllegalArgumentException.class, () -> new Triangle(Math.PI, 3, 4));
    assertThrows(IllegalArgumentException.class, () -> new Triangle(Math.PI / 2, -1, 4));
    assertThrows(IllegalArgumentException.class, () -> new Triangle(Math.PI / 2, 3, -1));
  }

  @Test
  void testTriangleCircumference() {
    Triangle t = new Triangle(Math.PI / 2, 3, 4);
    assertEquals(12, t.getCircumference(), DELTA); // 3 + 4 + 5 for right triangle
  }

  @Test
  void testTriangleArea() {
    Triangle t = new Triangle(Math.PI / 2, 3, 4);
    assertEquals(6, t.getArea(), DELTA); // (3 * 4)/2 for right triangle
  }

  @Test
  void testTotalCircumference() {
    Shape[] shapes = {
        new Circle(2),
        new Rectangle(3, 4),
        new Triangle(Math.PI / 2, 3, 4)
    };
    double expected = 4 * Math.PI // circle
        + 14 // rectangle
        + 12; // triangle
    assertEquals(expected, Shapes.totalCircumference(shapes), DELTA);
  }

  @Test
  void testTotalArea() {
    Shape[] shapes = {
        new Circle(2),
        new Rectangle(3, 4),
        new Triangle(Math.PI / 2, 3, 4)
    };
    double expected = 4 * Math.PI // circle
        + 12 // rectangle
        + 6; // triangle
    assertEquals(expected, Shapes.totalArea(shapes), DELTA);
  }

  @Test
  void testShapeEquality() {
    Shape c1 = new Circle(5);
    Shape c2 = new Circle(5);
    Shape c3 = new Circle(6);
    Shape r1 = new Rectangle(3, 4);
    Shape r2 = new Rectangle(3, 4);
    Shape r3 = new Rectangle(4, 3);
    Shape t1 = new Triangle(Math.PI / 2, 3, 4);
    Shape t2 = new Triangle(Math.PI / 2, 3, 4);
    Shape t3 = new Triangle(Math.PI / 3, 3, 4);

    assertEquals(c1, c2);
    assertNotEquals(c1, c3);
    assertEquals(r1, r2);
    assertNotEquals(r1, r3);
    assertEquals(t1, t2);
    assertNotEquals(t1, t3);
    assertNotEquals(c1, r1);
    assertNotEquals(r1, t1);
  }

  @Test
  void testShapeToString() {
    Shape c = new Circle(5);
    Shape r = new Rectangle(3, 4);
    Shape t = new Triangle(Math.PI / 2, 3, 4);

    assertEquals("Circle(radius=5.00)", c.toString());
    assertEquals("Rectangle(width=3.00, height=4.00)", r.toString());
    assertEquals("Triangle(angle=1.57, side1=3.00, side2=4.00)", t.toString());
  }

  @Test
  void testDynamicBinding() {
    // Test that the right implementation is called regardless of reference type
    Shape circle = new Circle(2);
    Shape rectangle = new Rectangle(3, 4);
    Shape triangle = new Triangle(Math.PI / 2, 3, 4);

    // Each shape should use its own implementation
    assertEquals(4 * Math.PI, circle.getCircumference(), DELTA);
    assertEquals(14, rectangle.getCircumference(), DELTA);
    assertEquals(12, triangle.getCircumference(), DELTA);

    assertEquals(4 * Math.PI, circle.getArea(), DELTA);
    assertEquals(12, rectangle.getArea(), DELTA);
    assertEquals(6, triangle.getArea(), DELTA);
  }

  @Test
  void testDynamicBindingInCollection() {
    // Test dynamic binding through different collection types
    List<Shape> shapes = Arrays.asList(
        new Circle(2),
        new Rectangle(3, 4),
        new Triangle(Math.PI / 2, 3, 4));

    double expectedCircumference = 4 * Math.PI + 14 + 12;
    double expectedArea = 4 * Math.PI + 12 + 6;

    double totalCircumference = 0;
    double totalArea = 0;

    // Each shape.getCircumference() call should invoke the right implementation
    for (Shape shape : shapes) {
      totalCircumference += shape.getCircumference();
      totalArea += shape.getArea();
    }

    assertEquals(expectedCircumference, totalCircumference, DELTA);
    assertEquals(expectedArea, totalArea, DELTA);
  }

  @Test
  void testDynamicBindingWithMixedTypes() {
    // Test that implementations work correctly even when mixed with specific types
    Circle circle = new Circle(2);
    Shape rectangle = new Rectangle(3, 4);
    Triangle triangle = new Triangle(Math.PI / 2, 3, 4);

    Shape[] shapes = { circle, rectangle, triangle };

    // Test both through Shape reference and specific type reference
    assertEquals(circle.getCircumference(), shapes[0].getCircumference(), DELTA);
    assertEquals(rectangle.getCircumference(), shapes[1].getCircumference(), DELTA);
    assertEquals(triangle.getCircumference(), shapes[2].getCircumference(), DELTA);
  }
}
