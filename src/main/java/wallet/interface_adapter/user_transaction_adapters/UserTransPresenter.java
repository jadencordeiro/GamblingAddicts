package wallet.interface_adapter.user_transaction_adapters;

import interface_adapter.ViewManagerModel;
import wallet.interface_adapter.WalletViewModel;
import wallet.use_case.user_transactions.UserTransactionOutputBoundary;
import wallet.use_case.user_transactions.UserTransactionOutputData;

public class UserTransPresenter implements UserTransactionOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private final WalletViewModel walletViewModel;

    public UserTransPresenter(ViewManagerModel viewManagerModel, WalletViewModel walletViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.walletViewModel = walletViewModel;
    }

    @Override
    public void prepareSuccessView(UserTransactionOutputData userTransactionOutputData) {
        UserTransState walletState = walletViewModel.getState();
        walletState.setBalance(userTransactionOutputData.getNewBalance());
        walletState.setWalletError(null);
        this.walletViewModel.setState(walletState);
        this.walletViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UserTransState walletState = walletViewModel.getState();
        walletState.setWalletError(error);
        walletViewModel.firePropertyChanged();;

    }
}
