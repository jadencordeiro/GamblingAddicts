package schedule.entity;

import java.time.LocalDateTime;

public interface EventFactory {

    Event create(int id, String home, String away, LocalDateTime date, String week);
}
