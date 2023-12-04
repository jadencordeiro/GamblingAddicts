package bet.interface_adapters;

import bet.use_case.BetTransactionInputBoundary;
import bet.use_case.BetTransactionInputData;

public class PlaceBetController {

    final BetTransactionInputBoundary betUseCaseInteractor;
    public PlaceBetController(BetTransactionInputBoundary betUseCaseInteractor){
        this.betUseCaseInteractor = betUseCaseInteractor;
    }

    public void execute(String eventTitle, float wager, boolean betOnHome, String username) {
        BetTransactionInputData betTransactionInputData = new BetTransactionInputData(
                eventTitle, wager, betOnHome, username);

        betUseCaseInteractor.execute(betTransactionInputData);
    }
}
