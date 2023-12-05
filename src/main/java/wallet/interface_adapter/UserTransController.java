package wallet.interface_adapter;

import wallet.use_case.user_transactions.UserTransactionInputBoundary;
import wallet.use_case.user_transactions.UserTransactionInputData;
import wallet.use_case.user_transactions.UserTransactionInteractor;

import java.time.LocalDateTime;

public class UserTransController {
    final UserTransactionInputBoundary userTransactionInteractor;
    public UserTransController(UserTransactionInteractor userTransactionInteractor){
        this.userTransactionInteractor = userTransactionInteractor;
    }

    public void execute(String name, float amount, LocalDateTime ldt, boolean isDeposit) {
        UserTransactionInputData userTransactionInputData = new UserTransactionInputData(name,
                amount, ldt, isDeposit);

        userTransactionInteractor.execute(userTransactionInputData);
    }
}
