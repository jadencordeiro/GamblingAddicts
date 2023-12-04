package schedule.data_access;

import schedule.entity.Event;
import schedule.service.refresh.RefreshScheduleDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryScheduleDataAccessObject implements RefreshScheduleDataAccessInterface {
    private final Map<String, Event> events = new HashMap<>();

    @Override
    public boolean existsByName(String identifier) {
        return events.containsKey(identifier);
    }

    @Override
    public void save(Event event) {
        events.put(event.getTitle(), event);
    }

    @Override
    public Map<String, Event> getEvents() {
        return events;
    }

    @Override
    public void delete() {
        events.clear();
    }
}
