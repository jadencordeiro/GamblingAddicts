package view;

import navigation.interface_adapter.NavigationController;
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



    public LoggedInView(LoggedInViewModel loggedInViewModel, NavigationController navigationController) {
        this.loggedInViewModel = loggedInViewModel;
        this.navigationController = navigationController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(LoggedInViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        schedule = new JButton(LoggedInViewModel.SCHEDULE_BUTTON_LABEL);
        buttons.add(schedule);

        schedule.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(schedule)) {
                            navigationController.execute("schedule");
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
