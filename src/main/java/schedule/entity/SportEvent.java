package schedule.entity;

import java.time.LocalDateTime;

public class SportEvent implements Event {
    final private int id;
    final private String home;
    final private String away;
    final private LocalDateTime date;
    final private String week;
    private boolean result; // true if home is winner, false if away is winner
    private int activity = -1; // -1 game is yet to start, 0 game in progress, 1 game completed
    private int homeScore = 0;
    private int awayScore = 0;

    private int homeOdds = 0;
    private int awayOdds = 0;


    public SportEvent(int id, String home, String away, LocalDateTime date, String week){
        this.id = id;
        this.home = home;
        this.away = away;
        this.date = date;
        this.week = week;
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

    public void setActivity(int i) {
        activity = i;
    }

    public int getActivity(){
        return activity;
    }

    public String getWeek() {
        return week;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setHomeOdds(int homeOdds) {
        this.homeOdds = homeOdds;
    }

    public void setAwayOdds(int awayOdds) {
        this.awayOdds = awayOdds;
    }

    public int getHomeOdds() {
        return homeOdds;
    }

    public int getAwayOdds() {
        return awayOdds;
    }
}
