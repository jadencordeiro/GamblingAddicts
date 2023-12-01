package wallet.use_case;

public interface WalletUpdaterInterface {
    //deposit and withdraw will update the balance and transaction history
    public void depositFunds();
    public void withdrawFunds();

    //cashOut will update the balance and the bet history with value acquired from bet
    public void cashOut();

    //placing a bet will update the balance and the bet history with amount bet as a negative float value
    public void placeBet();
}
