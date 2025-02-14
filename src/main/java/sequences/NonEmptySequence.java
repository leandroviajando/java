package sequences;

/**
 * Represents a non-empty sequence of integers.
 */
public class NonEmptySequence implements Sequence {
    private final int head;
    private final Sequence tail;

    /**
     * Creates a new non-empty sequence.
     * 
     * @param head first value in the sequence
     * @param tail remaining values in the sequence
     * @throws IllegalArgumentException if tail is null
     */
    public NonEmptySequence(int head, Sequence tail) {
        if (tail == null) {
            throw new IllegalArgumentException("Tail cannot be null");
        }
        this.head = head;
        this.tail = tail;
    }

    /**
     * Returns the first value in this sequence.
     * 
     * @return head value
     */
    public int getHead() {
        return head;
    }

    /**
     * Returns the remaining values in this sequence.
     * 
     * @return tail sequence
     */
    public Sequence getTail() {
        return tail;
    }

    @Override
    public int length() {
        return 1 + tail.length();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Sequence prepend(int value) {
        return new NonEmptySequence(value, this);
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(head);

        Sequence current = tail;
        while (!current.isEmpty()) {
            NonEmptySequence nes = (NonEmptySequence) current;
            sb.append(", ");
            sb.append(nes.head);
            current = nes.tail;
        }

        sb.append("]");
        return sb.toString();
    }
}
