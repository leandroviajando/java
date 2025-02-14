package instructions;

/**
 * Instruction that halts the machine.
 */
public class Halt implements Instruction {
    @Override
    public void execute(Machine machine) {
        machine.halt();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Halt;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Halt()";
    }
}
