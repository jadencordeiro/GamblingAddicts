package schedule.service.refresh.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ScheduleViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Schedule";
    public static final String REFRESH_BUTTON_LABEL = "Refresh";
    public static final String HOME_BUTTON_LABEL = "Home";
    public ScheduleState state = new ScheduleState();

    public ScheduleViewModel(){
        super("schedule");
    }

    public void setState(ScheduleState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ScheduleState getState() {
        return state;
    }
}