package bet.use_case;

import bet.entity.Bet;
import user.entity.User;
import wallet.data_access.WalletDataAccessObject;
import wallet.entity.Wallet;
import user.data_access.UserDataAccessObject;
import schedule.data_access.FileScheduleDataAccessObject;

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
        Bet bet = betDAO.get(eventTitle);
        User user = userDataAccessObject.get(betTransactionInputData.getUsername());//need appropriate way to call userDAO
        Wallet wallet = user.getWallet();

        if (wager < 0.0){betTransactionPresenter.prepareFailView("Invalid bet");}
        else if (wallet.getBets().containsKey(bet)){betTransactionPresenter.prepareFailView("Bet already placed");}
        //fail if bet already placed
        else {
            wallet.setBalance(wallet.getBalance() - wager);//Decrease the amount placed
            bet.setBettingSide(betOnHome);
            bet.setWager(wager);
            wallet.setBets(bet, -wager);//Adding this bet to the bet list. Use -wager to show a loss before payout.
            BetTransactionOutputData betTransactionOutputData = new BetTransactionOutputData(eventTitle, wager, betOnHome,
                    false);
            betTransactionPresenter.prepareSuccessView(betTransactionOutputData);}
        //When should we call the payout method?
        // What do we need to add if we want to payout automatically after each game ends?
    }
}
