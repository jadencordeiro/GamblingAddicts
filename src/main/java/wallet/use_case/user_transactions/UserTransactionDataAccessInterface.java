package wallet.use_case.user_transactions;
import user.entity.User;
import wallet.entity.Wallet;

public interface UserTransactionDataAccessInterface {
    public Wallet getWallet(String name);
}
