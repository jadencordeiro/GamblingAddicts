package app;

import interface_adapter.ViewManagerModel;
import navigation.NavigationInputBoundary;
import navigation.NavigationInteractor;
import navigation.NavigationOutputBoundary;
import navigation.interface_adapter.NavigationController;
import navigation.interface_adapter.NavigationPresenter;
import user.use_case.LoginOutputBoundary;

public class NavigationUseCaseFactory {

    NavigationUseCaseFactory() {}

    public static NavigationController createController(ViewManagerModel viewManagerModel){
        NavigationOutputBoundary navigationOutputBoundary = new NavigationPresenter(viewManagerModel);
        NavigationInputBoundary navigationInputBoundary = new NavigationInteractor(navigationOutputBoundary);
        return new NavigationController(navigationInputBoundary);
    }
}
