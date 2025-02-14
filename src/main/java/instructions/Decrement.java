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
        machine.setRegister(register, machine.getRegister(register) - 1);
        machine.nextInstruction();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Decrement))
            return false;
        Decrement other = (Decrement) obj;
        return register == other.register;
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
