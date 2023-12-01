package wallet.entity;


import bet.Bet;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * balance: The balance in the wallet.
 * transactions: The list of user completed deposits/withdrawals
 * bet history: the map linking bets to payouts/losses
 * username: User associated with wallet
 */
public class Wallet {

    private float balance;
    final private String name;
    private HashMap<Bet, Float> betHistory;
    private HashMap<LocalDateTime, Float> transactionHistory;

    public Wallet(String name) {

        this.balance = 0.0F;
        this.name = name;
        this.betHistory = new HashMap<>();
        this.transactionHistory = new HashMap<>();
    }

    public float getBalance() {
        return this.balance;
    }
    public void setBalance(float updatedBalance) {this.balance = updatedBalance;}
    public String getWalletName(){return this.name;}

    public HashMap<Bet, Float> getBets() {
        return this.betHistory;
    }

    public HashMap<LocalDateTime, Float> getTransactionHistory() {
        return this.transactionHistory;
    }
}