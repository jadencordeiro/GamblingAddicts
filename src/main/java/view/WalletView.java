package view;



import navigation.interface_adapter.NavigationController;
import wallet.interface_adapter.user_transaction_adapters.UserTransController;
import wallet.interface_adapter.WalletViewModel;
import wallet.interface_adapter.user_transaction_adapters.UserTransState;
import wallet.interface_adapter.user_transaction_adapters.UserTransViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WalletView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "wallet";
    private final WalletViewModel walletViewModel;
    private final UserTransViewModel userTransViewModel;
    private final UserTransController userTransController;
    private final NavigationController navigationController;
    private final JButton deposit;
    private final JButton home;
    private final JButton withdraw;

    JLabel user;
    String username;
    JLabel balance;

    JTable transHistoryTable;

    public WalletView(WalletViewModel walletViewModel,UserTransViewModel userTransViewModel, UserTransController userTransController, NavigationController navigationController) {
        this.walletViewModel = walletViewModel;
        this.userTransViewModel = userTransViewModel;
        this.userTransController = userTransController;
        this.navigationController = navigationController;
        this.walletViewModel.addPropertyChangeListener(this);

        walletViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(WalletViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usernameInfo = new JLabel("Currently logged in: ");
        user = new JLabel();
        JLabel balanceInfo = new JLabel("Current balance: ");
        balance = new JLabel();

        JPanel buttons = new JPanel();
        home = new JButton(WalletViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);
        deposit = new JButton(WalletViewModel.DEPOSIT_BUTTON_LABEL);
        buttons.add(deposit);
        withdraw = new JButton(WalletViewModel.WITHDRAW_BUTTON_LABEL);
        buttons.add(withdraw);

        transHistoryTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(transHistoryTable);
        setTransHistoryData(getTransHistoryData());


        home.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(home)) {
                            navigationController.execute("logged in " + walletViewModel.getState().getUser());
                        }
                    }
                }
        );

        deposit.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(deposit)){
                            Float amount = Float.parseFloat(JOptionPane.showInputDialog("How much would you like to deposit?"));
                            userTransController.execute(username,amount, true);
                        }
                    }
                }
        );

        withdraw.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(withdraw)){
                            Float amount = Float.parseFloat(JOptionPane.showInputDialog("How much would you like to withdraw?"));
                            userTransController.execute(username,amount, false);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(usernameInfo);
        this.add(user);
        this.add(balanceInfo);
        this.add(balance);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserTransState state = (UserTransState) evt.getNewValue();
        username = state.getUser();
        user.setText(username);
        balance.setText(String.valueOf(state.getBalance()));

        if (state.getWalletError() != null) {
            JOptionPane.showMessageDialog(this, state.getWalletError());
        }
    }

    private void setTransHistoryData(String[][] data){
        String[] columnNames = {"Date/Time", "Amount"};
        transHistoryTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private String[][] getTransHistoryData() {
        UserTransState currentState = walletViewModel.getState();
        return currentState.getData();
    }

}