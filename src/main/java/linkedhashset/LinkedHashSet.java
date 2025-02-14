package linkedhashset;

/**
 * A Set implementation that maintains insertion order and provides
 * constant-time
 * operations using a combination of a hash table and a doubly-linked list.
 */
public class LinkedHashSet implements Set {
  private static class Node {
    final Object element;
    Node prev;
    Node next;

    Node(Object element) {
      this.element = element;
    }
  }

  private static class HashMap {
    private static class Entry {
      Object key;
      Node value;
      Entry next;

      Entry(Object key, Node value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
      }
    }

    private Entry[] buckets;
    private int size;
    private static final double MAX_LOAD_FACTOR = 0.75;

    HashMap() {
      buckets = new Entry[16];
    }

    Node get(Object key) {
      int index = getBucketIndex(key);
      for (Entry e = buckets[index]; e != null; e = e.next) {
        if (key.equals(e.key))
          return e.value;
      }
      return null;
    }

    void put(Object key, Node value) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    void remove(Object key) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    private int getBucketIndex(Object key) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    private void resize() {
      Entry[] oldBuckets = buckets;
      buckets = new Entry[oldBuckets.length * 2];
      size = 0;
      for (Entry e : oldBuckets) {
        while (e != null) {
          put(e.key, e.value);
          e = e.next;
        }
      }
    }
  }

  private final HashMap map;
  private Node head;
  private Node tail;
  private int size;

  public LinkedHashSet() {
    map = new HashMap();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(Object value) {
    if (value == null)
      throw new IllegalArgumentException("value is null");
    return map.get(value) != null;
  }

  @Override
  public void add(Object value) {
    if (value == null)
      throw new IllegalArgumentException("value is null");

    if (contains(value))
      return;

    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void remove(Object value) {
    if (value == null)
      throw new IllegalArgumentException("value is null");

    throw new UnsupportedOperationException("Not implemented yet");
  }
}
