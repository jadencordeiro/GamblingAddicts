package app;

import interface_adapter.ViewManagerModel;
import navigation.interface_adapter.NavigationController;
import user.interface_adapter.LoggedInViewModel;
import user.interface_adapter.LoginController;
import user.interface_adapter.LoginPresenter;
import user.interface_adapter.LoginViewModel;
import user.use_case.LoginInputBoundary;
import user.use_case.LoginInteractor;
import user.use_case.LoginOutputBoundary;
import user.use_case.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, LoginUserDataAccessInterface userDataAccessObject, NavigationController navigationController) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            return new LoginView(loginController, loginViewModel, navigationController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, LoginUserDataAccessInterface userDataAccessObject) throws  IOException{
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
