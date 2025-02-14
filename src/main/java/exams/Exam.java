package exams;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an exam that can take place in multiple rooms.
 * 
 * @invar | getRooms() != null
 * @invar | getRooms().stream().allMatch(room -> room != null &&
 *        room.getExams().contains(this))
 */
public class Exam {
    private final Set<Room> rooms = new HashSet<>();

    /**
     * Initializes this exam with no assigned rooms.
     * 
     * @post | getRooms().isEmpty()
     */
    public Exam() {
    }

    /**
     * Returns the set of rooms where this exam takes place.
     * 
     * @creates | result
     * @post | result != null
     * @post | result.stream().allMatch(room -> room.getExams().contains(this))
     */
    public Set<Room> getRooms() {
        return Set.copyOf(rooms);
    }

    /**
     * Schedules this exam to take place in the given room.
     * 
     * @throws IllegalArgumentException if room is null
     * @mutates | this, room
     * @post | getRooms().contains(room)
     * @post | room.getExams().contains(this)
     * @post | getRooms().stream().allMatch(r -> r == room ||
     *       old(getRooms()).contains(r))
     * @post | room.getExams().stream().allMatch(e -> e == this ||
     *       old(room.getExams()).contains(e))
     */
    public void scheduleIn(Room room) {
        if (room == null)
            throw new IllegalArgumentException("room is null");

        rooms.add(room);
        room.addExam(this);
    }

    /**
     * Removes this exam from the given room.
     * 
     * @throws IllegalArgumentException if room is null
     * @throws IllegalArgumentException if this exam is not scheduled in the given
     *                                  room
     * @mutates | this, room
     * @post | !getRooms().contains(room)
     * @post | !room.getExams().contains(this)
     * @post | getRooms().stream().allMatch(r -> old(getRooms()).contains(r))
     * @post | room.getExams().stream().allMatch(e ->
     *       old(room.getExams()).contains(e))
     */
    public void removeFrom(Room room) {
        if (room == null)
            throw new IllegalArgumentException("room is null");
        if (!rooms.contains(room))
            throw new IllegalArgumentException("Exam is not scheduled in this room");

        rooms.remove(room);
        room.removeExam(this);
    }
}
