package bet.use_case;

import bet.entity.Bet;
public interface BetTransactionDataAccessInterface {

    boolean existsByName(String identifier);
    void save(Bet bet);
    Bet get(String eventTitle);
}
