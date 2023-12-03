package wallet.view;



import wallet.interface_adapter.user_transaction_adapters.UserTransController;
import wallet.interface_adapter.user_transaction_adapters.UserTransState;
import wallet.interface_adapter.user_transaction_adapters.UserTransViewModel;
import wallet.interface_adapter.WalletViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class WalletView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Wallet";

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
