package instructions;

/**
 * Instruction that multiplies two registers.
 */
public class Multiply implements Instruction {
    private final int register1;
    private final int register2;

    /**
     * Creates a new Multiply instruction.
     * 
     * @param register1 destination register
     * @param register2 source register
     */
    public Multiply(int register1, int register2) {
        this.register1 = register1;
        this.register2 = register2;
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
        return String.format("Multiply(%d, %d)", register1, register2);
    }
}
