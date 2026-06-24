	import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyProfilePage extends JFrame {

    private String userName;

    public ModifyProfilePage(String userName) {
        this.userName = userName;

        setTitle("Modify Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Adding space between components

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(userName, 20);
        usernameField.setEditable(true);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField("user@example.com", 20);  // Example email
        emailField.setEditable(true);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField("password123", 20);

        // Edit button
        JButton editButton = new JButton("Edit Profile");
        editButton.addActionListener(e -> {
            // Here we would save the changes (for now, just a dummy message)
            String updatedUsername = usernameField.getText();
            String updatedEmail = emailField.getText();
            char[] updatedPassword = passwordField.getPassword();

            // Show a message with the updated details (this could be replaced with actual saving logic)
            JOptionPane.showMessageDialog(this, "Profile updated!\nUsername: " + updatedUsername + "\nEmail: " + updatedEmail);

            // After updating, go back to HomePage
            new HomePage(updatedUsername);  // Pass the updated username
            dispose();  // Close the ModifyProfilePage
        });

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            // Close the application
            System.exit(0);
        });

        // Adding components to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  // Make the button span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(editButton, gbc);

        gbc.gridy = 4;
        mainPanel.add(logoutButton, gbc);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new ModifyProfilePage("John Doe");
    }
}
