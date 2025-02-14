package instructions;

/**
 * Represents a machine instruction that can be executed.
 */
public interface Instruction {
    /**
     * Executes this instruction on the given machine.
     * 
     * @param machine the machine to execute the instruction on
     */
    void execute(Machine machine);
}
