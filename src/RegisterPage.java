import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RegisterPage extends JFrame {

    // List to store user data (name, email, password)
    static ArrayList<String[]> userData = new ArrayList<>();

    public RegisterPage() {
        setTitle("SocialBuzz - Registration");
        setSize(400, 350); // Adjusted size to give some margin
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Set Layout and background color
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Create title label with a creative font and color
        JLabel registerLabel = new JLabel("Create a New Account", JLabel.CENTER);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        registerLabel.setForeground(new Color(70, 130, 180)); // SteelBlue color

        // Registration form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        formPanel.setBackground(new Color(245, 245, 245)); // Matching background color

        // Create constraints for centering components in GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add space between components
        gbc.anchor = GridBagConstraints.CENTER; // Center components

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        // Button to register
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        registerButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        registerButton.setForeground(Color.WHITE); // White text

        // Action for Register Button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Validation: Check if fields are empty
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                if (isEmailAlreadyRegistered(email)) {
                    JOptionPane.showMessageDialog(null, "Email is already registered. Please log in.");
                    new LoginPage();
                    dispose();  // Close the registration page
                } else {
                    // Save the user data in the ArrayList
                    userData.add(new String[]{name, email, password});
                    JOptionPane.showMessageDialog(null, "Registration Successful! Please log in.");
                    new LoginPage();
                    dispose();  // Close the registration page
                }
            }
        });

        // Add form components to the formPanel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span the button across two columns
        formPanel.add(registerButton, gbc);

        // Add components to the main window
        add(registerLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Check if the email is already registered
    private boolean isEmailAlreadyRegistered(String email) {
        for (String[] user : userData) {
            if (user[1].equals(email)) { // Check if the email already exists
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new RegisterPage(); // Show the Registration Page
    }
}
