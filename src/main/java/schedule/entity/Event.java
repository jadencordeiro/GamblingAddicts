package schedule.entity;

import java.util.Date;
import java.util.Map;

public class Event {
    final private int id;
    final private String home;
    final private String away;
    final private Date date;
    private Map<String, Integer> score;
    private boolean result;

    public Event(int id, String home, String away, Date date){
        this.id = id;
        this.home = home;
        this.away = away;
        this.date = date;
        score.put(home, 0);
        score.put(away, 0);
    }


    public int getId() {
        return id;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }

    public Date getDate() {
        return date;
    }

    public Integer getScore(boolean team) {
        if (team) { return score.get(home);}
        return score.get(away);
    }

    public void setScore(boolean team, Integer score) {
        if (team) {
            this.score.put(home, score);
        } else {
            this.score.put(away, score);
        }
    }
}
