package wallet.interface_adapter.user_transaction_adapters;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserTransState {

    private String user = "";
    private float balance = 0.0f;
    private String walletError = null;

    public Map<LocalDateTime, Float> transactions = new HashMap<>();

    public UserTransState(UserTransState copy){
        user = copy.user;
    }

    public UserTransState() {}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUsername(String username) {
        user = username;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance){
        this.balance = balance;
    }

    public String getWalletError() {
        return walletError;
    }

    public void setWalletError(String walletError) {
        this.walletError = walletError;
    }

    public String[][] getData() {
        String[][] array = new String[transactions.size()][2];
        int i = 0;
        for (Map.Entry<LocalDateTime, Float> trans: transactions.entrySet()) {
            String[] a = {trans.getKey().toString(), trans.getValue().toString()};
            array[i] = a;
            i++;
        }
        return array;

    }
}
