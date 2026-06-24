import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PostPage extends JFrame {

    private String[] userData; // Array to hold user data (name, email, password)
    private ArrayList<String> posts; // List to store posts
    private ArrayList<String> likes; // List of users who liked the post
    private ArrayList<String> followers; // List of followers

    // Constructor for PostPage
    public PostPage(String[] userData) {
        this.userData = userData; // Receive user data (name, email, password)
        this.posts = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.followers = new ArrayList<>();
        
        setTitle("SocialBuzz - Post");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Set Layout and background color
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Title Label
        JLabel postLabel = new JLabel("Add a New Post", JLabel.CENTER);
        postLabel.setFont(new Font("Arial", Font.BOLD, 24));
        postLabel.setForeground(new Color(70, 130, 180)); // SteelBlue color

        // Text area to write a post
        JTextArea postArea = new JTextArea(5, 30);
        postArea.setWrapStyleWord(true);
        postArea.setLineWrap(true);
        postArea.setFont(new Font("Arial", Font.PLAIN, 16));
        postArea.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        JScrollPane scrollPane = new JScrollPane(postArea);

        // Button to add the post
        JButton postButton = new JButton("Post");
        postButton.setFont(new Font("Arial", Font.PLAIN, 18));
        postButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        postButton.setForeground(Color.WHITE); // White text
        postButton.setFocusPainted(false); // Remove focus outline

        // Button to Like the post
        JButton likeButton = new JButton("Like");
        likeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        likeButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        likeButton.setForeground(Color.WHITE); // White text

        // Button to Follow the user
        JButton followButton = new JButton("Follow");
        followButton.setFont(new Font("Arial", Font.PLAIN, 18));
        followButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        followButton.setForeground(Color.WHITE); // White text

        // Action for Post Button
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postContent = postArea.getText();
                if (!postContent.isEmpty()) {
                    posts.add(postContent);
                    JOptionPane.showMessageDialog(null, "Post added successfully!");
                    postArea.setText(""); // Clear the text area after posting
                } else {
                    JOptionPane.showMessageDialog(null, "Please write something before posting.");
                }
            }
        });

        // Action for Like Button
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the current user to the likes list if not already liked
                if (!likes.contains(userData[0])) {
                    likes.add(userData[0]);
                    JOptionPane.showMessageDialog(null, "You liked the post.");
                } else {
                    JOptionPane.showMessageDialog(null, "You already liked this post.");
                }
            }
        });

        // Action for Follow Button
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the current user to the followers list if not already following
                if (!followers.contains(userData[0])) {
                    followers.add(userData[0]);
                    JOptionPane.showMessageDialog(null, "You are now following the user.");
                } else {
                    JOptionPane.showMessageDialog(null, "You are already following this user.");
                }
            }
        });

        // Layout the components
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(likeButton);
        buttonPanel.add(followButton);
        buttonPanel.add(postButton);

        // Main Layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(postLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PostPage(new String[]{"John Doe", "john@example.com", "password123"}); // For testing
    }
}
