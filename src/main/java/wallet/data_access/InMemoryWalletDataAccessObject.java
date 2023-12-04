package wallet.data_access;

import user.entity.User;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryWalletDataAccessObject implements UserTransactionDataAccessInterface {

    private final Map<String, Wallet> wallets = new HashMap<>();
    @Override
    public Wallet getWallet(String name) {
        return wallets.get(name);
    }

    @Override
    public void save(User user) {
        wallets.put(user.getName(), user.getWallet());
    }

    @Override
    public void save() {
    }
}
