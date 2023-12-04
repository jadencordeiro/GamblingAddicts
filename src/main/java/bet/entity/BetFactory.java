package bet.entity;
public class BetFactory {
    public Bet create(String eventTitle, String userName) {
        return new Bet(eventTitle, userName);
    }
}
