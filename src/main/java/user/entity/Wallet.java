package user.entity;

import bet.Bet;

import java.util.ArrayList;

/**
 * @param: balance The balance in the wallet.
 * @param: bets The list of bets inside this wallet.
 */
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
    // shall we add a precondition that addition >= 0 here, so we could omit the "if" part?

    /**
     * @param bet Takes a Bet for input.
     * @see: java.bet.Bet
     * @throws InsufficientFundsException throw Exception when the fund is not enough for the bet place.
     */
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


    /**
     * payout each individual bet inside the bets list in this wallet.
     */
    public void settlement() {
        for (Bet bet : bets) {balance += bet.payout();}
    }
    // payout all bets in the Bet array.
}

