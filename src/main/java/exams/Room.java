package exams;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a room that can host exams.
 * 
 * @invar | getExams() != null
 * @invar | getExams().stream().allMatch(exam -> exam != null &&
 *        exam.getRooms().contains(this))
 */
public class Room {
    private final Set<Exam> exams = new HashSet<>();

    /**
     * Initializes this room with no exams.
     * 
     * @post | getExams().isEmpty()
     */
    public Room() {
    }

    /**
     * Returns the set of exams that take place in this room.
     * 
     * @creates | result
     * @post | result != null
     * @post | result.stream().allMatch(exam -> exam.getRooms().contains(this))
     */
    public Set<Exam> getExams() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Package-private method to add an exam to this room.
     */
    void addExam(Exam exam) {
        exams.add(exam);
    }

    /**
     * Package-private method to remove an exam from this room.
     */
    void removeExam(Exam exam) {
        exams.remove(exam);
    }
}
