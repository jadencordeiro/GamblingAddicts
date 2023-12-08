package wallet.use_case.user_transactions;
import user.entity.User;
import wallet.entity.Wallet;

import java.util.Map;

public interface UserTransactionDataAccessInterface {
    public Map<String, Wallet> loadWallets();

    void saveWallet(Wallet wallet);
}
