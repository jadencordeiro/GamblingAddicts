package navigation.interface_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import navigation.NavigationOutputBoundary;
import navigation.NavigationOutputData;
import schedule.service.refresh.interface_adapter.ScheduleState;
import schedule.service.refresh.interface_adapter.ScheduleViewModel;
import user.interface_adapter.LoggedInState;
import user.interface_adapter.LoggedInViewModel;
import user.interface_adapter.LoginViewModel;
import user.interface_adapter.SignupViewModel;
import view.SignupView;
import wallet.interface_adapter.WalletViewModel;
import wallet.interface_adapter.user_transaction_adapters.UserTransState;

public class NavigationPresenter implements NavigationOutputBoundary {


    private ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final LoginViewModel loginViewModel;
    private final WalletViewModel walletViewModel;
    private final ScheduleViewModel scheduleViewModel;
    private final SignupViewModel signupViewModel;


    public NavigationPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel, WalletViewModel walletViewModel, ScheduleViewModel scheduleViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.walletViewModel = walletViewModel;
        this.scheduleViewModel = scheduleViewModel;
        this.signupViewModel = signupViewModel;
    }


    @Override
    public void prepareSuccessView(NavigationOutputData navigationOutputData) {
        String name = navigationOutputData.getName();
        String user = navigationOutputData.getUser();

        if(name.equals("schedule")){
            ScheduleState scheduleState = scheduleViewModel.getState();
            scheduleState.setUser(user);
            this.scheduleViewModel.setState(scheduleState);
            this.scheduleViewModel.firePropertyChanged();
        } else if (name.equals("wallet")) {
            UserTransState walletState = walletViewModel.getState();
            walletState.setUser(user);
            this.walletViewModel.setState(walletState);
            this.walletViewModel.firePropertyChanged();
        } else if (name.equals("home")) {
            LoggedInState loggedInState = loggedInViewModel.getState();
            loggedInState.setUsername(user);
            this.loggedInViewModel.setState(loggedInState);
            this.loggedInViewModel.firePropertyChanged();
        }

        viewManagerModel.setActiveView(navigationOutputData.getName());
        viewManagerModel.firePropertyChanged();
    }
}
