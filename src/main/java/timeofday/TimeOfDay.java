package timeofday;

public interface TimeOfDay {
    /**
     * Get the hours component of the time (0-23)
     */
    int getHours();

    /**
     * Get the minutes component of the time (0-59)
     */
    int getMinutes();

    /**
     * Get the total minutes since midnight (0-1439)
     */
    int getMinutesSinceMidnight();

    /**
     * Set the hours component (0-23)
     * 
     * @throws IllegalArgumentException if hours is not between 0 and 23
     */
    void setHours(int hours);

    /**
     * Set the minutes component (0-59)
     * 
     * @throws IllegalArgumentException if minutes is not between 0 and 59
     */
    void setMinutes(int minutes);

    /**
     * Set the time using minutes since midnight (0-1439)
     * 
     * @throws IllegalArgumentException if minutesSinceMidnight is not between 0 and
     *                                  1439
     */
    void setMinutesSinceMidnight(int minutesSinceMidnight);
}
