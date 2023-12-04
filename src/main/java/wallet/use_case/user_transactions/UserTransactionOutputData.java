package wallet.use_case.user_transactions;

import java.time.LocalDateTime;

public class UserTransactionOutputData {
    private final String name;
    private float amount;
    private float newBalance;
    private LocalDateTime ldt;
    private boolean useCaseFailed;

    public UserTransactionOutputData(String name, float amount, float newBalance, LocalDateTime ldt, boolean useCaseFailed) {
        this.name = name;
        this.amount = amount;
        this.newBalance = newBalance;
        this.ldt = ldt;
        this.useCaseFailed = useCaseFailed;
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

    public LocalDateTime getLdt(){
        return this.ldt;
    }
}

