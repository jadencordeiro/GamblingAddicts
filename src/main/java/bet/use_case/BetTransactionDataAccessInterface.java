package bet.use_case;

import bet.entity.Bet;
public interface BetTransactionDataAccessInterface {

    boolean existsByName(String user, String identifier);
    void save(String user, Bet bet);
    Bet get(String user, String eventTitle);
}
