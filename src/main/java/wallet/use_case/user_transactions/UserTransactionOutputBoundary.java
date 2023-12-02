package wallet.use_case.user_transactions;

public interface UserTransactionOutputBoundary {
    public void prepareSuccessView(UserTransactionOutputData userTransactionOutputData);
    public void prepareFailView(String error);
}
