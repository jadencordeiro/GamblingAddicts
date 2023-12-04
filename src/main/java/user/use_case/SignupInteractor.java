package user.use_case;


import user.data_access.UserDataAccessInterface;
import user.entity.User;
import user.entity.UserFactory;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {

    final UserDataAccessInterface userDataAccessObject;
    final UserTransactionDataAccessInterface walletDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                            UserTransactionDataAccessInterface walletDataAccessObject, SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.walletDataAccessObject = walletDataAccessObject;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {


            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), now);
            userDataAccessObject.save(user);
            walletDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
