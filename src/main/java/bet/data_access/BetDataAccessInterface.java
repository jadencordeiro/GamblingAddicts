package bet.data_access;

import bet.entity.Bet;
import schedule.entity.Event;
import user.entity.User;

public interface BetDataAccessInterface {
    boolean existsByName(String identifier);


    void save(Bet bet);


}
