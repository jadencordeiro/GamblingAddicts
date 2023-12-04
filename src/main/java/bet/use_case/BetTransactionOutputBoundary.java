package bet.use_case;

public interface BetTransactionOutputBoundary {
    public void prepareSuccessView(BetTransactionOutputData betTransactionOutputData);
    public void prepareFailView(String error);
}
