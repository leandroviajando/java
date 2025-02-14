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
      int index = getBucketIndex(key);
      for (Entry e = buckets[index]; e != null; e = e.next) {
        if (key.equals(e.key)) {
          e.value = value;
          return;
        }
      }
      buckets[index] = new Entry(key, value, buckets[index]);
      size++;
      if (size > buckets.length * MAX_LOAD_FACTOR)
        resize();
    }

    void remove(Object key) {
      int index = getBucketIndex(key);
      if (buckets[index] == null)
        return;
      if (key.equals(buckets[index].key)) {
        buckets[index] = buckets[index].next;
        size--;
        return;
      }
      for (Entry e = buckets[index]; e.next != null; e = e.next) {
        if (key.equals(e.next.key)) {
          e.next = e.next.next;
          size--;
          return;
        }
      }
    }

    private int getBucketIndex(Object key) {
      return Math.abs(key.hashCode()) % buckets.length;
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
    Object[] result = new Object[size];
    int i = 0;
    for (Node current = head; current != null; current = current.next)
      result[i++] = current.element;
    return result;
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

    Node node = new Node(value);
    if (head == null) {
      head = tail = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    map.put(value, node);
    size++;
  }

  @Override
  public void remove(Object value) {
    if (value == null)
      throw new IllegalArgumentException("value is null");

    Node node = map.get(value);
    if (node == null)
      return;

    if (node.prev != null)
      node.prev.next = node.next;
    else
      head = node.next;

    if (node.next != null)
      node.next.prev = node.prev;
    else
      tail = node.prev;

    map.remove(value);
    size--;
  }
}
