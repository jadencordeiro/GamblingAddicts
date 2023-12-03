package bet.entity;

import schedule.entity.Event;

public class BetFactory {
    public Bet create(String eventTitle) {
        return new Bet(eventTitle);
    }
}
