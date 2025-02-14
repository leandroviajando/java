package ringbuffer;

/**
 * Ring buffer implementation of List interface.
 * Provides O(1) access time for get() and constant average time for add/remove
 * near the start or end.
 */
public class RingBuffer implements List {
  private static final int INITIAL_CAPACITY = 10;
  private Object[] elements;
  private int size;
  private int start;

  /**
   * Creates a new empty ring buffer.
   */
  public RingBuffer() {
    elements = new Object[INITIAL_CAPACITY];
    size = 0;
    start = 0;
  }

  @Override
  public Object get(int index) {
    checkIndex(index, size);
    return elements[getActualIndex(index)];
  }

  @Override
  public void set(int index, Object element) {
    checkIndex(index, size);
    elements[getActualIndex(index)] = element;
  }

  @Override
  public void add(int index, Object element) {
    checkIndex(index, size + 1);
    ensureCapacity();

    if (index < size / 2) {
      // Insert near start - shift elements left
      start = (start - 1 + elements.length) % elements.length;
      for (int i = 0; i < index; i++) {
        elements[getActualIndex(i)] = elements[getActualIndex(i + 1)];
      }
    } else {
      // Insert near end - shift elements right
      for (int i = size; i > index; i--) {
        elements[getActualIndex(i)] = elements[getActualIndex(i - 1)];
      }
    }

    elements[getActualIndex(index)] = element;
    size++;
  }

  @Override
  public void remove(int index) {
    checkIndex(index, size);

    if (index < size / 2) {
      // Remove near start - shift elements right
      for (int i = index; i > 0; i--) {
        elements[getActualIndex(i)] = elements[getActualIndex(i - 1)];
      }
      elements[start] = null;
      start = (start + 1) % elements.length;
    } else {
      // Remove near end - shift elements left
      for (int i = index; i < size - 1; i++) {
        elements[getActualIndex(i)] = elements[getActualIndex(i + 1)];
      }
      elements[getActualIndex(size - 1)] = null;
    }

    size--;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[size];
    for (int i = 0; i < size; i++) {
      result[i] = elements[getActualIndex(i)];
    }
    return result;
  }

  @Override
  public boolean contains(Object value) {
    if (value == null) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (value.equals(elements[getActualIndex(i)])) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void remove(Object value) {
    if (value == null) {
      return;
    }
    for (int i = 0; i < size; i++) {
      if (value.equals(elements[getActualIndex(i)])) {
        remove(i);
        return;
      }
    }
  }

  /**
   * Returns the actual array index for a given logical index.
   */
  private int getActualIndex(int logicalIndex) {
    return (start + logicalIndex) % elements.length;
  }

  /**
   * Ensures the buffer has enough capacity for one more element.
   */
  private void ensureCapacity() {
    if (size == elements.length) {
      Object[] newElements = new Object[elements.length * 2];
      for (int i = 0; i < size; i++) {
        newElements[i] = elements[getActualIndex(i)];
      }
      elements = newElements;
      start = 0;
    }
  }

  /**
   * Checks if the given index is valid.
   */
  private void checkIndex(int index, int bound) {
    if (index < 0 || index >= bound) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }
}
