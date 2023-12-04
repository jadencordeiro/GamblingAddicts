package app;

import interface_adapter.ViewManagerModel;
import navigation.NavigationInputBoundary;
import navigation.NavigationInteractor;
import navigation.NavigationOutputBoundary;
import navigation.interface_adapter.NavigationController;
import navigation.interface_adapter.NavigationPresenter;
import user.interface_adapter.LoggedInViewModel;
import view.LoggedInView;

public class LoggedInUseCaseFactory {

    LoggedInUseCaseFactory() {}

    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        NavigationController navigationController = createNavigationUseCase(viewManagerModel, loggedInViewModel);
        return new LoggedInView(loggedInViewModel, navigationController);
    }

    private static NavigationController createNavigationUseCase(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        NavigationOutputBoundary navigationOutputBoundary = new NavigationPresenter(viewManagerModel);

        NavigationInputBoundary navigationInteractor = new NavigationInteractor(navigationOutputBoundary);

        return new NavigationController(navigationInteractor);
    }

}
