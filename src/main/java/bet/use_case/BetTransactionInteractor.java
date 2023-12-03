package bet.use_case;

import bet.entity.Bet;
import schedule.entity.Event;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionOutputData;
import user.entity.User;

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
        String eventTitle = betTransactionInputData.getEventTitle();
        float wager = betTransactionInputData.getWager();
        boolean betOnHome = betTransactionInputData.getBetOnHome();
        String username = betTransactionInputData.getName();
        Bet bet = betDAO.get(eventTitle);
        boolean betAlreadyPlaced = false;
        Wallet wallet = new Wallet(username);//Not sure how to call since user.getwallet() doesn't have name as input.

        for (Bet bet1 : wallet.getBets().keySet()) {
            if (bet1.getEventTitle().equals(bet.getEventTitle())) {
                betAlreadyPlaced = true;
                break;
            }
        }//True if event is already in the bets list.

        if (wager < 0.0){betTransactionPresenter.prepareFailView("Invalid bet");}
        else if (betAlreadyPlaced){betTransactionPresenter.prepareFailView("Bet already placed");}
        //fail if bet already placed
        else {
            wallet.setBalance(wallet.getBalance() - wager);//Decrease the amount placed
            bet.setBettingSide(betOnHome);
            bet.setWager(wager);
            wallet.setBets(bet, wager);//Adding this bet to the bet list.
            BetTransactionOutputData betTransactionOutputData = new BetTransactionOutputData(eventTitle, wager, betOnHome,
                    false);
            betTransactionPresenter.prepareSuccessView(betTransactionOutputData);}
        //When should we call the payout method?
        // What do we need to add if we want to payout automatically after each game ends?
    }
}
