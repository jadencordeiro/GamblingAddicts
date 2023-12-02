package app;

import interface_adapter.ViewManagerModel;
import schedule.data_access.ScheduleDataAccessObject;
import schedule.entity.SportEventFactory;
import schedule.service.refresh.interface_adapter.ScheduleViewModel;
import user.data_access.UserDataAccessObject;
import user.entity.UserFactory;
import user.interface_adapter.LoggedInViewModel;
import user.interface_adapter.LoginViewModel;
import user.interface_adapter.SignupViewModel;
import view.*;

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

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject();
            userDataAccessObject.FileUserDataAccessObject("./users.csv", new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScheduleDataAccessObject scheduleDataAccessObject;
        try {
            scheduleDataAccessObject = new ScheduleDataAccessObject("./events.csv", new SportEventFactory());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        ScheduleView scheduleView = ScheduleUseCaseFactory.create(viewManagerModel, scheduleViewModel, scheduleDataAccessObject);
        views.add(scheduleView, scheduleView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}