package bet.use_case;

import bet.entity.Bet;
import schedule.entity.Event;

public interface BetTransactionDataAccessInterface {

    boolean existsByName(String identifier);
    public void save(Bet bet);
    Bet get(String eventTitle);
}
