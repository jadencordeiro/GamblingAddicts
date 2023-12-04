package user.use_case;

import org.junit.jupiter.api.Test;
import user.data_access.InMemoryUserDataAccessObject;
import user.data_access.UserDataAccessInterface;
import user.entity.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    @Test
    void successTest(){
        UserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        User user = new User("Jaden", "a", LocalDateTime.now());
        userRepository.save(user);

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Jaden", user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputData inputData = new LoginInputData("Jaden", "a");


        LoginInputBoundary interactor = new LoginInteractor((LoginUserDataAccessInterface) userRepository, presenter);
        interactor.execute(inputData);

    }

}