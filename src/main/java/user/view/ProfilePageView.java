package user.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePageView extends JFrame {
    private JTextField usernameField;
    private JTextField walletField;
    private JTextField balanceField;
    private JTextField betHistoryFiel;
    private JButton saveButton;

    public ProfilePageView() {
        setTitle("Profile Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new GridLayout(3, 2));


        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel walletLabel = new JLabel("Wallet:");
        walletField = new JTextField();

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(walletLabel);
        add(walletField);
        add(saveButton);


        setVisible(true);
    }

    private void saveProfile() {
        String username = usernameField.getText();
        String wallet = walletField.getText();

        JOptionPane.showMessageDialog(this, "Profile saved successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfilePageView();
            }
        });
    }
}

