package schedule.entity;

import java.time.LocalDateTime;
import java.util.Map;

public class SportEvent implements Event {
    final private int id;
    final private String home;
    final private String away;
    final private LocalDateTime date;
    private Map<String, Integer> score;
    private boolean result;

    public SportEvent(int id, String home, String away, LocalDateTime date){
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

    public LocalDateTime getDate() {
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

    public String getTitle() {
        return home + " vs " + away;
    }
}
