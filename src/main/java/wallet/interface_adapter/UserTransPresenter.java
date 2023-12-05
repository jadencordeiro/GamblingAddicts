package wallet.interface_adapter;

import interface_adapter.ViewManagerModel;
import wallet.data_access.WalletDataAccessObject;
import wallet.interface_adapter.WalletViewModel;
import wallet.use_case.user_transactions.UserTransactionOutputBoundary;
import wallet.use_case.user_transactions.UserTransactionOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UserTransPresenter implements UserTransactionOutputBoundary {

    private final WalletViewModel walletViewModel;
    private ViewManagerModel viewManagerModel;

    public UserTransPresenter(ViewManagerModel viewManagerModel,
                              WalletViewModel walletViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.walletViewModel = walletViewModel;
    }
    @Override
    public void prepareSuccessView(UserTransactionOutputData response) {
        WalletState walletState = walletViewModel.getState();
        walletState.setTransactions(response.getNewTransHistory());
        this.walletViewModel.setState(walletState);
        walletViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(walletViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
