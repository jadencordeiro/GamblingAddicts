package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import navigation.NavigationInputBoundary;
import navigation.NavigationInteractor;
import navigation.NavigationOutputBoundary;
import navigation.interface_adapter.NavigationController;
import navigation.interface_adapter.NavigationPresenter;
import schedule.service.refresh.interface_adapter.ScheduleViewModel;
import user.interface_adapter.LoggedInViewModel;
import user.interface_adapter.LoginViewModel;
import user.interface_adapter.SignupViewModel;
import view.LoginView;
import view.SignupView;
import wallet.entity.Wallet;
import wallet.interface_adapter.WalletViewModel;
import wallet.interface_adapter.user_transaction_adapters.UserTransViewModel;

public class NavigationUseCaseFactory {

    NavigationUseCaseFactory() {}

    public static NavigationController createController(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel, WalletViewModel walletViewModel, ScheduleViewModel scheduleViewModel, SignupViewModel signupViewModel){
        NavigationOutputBoundary navigationOutputBoundary = new NavigationPresenter(viewManagerModel, loggedInViewModel, loginViewModel, walletViewModel, scheduleViewModel, signupViewModel);
        NavigationInputBoundary navigationInputBoundary = new NavigationInteractor(navigationOutputBoundary);
        return new NavigationController(navigationInputBoundary);
    }
}
