package user.use_case;

import org.junit.jupiter.api.Test;
import user.data_access.InMemoryUserDataAccessObject;
import user.data_access.UserDataAccessInterface;
import user.entity.UserFactory;
import wallet.data_access.InMemoryWalletDataAccessObject;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest(){
        UserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserTransactionDataAccessInterface walletRespository = new InMemoryWalletDataAccessObject();
        SignupInputData inputData = new SignupInputData("Jaden", "a", "a");
        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("Jaden", user.getUsername());
                assertNotNull(user.getCreationTime());
                assertTrue(userRepository.existsByName("Jaden"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, walletRespository, presenter, new UserFactory());
        interactor.execute(inputData);
    }

}