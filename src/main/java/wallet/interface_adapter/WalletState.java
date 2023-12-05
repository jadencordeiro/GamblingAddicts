package wallet.interface_adapter;

import bet.Bet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WalletState {

    private Map<LocalDateTime, Float> transactions = new HashMap<>();
    private Map<Bet, Float> bets = new HashMap<>();

    public WalletState(WalletState copy){
        transactions = copy.transactions;
        bets = copy.bets;
    }

    public WalletState(){
    }

    public void setTransactions(Map<LocalDateTime, Float> transactions){
        this.transactions = transactions;
    }

    public void setBets(Map<Bet, Float> bets) {
        this.bets = bets;
    }

    public String[][] getBetData() {
        String[][] array = new String[bets.size()][2];
        int i = 0;
        for(Map.Entry<Bet, Float> entry: bets.entrySet()) {
            String[] a = {entry.getKey().getEvent().getTitle(), entry.getValue().toString()};
            array[i] = a;
            i++;
        }
        return array;
    }

    public String[][] getTransactionData() {
        String[][] array = new String[transactions.size()][2];
        int i = 0;
        for(Map.Entry<LocalDateTime, Float> entry: transactions.entrySet()) {
            String[] a = {entry.getKey().toString(), entry.getValue().toString()};
            array[i] = a;
            i++;
        }
        return array;
    }

}
