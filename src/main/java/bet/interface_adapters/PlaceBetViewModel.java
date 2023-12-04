package bet.interface_adapters;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlaceBetViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Betting";
    public static final String BET_ON_HOME_BUTTON_LABEL = "Bet on Home";
    public static final String BET_ON_AWAY_BUTTON_LABEL = "Bet on Away";

    private PlaceBetState state = new PlaceBetState();
    public PlaceBetViewModel() {
        super("betting");
    }

    public void setState(PlaceBetState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlaceBetState getState() {
        return state;
    }
}