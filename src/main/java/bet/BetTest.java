package bet;
import org.junit.jupiter.api.Test;
import schedule.entity.SportEvent;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    SportEvent event = new SportEvent(1, "A", "B", LocalDateTime.now(), "C");
    Bet bet = new Bet(event, 50.0F, true);

    @Test
    void testGetEvent() {
        assertEquals(event, bet.getEvent());
    }

    @Test
    void testGetWager() {
        float wager = 50.0F;
        assertEquals(wager, bet.getWager(), 0.001);
    }

    @Test
    void testGetBettingSide() {
        boolean betOnHome = true;
        assertEquals(betOnHome, bet.getBettingSide());
    }

    @Test
    void testPayoutWhenBettingOnHomeAndHomeTeamWins() {
        Bet bet = new Bet(event, 50.0F, true);

        // Assume that the home team wins
        event.setResult("A");

        double expectedPayout = 50.0F * event.getHomeOdds();
        double actualPayout = bet.payout();

        assertEquals(expectedPayout, actualPayout, 0.001); // Use an appropriate delta for floating-point comparisons
    }

    @Test
    void testPayoutWhenBettingOnHomeAndAwayTeamWins() {
        Bet bet = new Bet(event, 50.0F, true);

        // Assume that the away team wins
        event.setResult("B");

        double expectedPayout = 0.0F; // Should not receive any payout when betting on the losing team
        double actualPayout = bet.payout();

        assertEquals(expectedPayout, actualPayout, 0.001);
    }

    // Similar tests can be added for scenarios like betting on away, different wager amounts, etc.
}
