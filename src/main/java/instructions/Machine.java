package instructions;

/**
 * Represents a simple machine with registers and a program counter.
 */
public class Machine {
    private final int[] registers;
    private final Instruction[] instructions;
    private int programCounter;
    private boolean halted;

    /**
     * Creates a new machine with the given registers and instructions.
     * 
     * @param registers    array of registers to use
     * @param instructions array of instructions to execute
     * @throws IllegalArgumentException if either array is null
     */
    public Machine(int[] registers, Instruction[] instructions) {
        if (registers == null || instructions == null) {
            throw new IllegalArgumentException("Registers and instructions cannot be null");
        }
        this.registers = registers;
        this.instructions = instructions;
        this.programCounter = 0;
        this.halted = false;
    }

    /**
     * Returns the value of the register at the given index.
     * 
     * @param index register index
     * @return value in the register
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public int getRegister(int index) {
        return registers[index];
    }

    /**
     * Sets the value of the register at the given index.
     * 
     * @param index register index
     * @param value value to set
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void setRegister(int index, int value) {
        registers[index] = value;
    }

    /**
     * Sets the program counter to the given address.
     * 
     * @param address address to jump to
     * @throws IllegalArgumentException if address is negative or beyond program
     *                                  length
     */
    public void jump(int address) {
        if (address < 0 || address >= instructions.length) {
            throw new IllegalArgumentException("Invalid jump address");
        }
        programCounter = address;
    }

    /**
     * Advances the program counter to the next instruction.
     */
    public void nextInstruction() {
        programCounter++;
    }

    /**
     * Halts the machine.
     */
    public void halt() {
        halted = true;
    }

    /**
     * Returns whether the machine is halted.
     * 
     * @return true if halted, false otherwise
     */
    public boolean isHalted() {
        return halted;
    }

    /**
     * Returns the current program counter value.
     * 
     * @return current instruction address
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * Executes the program on this machine.
     */
    public void execute() {
        while (!halted && programCounter < instructions.length) {
            instructions[programCounter].execute(this);
        }
    }
}
