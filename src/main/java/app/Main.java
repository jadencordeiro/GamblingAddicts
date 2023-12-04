package app;

import bet.data_access.BetDataAccessObject;
import bet.entity.Bet;
import bet.entity.BetFactory;
import bet.interface_adapters.PlaceBetController;
import bet.interface_adapters.PlaceBetViewModel;
import interface_adapter.ViewManagerModel;
import navigation.interface_adapter.NavigationController;
import schedule.data_access.FileScheduleDataAccessObject;
import schedule.entity.SportEventFactory;
import schedule.service.refresh.interface_adapter.ScheduleViewModel;
import user.data_access.UserDataAccessObject;
import user.entity.UserFactory;
import user.interface_adapter.LoggedInViewModel;
import user.interface_adapter.LoginViewModel;
import user.interface_adapter.SignupViewModel;
import view.*;
import wallet.data_access.WalletDataAccessObject;
import wallet.entity.WalletFactory;
import wallet.interface_adapter.WalletViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Magic Wager World");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ScheduleViewModel scheduleViewModel = new ScheduleViewModel();
        PlaceBetViewModel betViewModel = new PlaceBetViewModel();
        WalletViewModel walletViewModel = new WalletViewModel();


        BetDataAccessObject betDataAccessObject;
        try{
            betDataAccessObject = new BetDataAccessObject("./bets.csv", new BetFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WalletDataAccessObject walletDataAccessObject;
        try {
            walletDataAccessObject = new WalletDataAccessObject("./wallets.csv", new WalletFactory(), betDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject();
            userDataAccessObject.FileUserDataAccessObject("./users.csv", new UserFactory(), walletDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileScheduleDataAccessObject fileScheduleDataAccessObject;
        try {
            fileScheduleDataAccessObject = new FileScheduleDataAccessObject("./events.csv", new SportEventFactory());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PlaceBetController placeBetController = PlaceBetUseCaseFactory.createPlaceBetController(viewManagerModel, betViewModel, betDataAccessObject, userDataAccessObject, fileScheduleDataAccessObject);

        NavigationController navigationController = NavigationUseCaseFactory.createController(viewManagerModel, loggedInViewModel, loginViewModel, walletViewModel, scheduleViewModel, signupViewModel);

        GamblingStartupView startupView = new GamblingStartupView(navigationController);
        views.add(startupView, startupView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, navigationController, walletDataAccessObject);
        views.add(signupView, signupView.viewName);



        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, navigationController);
        views.add(loginView, loginView.viewName);



        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loggedInViewModel, navigationController);
        views.add(loggedInView, loggedInView.viewName);


        ScheduleView scheduleView = ScheduleUseCaseFactory.create(viewManagerModel, scheduleViewModel, fileScheduleDataAccessObject, navigationController, placeBetController);
        views.add(scheduleView, scheduleView.viewName);


        WalletView walletView = WalletUseCaseFactory.create(viewManagerModel, walletViewModel, walletDataAccessObject, navigationController);
        views.add(walletView, walletView.viewName);

        viewManagerModel.setActiveView(startupView.viewName);
        viewManagerModel.firePropertyChanged();



        application.pack();
        application.setVisible(true);
    }
}