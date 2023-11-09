package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamblingStartupScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gambling Addicts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // title
        JLabel titleLabel = new JLabel("One Bet Away");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons
        // can also just have one button can deal with that later
        JButton button1 = new JButton("button 1");
        JButton button2 = new JButton("button 2");
        JButton exitButton = new JButton("Exit");

        // button fonts
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // // use case/action whatever

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // use case/action whatever

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });


        // panel at bottom for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(exitButton);

        // Add buttons to the panel
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
