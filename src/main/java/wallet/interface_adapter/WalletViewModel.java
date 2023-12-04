package wallet.interface_adapter;

import javax.swing.table.TableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WalletViewModel {
    public static final String BETS_LABEL = "Recent Bets";
    public static final String TRANSACTIONS_LABEL = "Recent Transactions";
    public static final String BALANCE_LABEL = "Current Account Balance";

    public static final String DEPOSIT_FUNDS_BUTTON_LABEL = "Deposit funds";
    public static final String WITHDRAW_FUNDS_BUTTON_LABEL = "Withdraw funds";
    public final String TITLE_LABEL = "Wallet";

    private static WalletState state = new WalletState(); // write user trans state

    public WalletViewModel() { super();}

    public void setState(WalletState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public static WalletState getState() {
        return state;
    }


    public String getViewName() { return "Wallet View";}
}
