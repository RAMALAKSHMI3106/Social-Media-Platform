import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("SocialBuzz - Login");
        setSize(400, 350); // Adjusted size to give some margin
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Set Layout and background color
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Create title label with a creative font and color
        JLabel loginLabel = new JLabel("Login to SocialBuzz", JLabel.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setForeground(new Color(70, 130, 180)); // SteelBlue color

        // Create main panel to hold form components, using GridBagLayout to center everything
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible placement
        formPanel.setBackground(new Color(245, 245, 245)); // Matching background color

        // Create constraints for centering components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add space between components

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        // Button to login
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        loginButton.setForeground(Color.WHITE); // White text

        // Button to register and navigate to RegisterPage
        JButton registerButton = new JButton("Don't have an account? Register here");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        registerButton.setForeground(new Color(70, 130, 180)); // SteelBlue color

        // Action for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Validation: Check if fields are empty
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both email and password.");
                    return;
                }

                String userName = isValidLogin(email, password);
                if (userName != null) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new HomePage(userName); // Pass user name to HomePage
                    dispose(); // Close the login page
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                }
            }
        });

        // Action for Register Button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPage(); // Open RegisterPage to register a new account
                dispose(); // Close the login page
            }
        });

        // Add form components to the formPanel using GridBagConstraints for centering
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);
        
        // Add Register button at the bottom (in a separate panel to be placed in the South of the frame)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the Register button
        bottomPanel.setBackground(new Color(245, 245, 245)); // Matching background color
        bottomPanel.add(registerButton);

        // Add components to the main window
        add(loginLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Check if the email and password are valid, and return the user's name if valid
    private String isValidLogin(String email, String password) {
        for (String[] user : RegisterPage.userData) {
            if (user[1].equals(email) && user[2].equals(password)) {
                return user[0]; // Return the user's name
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new LoginPage(); // Show the LoginPage
    }
}
