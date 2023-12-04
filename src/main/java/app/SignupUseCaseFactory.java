package app;

import interface_adapter.ViewManagerModel;
import navigation.interface_adapter.NavigationController;
import user.data_access.UserDataAccessInterface;
import user.entity.UserFactory;
import user.interface_adapter.LoginViewModel;
import user.interface_adapter.SignupController;
import user.interface_adapter.SignupPresenter;
import user.interface_adapter.SignupViewModel;
import user.use_case.SignupInputBoundary;
import user.use_case.SignupInteractor;
import user.use_case.SignupOutputBoundary;
import view.SignupView;
import wallet.data_access.WalletDataAccessObject;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, UserDataAccessInterface userDataAccessObject, NavigationController navigationController, WalletDataAccessObject walletDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject, walletDataAccessObject);
            return new SignupView(signupController, signupViewModel, navigationController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }


    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, UserDataAccessInterface userDataAccessObject, WalletDataAccessObject walletDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new UserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, walletDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}

