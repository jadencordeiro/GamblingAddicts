package user.entity;

import bet.Bet;

import java.util.ArrayList;

public class Wallet {

    private float balance;

    private ArrayList<Object> bets;

    public Wallet(){

        this.balance = 0.0F;

        this.bets = new ArrayList<>();
    }

    public float getBalance(){
        return this.balance;
    }

    public ArrayList<Object> getBets(){
        return this.bets;
    }

    public void addFunds(float addition){
        if (addition <= 0){
            return;
        }else {
            this.balance += addition;
        }
    }
// Add bet is giving an error because bet isn't implemented yet
    public void addBet(Bet bet){
        this.bets.add(bet);
    }
}
