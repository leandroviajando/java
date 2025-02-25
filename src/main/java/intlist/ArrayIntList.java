package intlist;

import java.util.Arrays;

/**
 * Array-based implementation of IntList.
 */
class ArrayIntList implements IntList {
    private int[] elements;
    private int size;

    /**
     * Creates a new ArrayIntList containing the values from the given array.
     * 
     * @param values array to copy values from
     * @throws IllegalArgumentException if values is null
     */
    ArrayIntList(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("values array cannot be null");
        }
        this.elements = Arrays.copyOf(values, Math.max(values.length, 10));
        this.size = values.length;
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
