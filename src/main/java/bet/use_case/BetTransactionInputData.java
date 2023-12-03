package bet.use_case;

public class BetTransactionInputData {
    final private float wager;
    final private boolean betOnHome;
    final private String eventTitle;

    public BetTransactionInputData(String eventTitle, float wager, boolean betOnHome){
        this.eventTitle = eventTitle;
        this.wager = wager;
        this.betOnHome = betOnHome;
    }

    String getEventTitle(){return eventTitle;}
    float getWager(){return wager;}

    boolean getBetOnHome() {return betOnHome;}

}
