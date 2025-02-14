package linkedhashset;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkedHashSetTest {
  @Test
  void testEmptySet() {
    Set set = new LinkedHashSet();
    assertEquals(0, set.size());
    assertArrayEquals(new Object[0], set.toArray());
  }

  @Test
  void testAddAndContains() {
    Set set = new LinkedHashSet();
    set.add("A");
    set.add("B");
    set.add("C");

    assertTrue(set.contains("A"));
    assertTrue(set.contains("B"));
    assertTrue(set.contains("C"));
    assertFalse(set.contains("D"));
    assertEquals(3, set.size());
  }

  @Test
  void testAddDuplicate() {
    Set set = new LinkedHashSet();
    set.add("A");
    set.add("B");
    set.add("A"); // Should not affect set or order

    assertEquals(2, set.size());
    assertArrayEquals(new Object[] { "A", "B" }, set.toArray());
  }

  @Test
  void testRemove() {
    Set set = new LinkedHashSet();
    set.add("A");
    set.add("B");
    set.add("C");

    set.remove("B");
    assertEquals(2, set.size());
    assertFalse(set.contains("B"));
    assertArrayEquals(new Object[] { "A", "C" }, set.toArray());
  }

  @Test
  void testRemoveNonexistent() {
    Set set = new LinkedHashSet();
    set.add("A");
    set.add("B");

    set.remove("C"); // Should not affect set
    assertEquals(2, set.size());
    assertArrayEquals(new Object[] { "A", "B" }, set.toArray());
  }

  @Test
  void testInsertionOrder() {
    Set set = new LinkedHashSet();
    set.add("C");
    set.add("A");
    set.add("B");

    assertArrayEquals(new Object[] { "C", "A", "B" }, set.toArray());
  }

  @Test
  void testNullArguments() {
    Set set = new LinkedHashSet();
    assertThrows(IllegalArgumentException.class, () -> set.add(null));
    assertThrows(IllegalArgumentException.class, () -> set.contains(null));
    assertThrows(IllegalArgumentException.class, () -> set.remove(null));
  }

  @Test
  void testManyElements() {
    Set set = new LinkedHashSet();
    // Add enough elements to force hash table resizing
    for (int i = 0; i < 100; i++) {
      set.add("Element" + i);
    }
    assertEquals(100, set.size());
    for (int i = 0; i < 100; i++) {
      assertTrue(set.contains("Element" + i));
    }
  }

  @Test
  void testRemoveFromEnds() {
    Set set = new LinkedHashSet();
    set.add("A");
    set.add("B");
    set.add("C");

    // Remove from front
    set.remove("A");
    assertArrayEquals(new Object[] { "B", "C" }, set.toArray());

    // Remove from back
    set.remove("C");
    assertArrayEquals(new Object[] { "B" }, set.toArray());
  }
}
