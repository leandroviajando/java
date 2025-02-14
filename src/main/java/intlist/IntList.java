package intlist;

/**
 * An abstraction for storing a sequence of integer values.
 * Supports adding values to the end and removing values from the end.
 */
public interface IntList {
    /**
     * Creates a new IntList containing the values from the given array.
     * 
     * @param values array of integers to initialize the list with
     * @return a new IntList containing the values
     * @throws IllegalArgumentException if values is null
     */
    static IntList from(int[] values) {
        return new ArrayIntList(values);
    }

    /**
     * Returns an array containing all values in this list in sequence.
     * 
     * @return a new array containing all values in this list
     */
    int[] toArray();

    /**
     * Adds the given value to the end of this list.
     * 
     * @param value the integer to add
     */
    void add(int value);

    /**
     * Removes and returns the last value in this list.
     * 
     * @return the last value that was in the list
     * @throws IllegalStateException if the list is empty
     */
    int removeLast();

    /**
     * Returns the number of values in this list.
     * 
     * @return the size of the list
     */
    int size();
}
