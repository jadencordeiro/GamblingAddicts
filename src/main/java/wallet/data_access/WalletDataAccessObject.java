package wallet.data_access;

import user.entity.User;
import wallet.entity.WalletFactory;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WalletDataAccessObject implements UserTransactionDataAccessInterface {
    private File csvFile;
    private Map<String, Wallet> wallets = new HashMap<>();

    private WalletFactory walletFactory;

    public WalletDataAccessObject(WalletFactory walletFactory) {
        this.walletFactory = walletFactory;
        this.wallets.put("t1", walletFactory.create("t1"));
    }
    @Override
    public Wallet getWallet(String name) {
        return this.wallets.get(name);
    }
}
