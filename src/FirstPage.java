import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class FirstPage extends JFrame {

    public FirstPage() {
        setTitle("SocialBuzz - Welcome");
        setSize(500, 350); // Slightly larger window for better visuals
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Set Layout and background color
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Add Background Image
        ImageIcon backgroundImage = new ImageIcon("images/DD.jpg"); // Make sure to add the image file in your project folder
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setBounds(0, 0, 500, 350);
        setContentPane(backgroundLabel); // Set the content pane to have the background

        // Create title label with a creative font and color
        JLabel welcomeLabel = new JLabel("Welcome to SOCIALBuzz", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30)); // A playful font style
        welcomeLabel.setForeground(new Color(000000)); // White text for contrast

        // Add a stylish button
        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.PLAIN, 18));
        enterButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        enterButton.setForeground(Color.WHITE); // White text
        enterButton.setFocusPainted(false); // Remove focus outline
        enterButton.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Border

        // Rounded corners for button
        enterButton.setContentAreaFilled(false); // Transparent background
        Border roundedBorder = BorderFactory.createLineBorder(new Color(70, 130, 180), 2);
        enterButton.setBorder(roundedBorder);
        enterButton.setOpaque(true);

        // Hover effect for button (Smooth animation on hover)
        enterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterButton.setBackground(new Color(100, 149, 237)); // Lighter blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterButton.setBackground(new Color(70, 130, 180)); // Original color
            }
        });

        // Smooth Fade-in animation for welcome label
        new Thread(() -> {
            try {
                for (float f = 0.0f; f <= 1.0f; f += 0.05f) {
                    welcomeLabel.setForeground(new Color(70, 130, 180));
                    Thread.sleep(50);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        // Action for Enter Button
        enterButton.addActionListener(e -> {
            new LoginPage(); // Go to login page
            dispose(); // Close the welcome screen
        });

        // Add components to the background panel
        add(welcomeLabel, BorderLayout.CENTER);
        add(enterButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FirstPage();
    }
}
