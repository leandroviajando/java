package instructions;

/**
 * Instruction that decrements a register's value by 1.
 */
public class Decrement implements Instruction {
    private final int register;

    /**
     * Creates a new Decrement instruction.
     * 
     * @param register register to decrement
     */
    public Decrement(int register) {
        this.register = register;
    }

    @Override
    public void execute(Machine machine) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int hashCode() {
        return register;
    }

    @Override
    public String toString() {
        return String.format("Decrement(%d)", register);
    }
}
