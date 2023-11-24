package bet;

import schedule.entity.Event;


public class Bet {

    // moved the "odds" part to Events, delete getOdds method
    // Initializing betting event, betting side, and amount.
    private Event event;

    private float wager;

    private boolean betOnHome;

    public Bet(Event event, float wager, boolean betOnHome){

        this.event = event;

        this.wager = wager;

        this.betOnHome = true;
    }

    public Event getEvent(){
        return this.event;
    }
    // Need to change value depending on W/L but later on
    public float getWager(){
        return this.wager;
    }
    // True when betting on Home, False when betting on Away
    public boolean getBettingSide() {return this.betOnHome;}

    public double payout(){
        if (event.getResult() == this.getBettingSide() == true){return this.getWager() * event.getHomeOdds();}
        else if (event.getResult() == this.getBettingSide() == false){return this.getWager() * event.getAwayOdds();}
        else return 0.0F;
        // pay if bet placed on the winning team
    }
}
