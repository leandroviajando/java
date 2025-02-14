package intlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IntListTest {

  @Test
  void testCreateEmpty() {
    IntList list = IntList.from(new int[] {});
    assertEquals(0, list.size());
    assertArrayEquals(new int[] {}, list.toArray());
  }

  @Test
  void testCreateWithValues() {
    IntList list = IntList.from(new int[] { 1, 2, 3 });
    assertEquals(3, list.size());
    assertArrayEquals(new int[] { 1, 2, 3 }, list.toArray());
  }

  @Test
  void testCreateNull() {
    assertThrows(IllegalArgumentException.class, () -> IntList.from(null));
  }

  @Test
  void testAdd() {
    IntList list = IntList.from(new int[] { 1, 2 });
    list.add(3);
    assertEquals(3, list.size());
    assertArrayEquals(new int[] { 1, 2, 3 }, list.toArray());
  }

  @Test
  void testRemoveLast() {
    IntList list = IntList.from(new int[] { 1, 2, 3 });
    assertEquals(3, list.removeLast());
    assertEquals(2, list.size());
    assertArrayEquals(new int[] { 1, 2 }, list.toArray());
  }

  @Test
  void testRemoveLastEmpty() {
    IntList list = IntList.from(new int[] {});
    assertThrows(IllegalStateException.class, () -> list.removeLast());
  }

  @Test
  void testMultipleOperations() {
    IntList list = IntList.from(new int[] { 1, 2 });
    list.add(3);
    list.add(4);
    assertEquals(4, list.removeLast());
    list.add(5);
    assertEquals(4, list.size());
    assertArrayEquals(new int[] { 1, 2, 3, 5 }, list.toArray());
  }
}
