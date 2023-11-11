package schedule.service.refresh;

import schedule.entity.Event;

public interface RefreshScheduleDataAccessInterface {

    boolean existsByName(String identifier);

    void save(Event event);

}
