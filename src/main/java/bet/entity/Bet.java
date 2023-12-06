package bet.entity;
/**
 * event: The event betting on.
 * wager: The amount of bet placed.
 * betOnHome: The betting side. True if betting on home.
 */
public class Bet {
    private String eventTitle;

    private float wager;

    private boolean betOnHome;

    private String userName;
    public Bet(String eventTitle, String userName){
        this.eventTitle = eventTitle;

        this.wager = 0.0F;

        this.betOnHome = true;

        this.userName = userName;
    }

    public String getEventTitle(){
        return this.eventTitle;
    }
    // Need to change value depending on W/L but later on
    public float getWager(){
        return this.wager;
    }

    public void setWager(float updatedWager) {this.wager = updatedWager;}
    // True when betting on Home, False when betting on Away
    public boolean getBettingSide() {return this.betOnHome;}
    public void setBettingSide(boolean Team) {this.betOnHome = Team;}

    public String getUserName(){return this.userName;}

    @Override
    public String toString() {
        String betDescription = this.userName + "bet on" + this.eventTitle;
        return betDescription;
    }

    // Static method to create a Bet object from a string representation
    public static Bet parseBet(String betString) {
        String[] parts = betString.split(" bet on ");  // Assuming some delimiter, adjust as needed
        if (parts.length == 2) {
            return new Bet(parts[0], parts[1]);
        } else {
            // Handle invalid string representation
            throw new IllegalArgumentException("Invalid bet string: " + betString);
        }
    }
    /**
     * Payout if bet placed on the winning team.
     * @return The winning amount.
     */
    public float payout(){
        if (event.getResult() == this.getBettingSide()){return this.getWager() * event.getHomeOdds();}
        else if (!(event.getResult() == this.getBettingSide())){return this.getWager() * event.getAwayOdds();}
        else return 0.0F;
    }// needs to be modified
}
