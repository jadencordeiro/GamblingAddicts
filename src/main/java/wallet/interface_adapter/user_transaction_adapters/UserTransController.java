package wallet.interface_adapter.user_transaction_adapters;

import wallet.use_case.user_transactions.UserTransactionInputBoundary;
import wallet.use_case.user_transactions.UserTransactionInputData;

import java.time.LocalDateTime;

public class UserTransController {

    final UserTransactionInputBoundary userTransInteractor;


    public UserTransController(UserTransactionInputBoundary userTransInteractor) {
        this.userTransInteractor = userTransInteractor;
    }

    public void execute(String name, Float amount, Boolean isDeposit){
        UserTransactionInputData userTransactionInputData = new UserTransactionInputData(name, amount, LocalDateTime.now(), isDeposit);
        userTransInteractor.execute(userTransactionInputData);
    }
}
