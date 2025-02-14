package intlist;

/**
 * Linked list-based implementation of IntList.
 */
class LinkedIntList implements IntList {
    private static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /**
     * Creates a new LinkedIntList containing the values from the given array.
     * 
     * @param values array to copy values from
     * @throws IllegalArgumentException if values is null
     */
    LinkedIntList(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("values array cannot be null");
        }

        for (int value : values) {
            add(value);
        }
    }

    @Override
    public int[] toArray() {
        int[] result = new int[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value, null);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public int removeLast() {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from empty list");
        }

        int value = tail.value;

        if (size == 1) {
            head = tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }

        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }
}
