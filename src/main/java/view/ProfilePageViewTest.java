package view;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.Assert.*;

public class ProfilePageViewTest {

    private ProfilePageView profilePageView;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            profilePageView = new ProfilePageView();
        });
    }

    @Test
    public void testProfilePageComponents() {
        assertNotNull(profilePageView);

        JTextField usernameField = findComponent(profilePageView, JTextField.class, "usernameField");
        JTextField walletField = findComponent(profilePageView, JTextField.class, "walletField");
        JButton saveButton = findComponent(profilePageView, JButton.class, "saveButton");

        assertNotNull(usernameField);
        assertNotNull(walletField);
        assertNotNull(saveButton);
        assertEquals("Save", saveButton.getText());
    }

    @Test
    public void testSaveProfile() {
        assertNotNull(profilePageView);

        JTextField usernameField = findComponent(profilePageView, JTextField.class, "usernameField");
        JTextField walletField = findComponent(profilePageView, JTextField.class, "walletField");
        JButton saveButton = findComponent(profilePageView, JButton.class, "saveButton");

        assertNotNull(usernameField);
        assertNotNull(walletField);
        assertNotNull(saveButton);

        // Set some values
        usernameField.setText("testUser");
        walletField.setText("100");

        // Trigger save action
        saveButton.doClick();

        // Verify if a JOptionPane is displayed
        Window[] windows = Window.getWindows();
        boolean dialogFound = false;
        for (Window window : windows) {
            if (window instanceof JDialog) {
                dialogFound = true;
                break;
            }
        }
        assertTrue(dialogFound);
    }

    private <T> T findComponent(Container container, Class<T> type, String name) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (type.isAssignableFrom(component.getClass()) && name.equals(component.getName())) {
                return type.cast(component);
            } else if (component instanceof Container) {
                T result = findComponent((Container) component, type, name);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
