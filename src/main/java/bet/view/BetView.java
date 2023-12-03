package bet.view;

import bet.interface_adapters.PlaceBetViewModel;
import navigation.interface_adapter.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BetView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Place your bets";

    private final PlaceBetViewModel placeBetViewModel;
    private final NavigationController navigationController;
    private final JButton betOnHomeButton;
    private final JButton betOnAwayButton;
    private final JTextField betAmountField;


    public BetView(PlaceBetViewModel placeBetViewModel, NavigationController navigationController) {
        this.placeBetViewModel = placeBetViewModel;
        this.navigationController = navigationController;
        this.placeBetViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(placeBetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        betOnHomeButton = new JButton(placeBetViewModel.BET_ON_HOME_BUTTON_LABEL);
        betOnAwayButton = new JButton(placeBetViewModel.BET_ON_AWAY_BUTTON_LABEL);
        betAmountField = new JTextField("Enter Bet Amount");
        betOnHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Handle bet on home button click
            }
        });

        betOnAwayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Handle bet on away button click
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(betOnHomeButton);
        this.add(betOnAwayButton);
        this.add(betAmountField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
