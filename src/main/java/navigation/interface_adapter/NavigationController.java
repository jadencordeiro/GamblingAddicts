package navigation.interface_adapter;

import navigation.NavigationInputBoundary;
import navigation.NavigationInputData;

public class NavigationController {

    final NavigationInputBoundary navigationUseCaseInteractor;

    public NavigationController(NavigationInputBoundary navigationUseCaseInteractor) {
        this.navigationUseCaseInteractor = navigationUseCaseInteractor;
    }

    public void execute(String name){
        NavigationInputData navigationInputData = new NavigationInputData(name);
        navigationUseCaseInteractor.execute(navigationInputData);
    }
}
