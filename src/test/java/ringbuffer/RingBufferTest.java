package ringbuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RingBufferTest {
  @Test
  void testEmptyBuffer() {
    List buffer = new RingBuffer();
    assertEquals(0, buffer.size());
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.get(0));
  }

  @Test
  void testAddGet() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");

    assertEquals("A", buffer.get(0));
    assertEquals("B", buffer.get(1));
    assertEquals("C", buffer.get(2));
    assertEquals(3, buffer.size());
  }

  @Test
  void testSet() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.set(0, "X");
    buffer.set(1, "Y");

    assertEquals("X", buffer.get(0));
    assertEquals("Y", buffer.get(1));
  }

  @Test
  void testAddAtStart() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(0, "B");
    buffer.add(0, "C");

    assertEquals("C", buffer.get(0));
    assertEquals("B", buffer.get(1));
    assertEquals("A", buffer.get(2));
  }

  @Test
  void testAddInMiddle() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(1, "C");

    assertEquals("A", buffer.get(0));
    assertEquals("C", buffer.get(1));
    assertEquals("B", buffer.get(2));
  }

  @Test
  void testRemoveFromStart() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");
    buffer.remove(0);

    assertEquals("B", buffer.get(0));
    assertEquals("C", buffer.get(1));
    assertEquals(2, buffer.size());
  }

  @Test
  void testRemoveFromMiddle() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");
    buffer.remove(1);

    assertEquals("A", buffer.get(0));
    assertEquals("C", buffer.get(1));
    assertEquals(2, buffer.size());
  }

  @Test
  void testRemoveFromEnd() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");
    buffer.remove(2);

    assertEquals("A", buffer.get(0));
    assertEquals("B", buffer.get(1));
    assertEquals(2, buffer.size());
  }

  @Test
  void testGrowCapacity() {
    List buffer = new RingBuffer();
    for (int i = 0; i < 20; i++) {
      buffer.add(i, String.valueOf(i));
    }

    for (int i = 0; i < 20; i++) {
      assertEquals(String.valueOf(i), buffer.get(i));
    }
  }

  @Test
  void testInvalidOperations() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");

    assertThrows(IndexOutOfBoundsException.class, () -> buffer.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.set(-1, "X"));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.set(1, "X"));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.add(-1, "X"));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.add(2, "X"));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.remove(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> buffer.remove(1));
  }

  @Test
  void testWraparound() {
    List buffer = new RingBuffer();
    // Fill buffer
    for (int i = 0; i < 8; i++) {
      buffer.add(i, String.valueOf(i));
    }
    // Remove from start to force wraparound
    for (int i = 0; i < 3; i++) {
      buffer.remove(0);
    }
    // Add new elements
    for (int i = 0; i < 3; i++) {
      buffer.add(buffer.size(), String.valueOf(i + 8));
    }

    // Verify contents
    for (int i = 0; i < buffer.size(); i++) {
      assertEquals(String.valueOf(i + 3), buffer.get(i));
    }
  }

  @Test
  void testToArray() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");

    Object[] array = buffer.toArray();
    assertEquals(3, array.length);
    assertEquals("A", array[0]);
    assertEquals("B", array[1]);
    assertEquals("C", array[2]);
  }

  @Test
  void testContains() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");

    assertTrue(buffer.contains("A"));
    assertTrue(buffer.contains("B"));
    assertTrue(buffer.contains("C"));
    assertFalse(buffer.contains("D"));
    assertFalse(buffer.contains(null));
  }

  @Test
  void testRemoveObject() {
    List buffer = new RingBuffer();
    buffer.add(0, "A");
    buffer.add(1, "B");
    buffer.add(2, "C");

    buffer.remove("B");
    assertEquals(2, buffer.size());
    assertEquals("A", buffer.get(0));
    assertEquals("C", buffer.get(1));

    // Remove non-existent element should not change list
    buffer.remove("X");
    assertEquals(2, buffer.size());
    assertEquals("A", buffer.get(0));
    assertEquals("C", buffer.get(1));

    // Remove null should not change list
    buffer.remove(null);
    assertEquals(2, buffer.size());
    assertEquals("A", buffer.get(0));
    assertEquals("C", buffer.get(1));
  }
}
