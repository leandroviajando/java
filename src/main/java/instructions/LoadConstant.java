package instructions;

/**
 * Instruction that loads a constant value into a register.
 */
public class LoadConstant implements Instruction {
    private final int register;
    private final int constant;

    /**
     * Creates a new LoadConstant instruction.
     * 
     * @param register register to load into
     * @param constant value to load
     */
    public LoadConstant(int register, int constant) {
        this.register = register;
        this.constant = constant;
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
        return String.format("LoadConstant(%d, %d)", register, constant);
    }
}
