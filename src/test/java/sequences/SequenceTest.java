package sequences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SequenceTest {
    @Test
    void testEmptySequence() {
        Sequence empty = EmptySequence.getInstance();
        assertEquals(0, empty.length());
        assertTrue(empty.isEmpty());
        assertEquals("[]", empty.toString());
    }

    @Test
    void testEmptySingleton() {
        assertSame(EmptySequence.getInstance(), EmptySequence.getInstance());
    }

    @Test
    void testNonEmptySequence() {
        Sequence seq = new NonEmptySequence(1, EmptySequence.getInstance());
        assertEquals(1, seq.length());
        assertFalse(seq.isEmpty());
        assertEquals("[1]", seq.toString());
    }

    @Test
    void testInvalidConstruction() {
        assertThrows(IllegalArgumentException.class,
                () -> new NonEmptySequence(1, null));
    }

    @Test
    void testPrepend() {
        Sequence empty = EmptySequence.getInstance();
        Sequence seq1 = empty.prepend(3);
        Sequence seq2 = seq1.prepend(2);
        Sequence seq3 = seq2.prepend(1);

        assertEquals("[1, 2, 3]", seq3.toString());
        assertEquals(3, seq3.length());
    }

    @Test
    void testEquality() {
        Sequence empty = EmptySequence.getInstance();
        assertEquals(empty, EmptySequence.getInstance());

        Sequence seq1 = empty.prepend(3).prepend(2).prepend(1);
        Sequence seq2 = empty.prepend(3).prepend(2).prepend(1);
        Sequence seq3 = empty.prepend(3).prepend(1).prepend(2);

        assertEquals(seq1, seq2);
        assertNotEquals(seq1, seq3);
        assertNotEquals(seq1, empty);
    }

    @Test
    void testDynamicBinding() {
        // Test that length() uses dynamic binding
        Sequence empty = EmptySequence.getInstance();
        Sequence seq = empty.prepend(3).prepend(2).prepend(1);

        // Length should work regardless of reference type
        assertEquals(0, empty.length());
        assertEquals(3, seq.length());

        // Test through different reference types
        Sequence ref = seq;
        assertEquals(3, ref.length());
    }
}
