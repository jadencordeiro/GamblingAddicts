package wallet.use_case.user_transactions;

import user.entity.User;

public interface UserTransactionInputBoundary {
    public void execute(UserTransactionInputData userTransactionInputData);
}
