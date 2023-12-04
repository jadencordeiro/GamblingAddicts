package navigation;

import org.junit.jupiter.api.Test;
import user.entity.User;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class NavigationInteractorTest {

    @Test
    void successTest() {

        NavigationOutputBoundary navigationPresenter = new NavigationOutputBoundary() {
            @Override
            public void prepareSuccessView(NavigationOutputData navigationOutputData) {
                assertEquals(navigationOutputData.getName(), "name");
            }
        };

        NavigationInputData inputData = new NavigationInputData("name", "jaden");

        NavigationInputBoundary interactor = new NavigationInteractor(navigationPresenter);
        interactor.execute(inputData);
    }
}
