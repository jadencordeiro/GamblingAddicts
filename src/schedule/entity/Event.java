package schedule.entity;

import java.util.Date;
import java.util.HashMap;

public class Event {

    private String home;
    private String away;
    private Date date;
    private HashMap<String, Integer> score;
    final private boolean result;

    public Event(String home, String away, Date date){
        this.home = home;
        this.away = away;
        this.date = date;
    }

}
