package api;
import schedule.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface SportDB {

    ArrayList<Event> getEvents(LocalDateTime date);
}
