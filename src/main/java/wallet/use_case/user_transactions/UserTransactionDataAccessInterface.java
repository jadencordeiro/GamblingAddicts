package wallet.use_case.user_transactions;
import user.entity.User;
import wallet.entity.Wallet;

public interface UserTransactionDataAccessInterface {
    Wallet getWallet(String name);

    void save(User user);

    void save();
}
