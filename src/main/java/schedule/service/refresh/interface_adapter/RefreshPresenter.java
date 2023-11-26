package schedule.service.refresh.interface_adapter;

import interface_adapter.ViewManagerModel;
import schedule.service.refresh.RefreshOutputBoundary;
import schedule.service.refresh.RefreshOutputData;

public class RefreshPresenter implements RefreshOutputBoundary {

    private final ScheduleViewModel scheduleViewModel;
    private ViewManagerModel viewManagerModel;

    public RefreshPresenter(ScheduleViewModel scheduleViewModel, ViewManagerModel viewManagerModel) {
        this.scheduleViewModel = scheduleViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RefreshOutputData response) {
        ScheduleState scheduleState = scheduleViewModel.getState();
        scheduleState.setEvents(response.getEvents());
        this.scheduleViewModel.setState(scheduleState);
        scheduleViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(scheduleViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
