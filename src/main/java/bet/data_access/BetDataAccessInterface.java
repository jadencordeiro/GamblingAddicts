package bet.data_access;

import bet.entity.Bet;

import java.util.ArrayList;

public interface BetDataAccessInterface {
    boolean existsByName(String user, String identifier);


    void save(String user, Bet bet);

    ArrayList<Bet> getBets(String user);


}
