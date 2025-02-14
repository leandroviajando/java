package instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MachineTest {
    @Test
    void testPowerProgram() {
        // Program to compute x^y
        Instruction[] program = {
                new LoadConstant(2, 1), // result = 1
                new JumpIfZero(1, 5), // if y == 0 goto halt
                new Multiply(2, 0), // result *= x
                new Decrement(1), // y--
                new Jump(1), // goto check
                new Halt() // halt
        };

        // Test 2^3 = 8
        int[] registers = { 2, 3, 0 }; // x=2, y=3, result=0
        Machine machine = new Machine(registers, program);
        machine.execute();
        assertEquals(8, registers[2]);

        // Test 3^2 = 9
        registers = new int[] { 3, 2, 0 }; // x=3, y=2, result=0
        machine = new Machine(registers, program);
        machine.execute();
        assertEquals(9, registers[2]);

        // Test x^0 = 1
        registers = new int[] { 4, 0, 0 }; // x=4, y=0, result=0
        machine = new Machine(registers, program);
        machine.execute();
        assertEquals(1, registers[2]);
    }

    @Test
    void testInvalidJump() {
        Instruction[] program = {
                new Jump(-1) // Invalid jump address
        };
        Machine machine = new Machine(new int[1], program);
        assertThrows(IllegalArgumentException.class, () -> machine.execute());
    }

    @Test
    void testInvalidRegister() {
        Instruction[] program = {
                new LoadConstant(1, 42) // Register 1 doesn't exist
        };
        Machine machine = new Machine(new int[1], program);
        assertThrows(IndexOutOfBoundsException.class, () -> machine.execute());
    }

    @Test
    void testEquality() {
        assertEquals(new LoadConstant(1, 42), new LoadConstant(1, 42));
        assertNotEquals(new LoadConstant(1, 42), new LoadConstant(1, 43));
        assertEquals(new Decrement(1), new Decrement(1));
        assertNotEquals(new Decrement(1), new Decrement(2));
        assertEquals(new Multiply(1, 2), new Multiply(1, 2));
        assertNotEquals(new Multiply(1, 2), new Multiply(2, 1));
        assertEquals(new JumpIfZero(1, 5), new JumpIfZero(1, 5));
        assertNotEquals(new JumpIfZero(1, 5), new JumpIfZero(1, 6));
        assertEquals(new Jump(5), new Jump(5));
        assertNotEquals(new Jump(5), new Jump(6));
        assertEquals(new Halt(), new Halt());
    }

    @Test
    void testToString() {
        assertEquals("LoadConstant(1, 42)", new LoadConstant(1, 42).toString());
        assertEquals("Decrement(1)", new Decrement(1).toString());
        assertEquals("Multiply(1, 2)", new Multiply(1, 2).toString());
        assertEquals("JumpIfZero(1, 5)", new JumpIfZero(1, 5).toString());
        assertEquals("Jump(5)", new Jump(5).toString());
        assertEquals("Halt()", new Halt().toString());
    }
}
