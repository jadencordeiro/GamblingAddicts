package bet.entity;
public class BetFactory {
    public Bet create(String eventTitle) {
        return new Bet(eventTitle);
    }
}
