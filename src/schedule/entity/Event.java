package schedule.entity;

import java.util.Date;
import java.util.HashMap;

public class Event {

    final private String home;
    final private String away;
    final private Date date;
    private HashMap<String, Integer> score;
    private boolean result;

    public Event(String home, String away, Date date){
        this.home = home;
        this.away = away;
        this.date = date;
    }

    public HashMap<String, Integer> getScore() {
        return score;
    }

    public void setScore(HashMap<String, Integer> score) {
        this.score = score;
    }
}
