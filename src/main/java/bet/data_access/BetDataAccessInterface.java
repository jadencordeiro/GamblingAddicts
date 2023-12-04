package bet.data_access;

import bet.entity.Bet;

public interface BetDataAccessInterface {
    boolean existsByName(String identifier);


    void save(Bet bet);


}
