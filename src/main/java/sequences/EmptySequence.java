package sequences;

/**
 * Represents an empty sequence of integers.
 */
public class EmptySequence implements Sequence {
    private static final EmptySequence INSTANCE = new EmptySequence();

    private EmptySequence() {
    } // Prevent instantiation

    /**
     * Returns the singleton instance of EmptySequence.
     * 
     * @return the empty sequence
     */
    public static EmptySequence getInstance() {
        return INSTANCE;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Sequence prepend(int value) {
        return new NonEmptySequence(value, this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EmptySequence;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "[]";
    }
}
