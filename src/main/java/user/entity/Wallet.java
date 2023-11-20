package user.entity;

import bet.Bet;

import java.util.ArrayList;

public class Wallet {

    private float balance;

    private ArrayList<Bet> bets;

    public Wallet() {

        this.balance = 0.0F;

        this.bets = new ArrayList<>();
    }

    public float getBalance() {
        return this.balance;
    }

    public ArrayList<Bet> getBets() {
        return this.bets;
    }

    public void addFunds(float addition) {
        if (addition <= 0) {
            return;
        } else {
            this.balance += addition;
        }
    }

    // Add bet is giving an error because bet isn't implemented yet
    public void addBet(Bet bet) throws InsufficientFundsException {
        if ((this.balance -= bet.getWager()) < 0) {
            throw new InsufficientFundsException();
        }
        this.balance -= bet.getWager();
        this.bets.add(bet);
    }

    public class InsufficientFundsException extends Exception {

        public InsufficientFundsException() {
            super("Insufficient funds in the account.");
        }
    }

    public void settlement() {
        for (Bet bet : bets) {balance += bet.payout();}
    }
}

// Error msg needed when insufficient funds
