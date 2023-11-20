package schedule.entity;

import java.time.LocalDateTime;

public interface Event {

    int getId();

    String getHome();

    String getAway();

    LocalDateTime getDate();

    String getTitle();

    boolean getResult();

    float calculateOdds();
}
