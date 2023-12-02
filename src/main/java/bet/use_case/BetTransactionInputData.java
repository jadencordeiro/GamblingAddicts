package bet.use_case;

import schedule.entity.Event;

public class BetTransactionInputData {
    final private float wager;
    final private boolean betOnHome;
    final private Event event;
    public BetTransactionInputData(Event event, float wager, boolean betOnHome){
        this.event = event;
        this.wager = wager;
        this.betOnHome = betOnHome;
    }

    Event getEvent(){return event;}
    float getWager(){return wager;}

    boolean getBetOnHome() {return betOnHome;}

}
