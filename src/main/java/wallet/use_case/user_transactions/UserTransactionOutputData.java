package wallet.use_case.user_transactions;

import java.time.LocalDateTime;
import java.util.HashMap;

public class UserTransactionOutputData {
    private final String name;
    private float amount;
    private float newBalance;
    private LocalDateTime ldt;
    private HashMap<LocalDateTime, Float> newTransHistory;
    private boolean useCaseFailed;

    public UserTransactionOutputData(String name, float amount, LocalDateTime ldt, HashMap<LocalDateTime, Float> newTransHistory, boolean useCaseFailed) {
        this.name = name;
        this.amount = amount;
        this.ldt = ldt;
        this.useCaseFailed = useCaseFailed;
        this.newTransHistory = newTransHistory;
    }

    public String getName(){
        return this.name;
    }

    public float getAmount(){
        return this.amount;
    }

    public float getNewBalance(){
        return this.newBalance;
    }

    public HashMap<LocalDateTime, Float> getNewTransHistory() {return this.newTransHistory;}

    public LocalDateTime getLdt(){
        return this.ldt;
    }
}

