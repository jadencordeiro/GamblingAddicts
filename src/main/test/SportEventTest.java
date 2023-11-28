import org.junit.jupiter.api.Test;
import schedule.entity.SportEvent;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class SportEventTest {

    @Test
    void testGetters() {
        LocalDateTime date = LocalDateTime.now();
        SportEvent sportEvent = new SportEvent(1, "HomeTeam", "AwayTeam", date, "Week 1");

        assertEquals(1, sportEvent.getId());
        assertEquals("HomeTeam", sportEvent.getHome());
        assertEquals("AwayTeam", sportEvent.getAway());
        assertEquals(date, sportEvent.getDate());
        assertEquals("HomeTeam vs AwayTeam", sportEvent.getTitle());
        assertEquals("Week 1", sportEvent.getWeek());
    }

    @Test
    void testSetAndGetResult() {
        SportEvent sportEvent = new SportEvent(1, "HomeTeam", "AwayTeam", LocalDateTime.now(), "Week 1");

        sportEvent.setResult("HomeTeam");
        assertTrue(sportEvent.getResult());

        sportEvent.setResult("AwayTeam");
        assertFalse(sportEvent.getResult());
    }

    @Test
    void testSetAndGetActivity() {
        SportEvent sportEvent = new SportEvent(1, "HomeTeam", "AwayTeam", LocalDateTime.now(), "Week 1");

        sportEvent.setActivity(0);
        assertEquals(0, sportEvent.getActivity());

        sportEvent.setActivity(1);
        assertEquals(1, sportEvent.getActivity());
    }

    @Test
    void testGetScores() {
        SportEvent sportEvent = new SportEvent(1, "HomeTeam", "AwayTeam", LocalDateTime.now(), "Week 1");

        assertEquals(0, sportEvent.getHomeScore());
        assertEquals(0, sportEvent.getAwayScore());

        sportEvent.setHomeScore(2);
        sportEvent.setAwayScore(1);

        assertEquals(2, sportEvent.getHomeScore());
        assertEquals(1, sportEvent.getAwayScore());
    }

    @Test
    void testSetAndGetOdds() {
        SportEvent sportEvent = new SportEvent(1, "HomeTeam", "AwayTeam", LocalDateTime.now(), "Week 1");

        sportEvent.setHomeOdds(2);
        sportEvent.setAwayOdds(3);

        assertEquals(2, sportEvent.getHomeOdds());
        assertEquals(3, sportEvent.getAwayOdds());
    }
}

