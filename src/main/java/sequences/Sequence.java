package sequences;

/**
 * Represents an immutable sequence of integers.
 */
public interface Sequence {
    /**
     * Returns the length of this sequence.
     * 
     * @return number of elements in the sequence
     */
    int length();

    /**
     * Returns whether this sequence is empty.
     * 
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Creates a new sequence with the given value as head and this sequence as
     * tail.
     * 
     * @param value the value to prepend
     * @return new sequence with value as head and this as tail
     */
    Sequence prepend(int value);
}
