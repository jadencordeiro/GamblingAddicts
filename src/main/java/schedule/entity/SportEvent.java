package schedule.entity;

import java.time.LocalDateTime;

public class SportEvent implements Event {
    final private int id;
    final private String home;
    final private String away;
    final private LocalDateTime date;
    private boolean result;

    public SportEvent(int id, String home, String away, LocalDateTime date){
        this.id = id;
        this.home = home;
        this.away = away;
        this.date = date;
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

    public String getTitle() {
        return home + " vs " + away;
    }

    public void setResult(String winner){
        result = home.equals(winner);
    }

    public boolean getResult() {
        return result;
    }

    // calculated using API
    public float calculateOdds(){return 0.0F;}
}
