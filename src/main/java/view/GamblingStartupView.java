package view;

import navigation.interface_adapter.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamblingStartupView extends JPanel implements ActionListener, PropertyChangeListener {

    public String viewName = "start up";
    private final NavigationController navigationController;
    private final JButton signUp;
    private final JButton logIn;

    public GamblingStartupView(NavigationController navigationController) {
        this.navigationController = navigationController;

        JLabel title = new JLabel("Gambling Addicts");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signUp = new JButton("Sign Up");
        buttons.add(signUp);

        logIn = new JButton("Log In");
        buttons.add(logIn);

        signUp.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signUp)){
                            navigationController.execute("sign up", "");
                        }
                    }
                }
        );

        logIn.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logIn)){
                            navigationController.execute("log in", "");
                        }
                    }
                }
        );

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
