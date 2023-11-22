package schedule.entity;

import java.time.LocalDateTime;

public interface Event {

    int getId();

    String getHome();

    String getAway();

    LocalDateTime getDate();

    String getTitle();

    String getWeek();

    void setActivity(int i);

    void setHomeScore(int homeScore);

    void setAwayScore(int awayScore);

    void setHomeOdds(int homeOdds);

    void setAwayOdds(int awayOdds);

    boolean getResult();

    int getHomeOdds();
    int getAwayOdds();
}
