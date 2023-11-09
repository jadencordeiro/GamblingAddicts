package api;
import schedule.entity.Event;

import java.util.ArrayList;
import java.util.Date;

public interface SportDB {

    ArrayList<Event> getEvents(Date date);




}
