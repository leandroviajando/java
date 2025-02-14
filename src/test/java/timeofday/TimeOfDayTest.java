package timeofday;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeOfDayTest {

  @Test
  public void testBothImplementations() {
    timeofday.TimeOfDay[] implementations = {
        new timeofday.HourMinuteTimeOfDay(12, 30),
        new timeofday.MinutesSinceMidnightTimeOfDay(12, 30)
    };

    for (timeofday.TimeOfDay time : implementations) {
      // Test initial state
      assertEquals(12, time.getHours());
      assertEquals(30, time.getMinutes());
      assertEquals(750, time.getMinutesSinceMidnight());

      // Test setHours
      time.setHours(15);
      assertEquals(15, time.getHours());
      assertEquals(30, time.getMinutes());
      assertEquals(930, time.getMinutesSinceMidnight());

      // Test setMinutes
      time.setMinutes(45);
      assertEquals(15, time.getHours());
      assertEquals(45, time.getMinutes());
      assertEquals(945, time.getMinutesSinceMidnight());

      // Test setMinutesSinceMidnight
      time.setMinutesSinceMidnight(800);
      assertEquals(13, time.getHours());
      assertEquals(20, time.getMinutes());
      assertEquals(800, time.getMinutesSinceMidnight());
    }
  }

  @Test
  public void testInvalidInputs() {
    timeofday.TimeOfDay[] implementations = {
        new timeofday.HourMinuteTimeOfDay(0, 0),
        new timeofday.MinutesSinceMidnightTimeOfDay(0, 0)
    };

    for (timeofday.TimeOfDay time : implementations) {
      // Test invalid hours
      assertThrows(IllegalArgumentException.class, () -> time.setHours(-1));
      assertThrows(IllegalArgumentException.class, () -> time.setHours(24));

      // Test invalid minutes
      assertThrows(IllegalArgumentException.class, () -> time.setMinutes(-1));
      assertThrows(IllegalArgumentException.class, () -> time.setMinutes(60));

      // Test invalid minutes since midnight
      assertThrows(IllegalArgumentException.class, () -> time.setMinutesSinceMidnight(-1));
      assertThrows(IllegalArgumentException.class, () -> time.setMinutesSinceMidnight(1440));
    }
  }

  @Test
  public void testConstructorValidation() {
    // Test invalid constructor parameters for both implementations
    assertThrows(IllegalArgumentException.class, () -> new timeofday.HourMinuteTimeOfDay(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> new timeofday.HourMinuteTimeOfDay(24, 0));
    assertThrows(IllegalArgumentException.class, () -> new timeofday.HourMinuteTimeOfDay(0, -1));
    assertThrows(IllegalArgumentException.class, () -> new timeofday.HourMinuteTimeOfDay(0, 60));

    assertThrows(IllegalArgumentException.class, () -> new timeofday.MinutesSinceMidnightTimeOfDay(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> new timeofday.MinutesSinceMidnightTimeOfDay(24, 0));
    assertThrows(IllegalArgumentException.class, () -> new timeofday.MinutesSinceMidnightTimeOfDay(0, -1));
    assertThrows(IllegalArgumentException.class, () -> new timeofday.MinutesSinceMidnightTimeOfDay(0, 60));
  }
}
