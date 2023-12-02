package wallet.use_case.user_transactions;

import java.time.LocalDateTime;

public class UserTransactionInputData {
    private final String name;
    private float amount;
    private LocalDateTime ldt;

    private boolean isDeposit;

    public UserTransactionInputData(String name, float amount, LocalDateTime ldt, boolean isDeposit) {
        this.name = name;
        this.amount = amount;
        this.ldt = ldt;
        this.isDeposit = isDeposit;
    }

    public String getName() {
        return this.name;
    }

    public float getAmount(){
        return this.amount;
    }

    public LocalDateTime getLdt(){
        return this.ldt;
    }

    public boolean getIsDeposit() {
        return this.isDeposit;
    }
}
