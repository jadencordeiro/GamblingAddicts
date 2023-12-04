package wallet.interface_adapter.user_transaction_adapters;

public class UserTransState {

    private String user = "";
    private float balance = 0.0f;
    private String walletError = null;

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
}

