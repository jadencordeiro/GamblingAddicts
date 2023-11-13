package bet;

import schedule.entity.Event;

public class Bet {
    private float odds;

    private Event event;

    private boolean result;

    private float wager;

    public Bet(Event event, float wager){

        this.odds = 0.0F;

        this.event = event;

        this.result = false;

        this.wager = wager;
    }
    // Don't know how we are changing odds
    public float getOdds(){
        return this.odds;
    }

    public Event getEvent(){
        return this.event;
    }
    // Need to change value depending on W/L but later on
    public boolean getResult(){
        return this.result;
    }

    public float getWager(){
        return this.wager;
    }
}
