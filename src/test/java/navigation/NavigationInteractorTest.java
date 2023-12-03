package navigation;

import org.junit.jupiter.api.Test;

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

        NavigationInputData inputData = new NavigationInputData("name");

        NavigationInputBoundary interactor = new NavigationInteractor(navigationPresenter);
        interactor.execute(inputData);
    }
}
