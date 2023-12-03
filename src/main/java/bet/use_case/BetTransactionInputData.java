package bet.use_case;

import schedule.entity.Event;

public class BetTransactionInputData {
    final private float wager;
    final private boolean betOnHome;
    final private String eventTitle;

    final private String name; // username
    public BetTransactionInputData(String eventTitle, float wager, boolean betOnHome, String name){
        this.eventTitle = eventTitle;
        this.wager = wager;
        this.betOnHome = betOnHome;
        this.name = name;
    }

    String getEventTitle(){return eventTitle;}
    float getWager(){return wager;}

    boolean getBetOnHome() {return betOnHome;}
    String getName() {return name;}

}
