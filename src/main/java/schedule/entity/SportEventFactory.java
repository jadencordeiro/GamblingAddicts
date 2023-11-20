package schedule.entity;

import java.time.LocalDateTime;

public class SportEventFactory implements EventFactory{
    @Override
    public Event create(int id, String home, String away, LocalDateTime date, String week) {
        return new SportEvent(id, home, away, date, week);
    }
}
