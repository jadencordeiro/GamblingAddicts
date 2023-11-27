package bet;

import schedule.entity.Event;

/**
 * event: The event betting on.
 * wager: The amount of bet placed.
 * betOnHome: The betting side. True if betting on home.
 */
public class Bet {
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

    /**
     * Payout if bet placed on the winning team.
     * @return The winning amount.
     */
    public float payout(){
        if (event.getResult() == this.getBettingSide()){return this.getWager() * event.getHomeOdds();}
        else if (!(event.getResult() == this.getBettingSide())){return this.getWager() * event.getAwayOdds();}
        else return 0.0F;
    }
}
