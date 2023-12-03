package bet.use_case;

public class BetTransactionOutputData {
    final private String eventTitle;
    final private float wager;
    final private boolean betOnHome;

    private boolean useCaseFailed;

    public BetTransactionOutputData(String eventTitle, float wager, boolean betOnHome, boolean useCaseFailed){
        this.eventTitle = eventTitle;
        this.wager = wager;
        this.betOnHome = betOnHome;
        this.useCaseFailed = useCaseFailed;
    }
    public String getEventTitle(){return eventTitle;}
    public float getWager(){return wager;}
    boolean getBetOnHome() {return betOnHome;}
}
