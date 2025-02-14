package timeofday;

public class MinutesSinceMidnightTimeOfDay implements TimeOfDay {
    private int minutesSinceMidnight;

    public MinutesSinceMidnightTimeOfDay(int hours, int minutes) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23");
        }
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        this.minutesSinceMidnight = hours * 60 + minutes;
    }

    @Override
    public int getHours() {
        return minutesSinceMidnight / 60;
    }

    @Override
    public int getMinutes() {
        return minutesSinceMidnight % 60;
    }

    @Override
    public int getMinutesSinceMidnight() {
        return minutesSinceMidnight;
    }

    @Override
    public void setHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23");
        }
        this.minutesSinceMidnight = hours * 60 + getMinutes();
    }

    @Override
    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        this.minutesSinceMidnight = getHours() * 60 + minutes;
    }

    @Override
    public void setMinutesSinceMidnight(int minutesSinceMidnight) {
        if (minutesSinceMidnight < 0 || minutesSinceMidnight > 1439) {
            throw new IllegalArgumentException("Minutes since midnight must be between 0 and 1439");
        }
        this.minutesSinceMidnight = minutesSinceMidnight;
    }
}
