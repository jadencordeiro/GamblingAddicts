package wallet.use_case.user_transactions;
import wallet.data_access.WalletDataAccessObject;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionOutputBoundary;

import java.time.LocalDateTime;

public class UserTransactionInteractor implements UserTransactionInputBoundary {
    final UserTransactionDataAccessInterface userDataAccessObject;
    final UserTransactionOutputBoundary userTransactionPresenter;

    public UserTransactionInteractor(UserTransactionDataAccessInterface userDataAccessObject,
                                     UserTransactionOutputBoundary userTransactionOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.userTransactionPresenter = userTransactionOutputBoundary;
    }

    @Override
    public void execute(UserTransactionInputData userTransactionInputData) {
        String name = userTransactionInputData.getName();
        float amount = userTransactionInputData.getAmount();
        LocalDateTime ldt = userTransactionInputData.getLdt();
        boolean isDeposit = userTransactionInputData.getIsDeposit();
        Wallet wallet = userDataAccessObject.getWallet(name);


        if ((amount < 0.0) || (amount > wallet.getBalance())) {
            userTransactionPresenter.prepareFailView(amount + " is not a valid amount.");
        } else {
            if (isDeposit) {
                wallet.setBalance(wallet.getBalance() + amount);
                wallet.setTransactionHistory(ldt, amount);
            } else {
                wallet.setBalance(wallet.getBalance() - amount);
                wallet.setTransactionHistory(ldt, (-1.0F * amount));
            }
            UserTransactionOutputData userTransactionOutputData = new UserTransactionOutputData(name, amount, ldt,
                    wallet.getTransactionHistory(), false);
            userTransactionPresenter.prepareSuccessView(userTransactionOutputData);
        }
    }
}
