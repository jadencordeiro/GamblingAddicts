package wallet.data_access;

import bet.data_access.BetDataAccessInterface;
import bet.data_access.BetDataAccessObject;
import bet.entity.Bet;
import schedule.entity.Event;
import user.entity.User;
import wallet.entity.WalletFactory;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WalletDataAccessObject implements UserTransactionDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private Map<String, Wallet> wallets = new HashMap<>();

    private WalletFactory walletFactory;

    private BetDataAccessInterface betDataAccessObject;

    public WalletDataAccessObject(String csvPath, WalletFactory walletFactory, BetDataAccessInterface betDataAccessObject) throws IOException {
        this.walletFactory = walletFactory;
        csvFile = new File(csvPath);
        headers.put("user", 0);
        headers.put("balance", 1);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("user, balance");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String user = String.valueOf(col[headers.get("user")]);
                    float balance = Float.valueOf(col[headers.get("balance")]);
                    Wallet wallet = walletFactory.create(user);
                    wallet.setBalance(balance);
                    wallets.put(wallet.getWalletName(), wallet);

                    if(betDataAccessObject.getBets(user) != null) {
                        for (Bet bet : betDataAccessObject.getBets(user)) {
                            wallet.setBets(bet, bet.getWager());
                        }
                    }
                }
            }
        }
    }

    public void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Wallet wallet : wallets.values()) {
                String line = String.format("%s,%s", wallet.getWalletName(), wallet.getBalance());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Wallet getWallet(String name) {
        return this.wallets.get(name);
    }

    public void save(User user){
        wallets.put(user.getName(), user.getWallet());
        this.save();
    }
}
