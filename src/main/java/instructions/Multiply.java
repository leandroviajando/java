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
        machine.setRegister(register1,
                machine.getRegister(register1) * machine.getRegister(register2));
        machine.nextInstruction();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Multiply))
            return false;
        Multiply other = (Multiply) obj;
        return register1 == other.register1 && register2 == other.register2;
    }

    @Override
    public int hashCode() {
        return register1 * 31 + register2;
    }

    @Override
    public String toString() {
        return String.format("Multiply(%d, %d)", register1, register2);
    }
}
