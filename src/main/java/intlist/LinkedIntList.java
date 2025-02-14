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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void add(int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int removeLast() {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from empty list");
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        return size;
    }
}
