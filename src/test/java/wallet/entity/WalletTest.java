package wallet.entity;

import bet.Bet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schedule.entity.SportEvent;
import user.entity.Wallet;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet(); // Create an empty wallet before testing.
    }

    @Test
    void testGetBalance() {
        assertEquals(0.0F, wallet.getBalance(), 0.001);
    }

    @Test
    void testGetBets() {
        assertTrue(wallet.getBets().isEmpty());
    }

    @Test
    void testAddFunds() {
        wallet.addFunds(50.0F);

        assertEquals(50.0F, wallet.getBalance(), 0.001);
    }

    @Test
    void testAddFundsNegativeAmount() {
        wallet.addFunds(-20.0F);

        assertEquals(0.0F, wallet.getBalance(), 0.001);
    }


    SportEvent event = new SportEvent(1, "A", "B", LocalDateTime.now(), "C");
    Bet bet = new Bet(event, 50.0F, true);
    Bet bet2 = new Bet(event, 50.0F, false);

    @Test
    void testAddBetSufficientFunds() throws Wallet.InsufficientFundsException {
        wallet.addFunds(100.0F);

        wallet.addBet(bet);

        assertEquals(100.0F - bet.getWager(), wallet.getBalance(), 0.001);
        assertEquals(1, wallet.getBets().size());
    }

    @Test
    void testAddBetInsufficientFunds() {
        assertThrows(Wallet.InsufficientFundsException.class, () -> {
            wallet.addBet(bet);
        });

        assertEquals(0.0F, wallet.getBalance(), 0.001);
        assertTrue(wallet.getBets().isEmpty());
    }

    @Test
    void testSettlement() {
        wallet.addFunds(100.0F);
        wallet.getBets().add(bet);
        wallet.getBets().add(bet2);
        assertEquals(0.0F, wallet.getBalance(), 0.001);

        wallet.settlement();

        assertEquals( bet.payout() + bet2.payout(), wallet.getBalance(), 0.001);
    }

}

