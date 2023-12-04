package view;

import navigation.interface_adapter.NavigationController;
import user.interface_adapter.LoggedInState;
import user.interface_adapter.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";

    private final LoggedInViewModel loggedInViewModel;
    private final NavigationController navigationController;
    private final JButton schedule;
    private final JButton wallet;
    JLabel username;
    String user;


    public LoggedInView(LoggedInViewModel loggedInViewModel, NavigationController navigationController) {
        this.loggedInViewModel = loggedInViewModel;
        this.navigationController = navigationController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(LoggedInViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        schedule = new JButton(LoggedInViewModel.SCHEDULE_BUTTON_LABEL);
        buttons.add(schedule);
        wallet = new JButton(LoggedInViewModel.WALLET_BUTTON_LABEL);
        buttons.add(wallet);

        schedule.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(schedule)) {
                            navigationController.execute("schedule", loggedInViewModel.getState().getUsername());
                        }
                    }
                }
        );

        wallet.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(wallet)) {
                            navigationController.execute("wallet", loggedInViewModel.getState().getUsername());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        user = state.getUsername();
    }

}
