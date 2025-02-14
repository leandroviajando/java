package instructions;

/**
 * Instruction that jumps if a register contains zero.
 */
public class JumpIfZero implements Instruction {
    private final int register;
    private final int address;

    /**
     * Creates a new JumpIfZero instruction.
     * 
     * @param register register to check
     * @param address  address to jump to
     */
    public JumpIfZero(int register, int address) {
        this.register = register;
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
        return String.format("JumpIfZero(%d, %d)", register, address);
    }
}
