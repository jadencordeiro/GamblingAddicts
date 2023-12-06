package wallet.interface_adapter;

import interface_adapter.ViewModel;
import schedule.service.refresh.interface_adapter.ScheduleState;
import wallet.interface_adapter.user_transaction_adapters.UserTransState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WalletViewModel extends ViewModel {


    public static final String TITLE_LABEL = "Wallet";
    public static final String DEPOSIT_BUTTON_LABEL = "Deposit";
    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String WITHDRAW_BUTTON_LABEL = "Withdraw";
    public UserTransState state = new UserTransState();

    public WalletViewModel(){
        super("schedule");
    }


    public void setState(UserTransState state) {
        this.state = state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public UserTransState getState() {
        return state;
    }

}