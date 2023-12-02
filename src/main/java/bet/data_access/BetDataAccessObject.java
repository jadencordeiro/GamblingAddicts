package bet.data_access;

import bet.entity.BetFactory;
import bet.entity.Bet;
import bet.use_case.BetTransactionDataAccessInterface;
import schedule.entity.Event;

import java.util.HashMap;
import java.util.Map;


public class BetDataAccessObject implements BetTransactionDataAccessInterface{

    private BetFactory betFactory;

    private Map<Event, Bet> bets = new HashMap<>();
    public BetDataAccessObject(BetFactory betFactory){
        this.betFactory = betFactory;
        // add bet to bets when bet placed.
    }

    @Override
    public Bet getBet(Event event) {
        return this.bets.get(event);
    }
}
