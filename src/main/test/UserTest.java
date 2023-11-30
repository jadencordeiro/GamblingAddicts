import org.junit.jupiter.api.Test;
import user.entity.User;
import user.entity.Wallet;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGetters() {
        User user = new User("Charlie", "password123");

        assertEquals("Charlie", user.getName());
        assertEquals("password123", user.getPassword());
        assertNotNull(user.getWallet());
        assertTrue(user.getWallet() instanceof Wallet);
    }
}

