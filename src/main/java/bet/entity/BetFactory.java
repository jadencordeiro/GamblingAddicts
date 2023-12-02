package bet.entity;

import schedule.entity.Event;

public class BetFactory {
    public Bet create(Event event) {
        return new Bet(event);
    }
}
