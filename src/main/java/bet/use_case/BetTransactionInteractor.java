package bet.use_case;

import bet.entity.Bet;
import schedule.entity.Event;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionOutputData;

public class BetTransactionInteractor implements BetTransactionInputBoundary{
    final BetTransactionDataAccessInterface betDAO;
    final BetTransactionOutputBoundary betTransactionPresenter;

    public BetTransactionInteractor(BetTransactionDataAccessInterface betTransactionDataAccessInterface,
                                    BetTransactionOutputBoundary betTransactionOutputBoundary){
        this.betDAO = betTransactionDataAccessInterface;
        this.betTransactionPresenter = betTransactionOutputBoundary;
    }
    @Override
    public void execute(BetTransactionInputData betTransactionInputData) {
        Event event = betTransactionInputData.getEvent();
        float wager = betTransactionInputData.getWager();
        boolean betOnHome = betTransactionInputData.getBetOnHome();
        Bet bet = betDAO.getBet(event);

        if (wager < 0.0){betTransactionPresenter.prepareFailView("Invalid bet");}
        else if (event == bet.getEvent()){betTransactionPresenter.prepareFailView("Bet already placed");}
        //Check if bet is duplicated for the same event
        else {
            BetTransactionOutputData betTransactionOutputData = new BetTransactionOutputData(event, wager, betOnHome,
                    false);
            betTransactionPresenter.prepareSuccessView(betTransactionOutputData);}
        //When should we call the payout method?
        // What do we need to add if we want to payout automatically after each game ends?
    }
}
