import bet.Bet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schedule.entity.SportEvent;
import wallet.entity.Wallet;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
/*
Use this file to test Wallet.java methods and the constructor. The other tests will be completed in a seperate file,
 as they refer to use cases.
 */
class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet("t1"); // Create an empty wallet before testing.
    }

    @Test
    void testGetBalance() {
        assertEquals(0.0F, wallet.getBalance(), 0.001);
    }

    @Test
    void testGetBets() {
        assertTrue(wallet.getBets().isEmpty());
    }
}
