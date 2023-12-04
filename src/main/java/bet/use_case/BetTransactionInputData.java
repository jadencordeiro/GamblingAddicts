package bet.use_case;

public class BetTransactionInputData {
    final private float wager;
    final private boolean betOnHome;
    final private String eventTitle;

    final private String username;

    public BetTransactionInputData(String eventTitle, float wager, boolean betOnHome, String username){
        this.eventTitle = eventTitle;
        this.wager = wager;
        this.betOnHome = betOnHome;
        this.username = username;
    }

    String getEventTitle(){return eventTitle;}
    float getWager(){return wager;}

    boolean getBetOnHome() {return betOnHome;}

    String getUsername(){return username;}

}
