package schedule.service.refresh;

import schedule.entity.Event;

import java.util.Map;

public interface RefreshScheduleDataAccessInterface {

    boolean existsByName(String identifier);

    void save(Event event);

    Map<String, Event> getEvents();
}
