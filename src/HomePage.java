import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class HomePage extends JFrame {

    private String userName;
    private JPanel postsPanel;
    private ArrayList<Post> posts;  // Store posts in a list

    public HomePage(String userName) {
        this.userName = userName;
        this.posts = new ArrayList<>();  // Initialize the posts list

        // Set up the JFrame
        setTitle("SocialBuzz - Home");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar (Left side navigation panel)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(250, 250, 250));
        sidebar.setPreferredSize(new Dimension(80, getHeight()));
        sidebar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Add Sidebar buttons (Home, Search, New Post, Notifications, Profile)
        JButton homeButton = createSidebarButton("Home", "Home", "images/home_icon.png");
        JButton newPostButton = createSidebarButton("New Post", "New Post", "images/new_post_icon.png");
        JButton notificationsButton = createSidebarButton("Notifications", "Notifications", "images/notifications_icon.png");
        JButton profileButton = createSidebarButton("Profile", "Profile", "images/profile_icon.png");

        // Add buttons to sidebar
        sidebar.add(homeButton);
        sidebar.add(newPostButton);
        sidebar.add(notificationsButton);
        sidebar.add(profileButton);

        // Main Panel (Content area with posts and profile section)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Welcome Label at the top of the main content panel
        JLabel welcomeLabel = new JLabel("Welcome, " + userName, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(70, 130, 180));  // Instagram Blue

        // Post Feed Panel (Grid of posts)
        postsPanel = new JPanel();
        postsPanel.setLayout(new GridLayout(0, 3, 10, 10));  // Grid layout for posts (3 columns)
        postsPanel.setBackground(Color.WHITE);

        // Adding some dummy posts for the feed
        for (int i = 0; i < 6; i++) {  // Let's add 6 sample posts
            Post post = new Post("Sample post #" + (i + 1), "images/download.jpg");
            posts.add(post);
            JPanel postPanel = createPostPanel(post);
            postsPanel.add(postPanel);
        }

        // Add the posts panel to the main content area
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(postsPanel, BorderLayout.CENTER);

        // Action Listeners for Sidebar buttons
        homeButton.addActionListener(e -> switchPanel(mainPanel));
        newPostButton.addActionListener(e -> showNewPostDialog());  // Show dialog for new post
        notificationsButton.addActionListener(e -> showNotifications());  // Show notifications
        profileButton.addActionListener(e -> {
            new ModifyProfilePage(userName);  // Open the profile modification page
            dispose();  // Close the current page (home page)
        });

        // Add Sidebar and Main Panel to the Frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Helper method to create Sidebar buttons
    private JButton createSidebarButton(String label, String fullLabel, String iconPath) {
        JButton button = new JButton(fullLabel);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(250, 250, 250));
        button.setIcon(new ImageIcon(iconPath));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(250, 40));
        return button;
    }

    // Method to switch between panels dynamically
    private void switchPanel(JPanel mainPanel) {
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Method to show New Post Dialog (with image upload and text input)
    private void showNewPostDialog() {
        // Create a dialog for posting a new post
        JDialog newPostDialog = new JDialog(this, "Create New Post", true);
        newPostDialog.setSize(400, 500);  // Increased height to accommodate new layout
        newPostDialog.setLayout(new BorderLayout());

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));  // Arrange components vertically

        // Post Image and Text
        JLabel postImageLabel = new JLabel("Choose an Image:");
        JButton imageButton = new JButton("Upload Image");
        JTextArea postTextArea = new JTextArea(5, 20);
        postTextArea.setLineWrap(true);
        postTextArea.setWrapStyleWord(true);

        // Label to display image preview
        JLabel imagePreviewLabel = new JLabel();
        imagePreviewLabel.setPreferredSize(new Dimension(300, 300));
        imagePreviewLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        String[] selectedImagePath = {null}; // To hold the image path (final to access inside the action listener)
        JButton postButton = new JButton("Post");
        postButton.setEnabled(false);  // Disable post button initially

        // ActionListener for image upload button
        imageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                selectedImagePath[0] = selectedFile.getPath();  // Store the selected image path

                // Display image preview
                ImageIcon icon = new ImageIcon(selectedFile.getPath());
                Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                imagePreviewLabel.setIcon(new ImageIcon(img));
                
                // Enable the Post button if there is an image and text
                postButton.setEnabled(!postTextArea.getText().isEmpty());
            }
        });

        // Document listener to enable the Post button if there's text in the post
        postTextArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                postButton.setEnabled(!postTextArea.getText().isEmpty() && selectedImagePath[0] != null);
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                postButton.setEnabled(!postTextArea.getText().isEmpty() && selectedImagePath[0] != null);
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                postButton.setEnabled(!postTextArea.getText().isEmpty() && selectedImagePath[0] != null);
            }
        });

        // ActionListener for the Post button
        postButton.addActionListener(e -> {
            String text = postTextArea.getText();
            if (selectedImagePath[0] != null) {
                // Create a new post with the selected image
                Post newPost = new Post(text, selectedImagePath[0]);
                posts.add(newPost);  // Add post to the list
                refreshPostsPanel();  // Refresh posts to show the new post
                newPostDialog.dispose();  // Close the dialog
                JOptionPane.showMessageDialog(this, "Post created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select an image for your post!");
            }
        });

        // Add components to dialog panel in order
        dialogPanel.add(postImageLabel);
        dialogPanel.add(imageButton);
        dialogPanel.add(imagePreviewLabel);
        dialogPanel.add(new JScrollPane(postTextArea));
        dialogPanel.add(postButton);

        // Add dialog panel to the JDialog and make it visible
        newPostDialog.add(dialogPanel, BorderLayout.CENTER);
        newPostDialog.setLocationRelativeTo(this);
        newPostDialog.setVisible(true);
    }

    // Method to refresh the posts panel after a new post
    private void refreshPostsPanel() {
        postsPanel.removeAll();  // Clear current posts
        for (Post post : posts) {
            JPanel postPanel = createPostPanel(post);
            postsPanel.add(postPanel);  // Add each post to the posts panel
        }
        revalidate();
        repaint();
    }

    // Method to create a post panel (with like, comment, share)
    private JPanel createPostPanel(Post post) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBackground(new Color(245, 245, 245));
        postPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Scale the image before adding it
        JLabel postImage = new JLabel(scaleImage(post.getImagePath(), 300, 300));
        postImage.setPreferredSize(new Dimension(300, 300));

        JTextArea postText = new JTextArea(post.getText());
        postText.setFont(new Font("Arial", Font.PLAIN, 14));
        postText.setForeground(new Color(70, 70, 70));
        postText.setEditable(false);
        postText.setWrapStyleWord(true);
        postText.setLineWrap(true);

        postPanel.add(postImage, BorderLayout.NORTH);
        postPanel.add(postText, BorderLayout.CENTER);

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton likeButton = createActionButton("Like", "images/like_icon.png", post);
        JButton commentButton = createActionButton("Comment", "images/comment_icon.png", post);
        JButton shareButton = createActionButton("Share", "images/share_icon.png", post);

        actionsPanel.add(likeButton);
        actionsPanel.add(commentButton);
        actionsPanel.add(shareButton);

        postPanel.add(actionsPanel, BorderLayout.SOUTH);

        return postPanel;
    }

    // Method to create action buttons (Like, Comment, Share)
    private JButton createActionButton(String text, String iconPath, Post post) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(245, 245, 245));
        button.setFocusable(false);
        
        button.addActionListener(e -> {
            if (text.equals("Like")) {
                // Show an alert message when the Like button is clicked
                JOptionPane.showMessageDialog(this, "You liked the post!");
            } else if (text.equals("Comment")) {
                String comment = JOptionPane.showInputDialog("Enter your comment:");
                post.addComment(comment);  // Add the comment
                refreshPostsPanel();  // Refresh posts to show the comment
            } else if (text.equals("Share")) {
                String recipient = JOptionPane.showInputDialog("Share with: ");
                JOptionPane.showMessageDialog(this, "Post shared with " + recipient);
            }
        });
        
        return button;
    }

    // Method to scale an image to a fixed size
    private ImageIcon scaleImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();  // Get the image from the icon
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);  // Return the scaled image as an ImageIcon
    }

    // Method to show notifications (for example: new post, profile update)
    private void showNotifications() {
        JOptionPane.showMessageDialog(this, "You have new notifications: \n1. New post created\n2. Profile updated");
    }

    public static void main(String[] args) {
        new HomePage("John Doe");  // Placeholder username for testing
    }
}

// Post class to represent a single post
class Post {
    private String text;
    private String imagePath;
    private int likes;
    private ArrayList<String> comments;

    public Post(String text, String imagePath) {
        this.text = text;
        this.imagePath = imagePath;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void addLike() {
        likes++;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public int getLikes() {
        return likes;
    }

    public ArrayList<String> getComments() {
        return comments;
    }
}
