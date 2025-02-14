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
        machine.jump(address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Jump))
            return false;
        Jump other = (Jump) obj;
        return address == other.address;
    }

    @Override
    public int hashCode() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("Jump(%d)", address);
    }
}
