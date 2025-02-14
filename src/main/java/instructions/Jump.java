package instructions;

/**
 * Instruction that jumps to a specific address.
 */
public class Jump implements Instruction {
    private final int address;

    /**
     * Creates a new Jump instruction.
     * 
     * @param address address to jump to
     */
    public Jump(int address) {
        this.address = address;
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String toString() {
        return String.format("Jump(%d)", address);
    }
}
