package bet.use_case;

import schedule.entity.Event;

public class BetTransactionInputData {
    final private float wager;
    final private boolean betOnHome;
    final private Event event;

    final private String name; // username
    public BetTransactionInputData(Event event, float wager, boolean betOnHome, String name){
        this.event = event;
        this.wager = wager;
        this.betOnHome = betOnHome;
        this.name = name;
    }

    Event getEvent(){return event;}
    float getWager(){return wager;}

    boolean getBetOnHome() {return betOnHome;}
    String getName() {return name;}

}
