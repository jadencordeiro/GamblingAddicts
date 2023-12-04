package navigation.interface_adapter;

import interface_adapter.ViewManagerModel;
import navigation.NavigationOutputBoundary;
import navigation.NavigationOutputData;

public class NavigationPresenter implements NavigationOutputBoundary {

    private ViewManagerModel viewManagerModel;

    public NavigationPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(NavigationOutputData navigationOutputData) {
        viewManagerModel.setActiveView(navigationOutputData.getName());
        viewManagerModel.firePropertyChanged();
    }
}
