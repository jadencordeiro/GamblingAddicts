package app;

import interface_adapter.ViewManagerModel;
import navigation.interface_adapter.NavigationController;
import view.WalletView;
import wallet.interface_adapter.WalletViewModel;
import wallet.interface_adapter.user_transaction_adapters.UserTransController;
import wallet.interface_adapter.user_transaction_adapters.UserTransPresenter;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;
import wallet.use_case.user_transactions.UserTransactionInputBoundary;
import wallet.use_case.user_transactions.UserTransactionInteractor;
import wallet.use_case.user_transactions.UserTransactionOutputBoundary;

import javax.swing.*;

public class WalletUseCaseFactory {

    WalletUseCaseFactory() {}

    public static WalletView create(ViewManagerModel viewManagerModel, WalletViewModel walletViewModel, UserTransactionDataAccessInterface userTransactionDataAccessObject, NavigationController navigationController){

        try {
            UserTransController userTransController = createUserTransUseCase(viewManagerModel, walletViewModel, userTransactionDataAccessObject);
            return new WalletView(walletViewModel, userTransController, navigationController);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not open wallet data file.");
        }
        return null;
    }

    private static UserTransController createUserTransUseCase(ViewManagerModel viewManagerModel, WalletViewModel walletViewModel, UserTransactionDataAccessInterface userTransactionDataAccessObject) {
        UserTransactionOutputBoundary userTransactionOutputBoundary = new UserTransPresenter(viewManagerModel, walletViewModel);

        UserTransactionInputBoundary userTransInteractor = new UserTransactionInteractor(userTransactionDataAccessObject, userTransactionOutputBoundary);

        return new UserTransController(userTransInteractor);
    }

}
