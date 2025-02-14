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
        machine.setRegister(register, constant);
        machine.nextInstruction();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof LoadConstant))
            return false;
        LoadConstant other = (LoadConstant) obj;
        return register == other.register && constant == other.constant;
    }

    @Override
    public int hashCode() {
        return register * 31 + constant;
    }

    @Override
    public String toString() {
        return String.format("LoadConstant(%d, %d)", register, constant);
    }
}
