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
        if (machine.getRegister(register) == 0) {
            machine.jump(address);
        } else {
            machine.nextInstruction();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof JumpIfZero))
            return false;
        JumpIfZero other = (JumpIfZero) obj;
        return register == other.register && address == other.address;
    }

    @Override
    public int hashCode() {
        return register * 31 + address;
    }

    @Override
    public String toString() {
        return String.format("JumpIfZero(%d, %d)", register, address);
    }
}
