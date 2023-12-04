package wallet.view;

import wallet.interface_adapter.UserTransController;
import wallet.interface_adapter.UserTransState;
import wallet.interface_adapter.WalletState;
import wallet.interface_adapter.WalletViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WalletView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Wallet";

    private final WalletViewModel walletViewModel;
    private final UserTransController userTransController;
    private final JButton depositFunds;
    private final JButton withdrawFunds;

    private JTable transactionTable;
    private JTable betTable;

    public WalletView(WalletViewModel walletViewModel, UserTransController userTransController) {
        this.walletViewModel = walletViewModel;
        this.userTransController = userTransController;

        JLabel title = new JLabel(walletViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bets = new JLabel(walletViewModel.BETS_LABEL);
        // set alignment

        JLabel transactions = new JLabel(walletViewModel.TRANSACTIONS_LABEL);
        // set alignment

        JLabel balance = new JLabel(walletViewModel.BALANCE_LABEL);
        JTable transactionTable = new JTable();
        JTable betTable = new JTable();

        JPanel buttons = new JPanel();
        depositFunds = new JButton(walletViewModel.DEPOSIT_FUNDS_BUTTON_LABEL);
        buttons.add(depositFunds);
        withdrawFunds = new JButton(walletViewModel.WITHDRAW_FUNDS_BUTTON_LABEL);
        buttons.add(withdrawFunds);

        betTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(betTable);
        transactionTable = new JTable();
        JScrollPane scrollPane1 = new JScrollPane(transactionTable);

        setTransactionData(getTransactionData());
        setBetData(getBetData());

        depositFunds.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(depositFunds)){
                            //userTransController.execute();
                        }
                    }
                }
        );

        withdrawFunds.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(withdrawFunds)){
                            //userTransController.execute();
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(bets);
        this.add(transactions);

        this.add(balance);
        this.add(buttons);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserTransState state = (UserTransState) evt.getNewValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void setTransactionData(String[][] data){
        String[] columnNames = {"Date", "Amount"};
        transactionTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private void setBetData(String[][] data){
        String[] columnNames = {"Bet", "Amount"};
        betTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private String[][] getTransactionData() {
        WalletState currentState = WalletViewModel.getState();
        return currentState.getTransactionData();
    }

    private String[][] getBetData(){
        WalletState currentState = WalletViewModel.getState();
        return currentState.getBetData();
    }
}
