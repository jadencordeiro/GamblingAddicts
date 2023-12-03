package bet.interface_adapters;

import bet.use_case.BetTransactionInputBoundary;
import bet.use_case.BetTransactionInputData;
import user.use_case.LoginInputData;

public class PlaceBetController {

    final BetTransactionInputBoundary betUseCaseInteractor;
    public PlaceBetController(BetTransactionInputBoundary betUseCaseInteractor){
        this.betUseCaseInteractor = betUseCaseInteractor;
    }

    public void execute(String eventTitle, float wager, boolean betOnHome) {
        BetTransactionInputData betTransactionInputData = new BetTransactionInputData(
                eventTitle, wager, betOnHome);

        betUseCaseInteractor.execute(betTransactionInputData);
    }
}
