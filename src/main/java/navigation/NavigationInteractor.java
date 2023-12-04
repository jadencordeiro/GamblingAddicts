package navigation;

public class NavigationInteractor implements NavigationInputBoundary{

    final NavigationOutputBoundary navigationPresenter;

    public NavigationInteractor(NavigationOutputBoundary navigationPresenter) {
        this.navigationPresenter = navigationPresenter;
    }

    @Override
    public void execute(NavigationInputData navigationInputData) {
        NavigationOutputData outputData = new NavigationOutputData(navigationInputData.getName(), navigationInputData.getUser());
        navigationPresenter.prepareSuccessView(outputData);
    }
}
