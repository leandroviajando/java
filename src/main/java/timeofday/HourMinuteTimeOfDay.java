package timeofday;

public class HourMinuteTimeOfDay implements TimeOfDay {
    private int hours;
    private int minutes;

    public HourMinuteTimeOfDay(int hours, int minutes) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23");
        }
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        this.hours = hours;
        this.minutes = minutes;
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getMinutesSinceMidnight() {
        return hours * 60 + minutes;
    }

    @Override
    public void setHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23");
        }
        this.hours = hours;
    }

    @Override
    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        this.minutes = minutes;
    }

    @Override
    public void setMinutesSinceMidnight(int minutesSinceMidnight) {
        if (minutesSinceMidnight < 0 || minutesSinceMidnight > 1439) {
            throw new IllegalArgumentException("Minutes since midnight must be between 0 and 1439");
        }
        this.hours = minutesSinceMidnight / 60;
        this.minutes = minutesSinceMidnight % 60;
    }
}
