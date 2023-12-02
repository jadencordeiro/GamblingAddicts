package bet.use_case;

import bet.entity.Bet;
import schedule.entity.Event;

public interface BetTransactionDataAccessInterface {
    public Bet getBet(Event event);
}
