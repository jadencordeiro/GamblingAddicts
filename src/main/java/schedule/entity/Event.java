package schedule.entity;

import java.time.LocalDateTime;

public interface Event extends Comparable<Event> {

    int getId();

    String getHome();

    String getAway();

    LocalDateTime getDate();

    String getTitle();

    String getWeek();

    void setActivity(int i);

    int getActivity();
    void setHomeScore(int homeScore);

    int getHomeScore();

    void setAwayScore(int awayScore);

    int getAwayScore();

    void setHomeOdds(float homeOdds);

    void setAwayOdds(float awayOdds);

    float getHomeOdds();
    float getAwayOdds();
}
