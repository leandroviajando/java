package exams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class ExamsTest {
    @Test
    void testNewRoomAndExam() {
        Room room = new Room();
        Exam exam = new Exam();

        assertTrue(room.getExams().isEmpty());
        assertTrue(exam.getRooms().isEmpty());
    }

    @Test
    void testScheduleExam() {
        Room room = new Room();
        Exam exam = new Exam();

        exam.scheduleIn(room);

        assertTrue(room.getExams().contains(exam));
        assertTrue(exam.getRooms().contains(room));
        assertEquals(1, room.getExams().size());
        assertEquals(1, exam.getRooms().size());
    }

    @Test
    void testRemoveExam() {
        Room room = new Room();
        Exam exam = new Exam();

        exam.scheduleIn(room);
        exam.removeFrom(room);

        assertFalse(room.getExams().contains(exam));
        assertFalse(exam.getRooms().contains(room));
        assertTrue(room.getExams().isEmpty());
        assertTrue(exam.getRooms().isEmpty());
    }

    @Test
    void testMultipleRooms() {
        Exam exam = new Exam();
        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();

        exam.scheduleIn(room1);
        exam.scheduleIn(room2);
        exam.scheduleIn(room3);

        assertEquals(3, exam.getRooms().size());
        assertTrue(exam.getRooms().contains(room1));
        assertTrue(exam.getRooms().contains(room2));
        assertTrue(exam.getRooms().contains(room3));
        assertEquals(1, room1.getExams().size());
        assertEquals(1, room2.getExams().size());
        assertEquals(1, room3.getExams().size());
    }

    @Test
    void testMultipleExams() {
        Room room = new Room();
        Exam exam1 = new Exam();
        Exam exam2 = new Exam();
        Exam exam3 = new Exam();

        exam1.scheduleIn(room);
        exam2.scheduleIn(room);
        exam3.scheduleIn(room);

        assertEquals(3, room.getExams().size());
        assertTrue(room.getExams().contains(exam1));
        assertTrue(room.getExams().contains(exam2));
        assertTrue(room.getExams().contains(exam3));
        assertEquals(1, exam1.getRooms().size());
        assertEquals(1, exam2.getRooms().size());
        assertEquals(1, exam3.getRooms().size());
    }

    @Test
    void testCannotScheduleInNull() {
        Exam exam = new Exam();
        assertThrows(IllegalArgumentException.class, () -> exam.scheduleIn(null));
    }

    @Test
    void testCannotRemoveFromNull() {
        Exam exam = new Exam();
        assertThrows(IllegalArgumentException.class, () -> exam.removeFrom(null));
    }

    @Test
    void testCannotRemoveFromUnscheduled() {
        Room room = new Room();
        Exam exam = new Exam();
        assertThrows(IllegalArgumentException.class, () -> exam.removeFrom(room));
    }

    @Test
    void testSetsAreImmutable() {
        Room room = new Room();
        Exam exam = new Exam();
        exam.scheduleIn(room);

        Set<Exam> exams = room.getExams();
        Set<Room> rooms = exam.getRooms();

        assertThrows(UnsupportedOperationException.class, () -> exams.add(new Exam()));
        assertThrows(UnsupportedOperationException.class, () -> exams.remove(exam));
        assertThrows(UnsupportedOperationException.class, () -> rooms.add(new Room()));
        assertThrows(UnsupportedOperationException.class, () -> rooms.remove(room));
    }

    @Test
    void testComplexSchedule() {
        Room room1 = new Room();
        Room room2 = new Room();
        Exam exam1 = new Exam();
        Exam exam2 = new Exam();

        // Schedule exam1 in both rooms, exam2 in room2
        exam1.scheduleIn(room1);
        exam1.scheduleIn(room2);
        exam2.scheduleIn(room2);

        // Test room1
        assertTrue(room1.getExams().contains(exam1));
        assertFalse(room1.getExams().contains(exam2));
        assertEquals(1, room1.getExams().size());

        // Test room2
        assertTrue(room2.getExams().contains(exam1));
        assertTrue(room2.getExams().contains(exam2));
        assertEquals(2, room2.getExams().size());

        // Test exam1
        assertTrue(exam1.getRooms().contains(room1));
        assertTrue(exam1.getRooms().contains(room2));
        assertEquals(2, exam1.getRooms().size());

        // Test exam2
        assertFalse(exam2.getRooms().contains(room1));
        assertTrue(exam2.getRooms().contains(room2));
        assertEquals(1, exam2.getRooms().size());

        // Remove exam1 from room2 and verify
        exam1.removeFrom(room2);
        assertFalse(room2.getExams().contains(exam1));
        assertTrue(room2.getExams().contains(exam2));
        assertEquals(1, room2.getExams().size());
        assertFalse(exam1.getRooms().contains(room2));
        assertEquals(1, exam1.getRooms().size());
    }
}
