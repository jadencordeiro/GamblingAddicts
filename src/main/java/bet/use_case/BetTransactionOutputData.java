package bet.use_case;

import schedule.entity.Event;

public class BetTransactionOutputData {
    final private Event event;
    final private float wager;
    final private boolean betOnHome;

    private boolean useCaseFailed;

    public BetTransactionOutputData(Event event, float wager, boolean betOnHome, boolean useCaseFailed){
        this.event = event;
        this.wager = wager;
        this.betOnHome = betOnHome;
        this.useCaseFailed = useCaseFailed;
    }
    public Event getEvent(){return event;}
    public float getWager(){return wager;}
    boolean getBetOnHome() {return betOnHome;}
}
