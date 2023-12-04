package wallet.use_case.user_transactions;

import org.junit.jupiter.api.Test;
import user.entity.User;
import wallet.data_access.InMemoryWalletDataAccessObject;
import wallet.data_access.WalletDataAccessObject;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTransactionInteractorTest {

    @Test
    void successTest1(){
        UserTransactionDataAccessInterface walletRespository = new InMemoryWalletDataAccessObject();
        User user = new User("Jaden", "Jaden", LocalDateTime.now());
        walletRespository.save(user);
        UserTransactionInputData inputData = new UserTransactionInputData("Jaden", 10.0f, LocalDateTime.now(), true);
        UserTransactionOutputBoundary walletPresenter = new UserTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(UserTransactionOutputData userTransactionOutputData) {
                assertEquals("Jaden", userTransactionOutputData.getName());
                assertNotNull(userTransactionOutputData.getLdt());
                assertEquals(10.0f, userTransactionOutputData.getAmount());
                assertEquals(10.0f, userTransactionOutputData.getNewBalance());

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        UserTransactionInputBoundary interactor = new UserTransactionInteractor(walletRespository, walletPresenter);
        interactor.execute(inputData);

    }

    @Test
    void successTest2(){
        UserTransactionDataAccessInterface walletRespository = new InMemoryWalletDataAccessObject();
        User user = new User("Jaden", "Jaden", LocalDateTime.now());
        user.getWallet().setBalance(11.0f);
        walletRespository.save(user);
        UserTransactionInputData inputData = new UserTransactionInputData("Jaden", 10.0f, LocalDateTime.now(), false);

        UserTransactionOutputBoundary walletPresenter = new UserTransactionOutputBoundary() {
            @Override
            public void prepareSuccessView(UserTransactionOutputData userTransactionOutputData) {
                assertEquals("Jaden", userTransactionOutputData.getName());
                assertNotNull(userTransactionOutputData.getLdt());
                assertEquals(10.0f, userTransactionOutputData.getAmount());
                assertEquals(1.0f, userTransactionOutputData.getNewBalance());

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        UserTransactionInputBoundary interactor = new UserTransactionInteractor(walletRespository, walletPresenter);
        interactor.execute(inputData);
    }

}