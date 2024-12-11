package org.example;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;


import static org.example.StayLoggedOut.roundTextFieldBorder;

public class LoggedInChat {
//    private Bot bot;
//
//    private static final String BOTNAME = "super";
//    private Chat chatSession;
////    Loading AIML Files for chatbot conversations
//    public ChatBot() {
//        String botClassPath = "src/main/resources";
//        this.bot = new Bot(BOTNAME, botClassPath);
//        this.chatSession = new Chat(bot);
//        generateResponse(null);
//    }
//
//    //Testing conversation
//    public void generateResponse(String userInput) {
//        Scanner keyboard = new Scanner(System.in);
//        for (int i = 0; i < 3; i++) {
//            userInput = keyboard.nextLine();
//            // userInput = "I have a question";
//            if (this.chatSession != null) {
//                String response = chatSession.multisentenceRespond(userInput);
//                System.out.println("Bot: " + response);
//            } else System.out.println("chat is not properly initialized");
//        }
//    }
// Class to represent chat history
class Chat {
    private String name;
    private int id;

    public Chat(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        // Display name in the JComboBox
        return name;
    }
}

    // Class to represent FAQs
    class FAQ {
        private String question;
        private String answer;

        public FAQ(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public String toString() {
            // Display question in the JComboBox
            return question;
        }
    }

        // Custom renderer for rounded items
         class CustomComboBoxRenderer extends BasicComboBoxRenderer {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Customize colors and font
                if (isSelected) {
                    label.setBackground(new Color(255, 100, 100));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }

                // Make the background transparent for smoother rounded edges
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                return label;
            }
        }

        // Custom rounded border class
         class RoundedBorder extends AbstractBorder {
            private final int radius;

            public RoundedBorder(int radius) {
                this.radius = radius;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
            }

            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                insets.left = insets.right = insets.top = insets.bottom = radius / 2;
                return insets;
            }
        }


    public  void showChatBot(){


        //creating the frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //creating the main panel
        JPanel mainPanel  = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 134, 148));

        // Create a container panel for the sidePanel and logoPanel
        JPanel horizontalContainer = new JPanel(new BorderLayout());
        horizontalContainer.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        horizontalContainer.setOpaque(false);

        //creating the side panel
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBackground(new Color(252, 73, 97));
        int customWidth = 250;
        sidePanel.setPreferredSize(new Dimension(customWidth,screenSize.height));
        horizontalContainer.add(sidePanel, BorderLayout.WEST);
        //creating a panel for the image that will go inside the side panel
        JPanel newChatButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newChatButtonPanel.setOpaque(false);//no background colour
        //loading the image
        ImageIcon newChatIcon = new ImageIcon("src/main/java/org/example/newChat.png");
        Image scaledNewChatImage = newChatIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        newChatIcon = new ImageIcon(scaledNewChatImage);
        //adding the image to a label
        JLabel newChatImageLabel = new JLabel(newChatIcon);
        //adding the newChat image to the panel
        newChatButtonPanel.add(newChatImageLabel);
        //adding the image panel to the side panel
        sidePanel.add(newChatButtonPanel, BorderLayout.EAST);

        // Hide navigation button (placed independently of the side panel)
        JButton hideNavButton = new JButton();
        hideNavButton.setBorderPainted(false); // Remove button border
        hideNavButton.setContentAreaFilled(false); // Remove background
        hideNavButton.setFocusPainted(false); // Remove focus border
        hideNavButton.setOpaque(false); // Transparent background

        // Set the icon for the button
        ImageIcon hideNavImage = new ImageIcon("src/main/java/org/example/nav.png");
        Image scaledHideNavImage = hideNavImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        hideNavButton.setIcon(new ImageIcon(scaledHideNavImage));

        // Container for the hideNavButton
        JPanel hideNavButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hideNavButtonContainer.setBackground(new Color(252, 73, 97));
        hideNavButtonContainer.setOpaque(true);
        hideNavButtonContainer.add(hideNavButton);

        // Add ActionListener to toggle the side panel
        hideNavButton.addActionListener(new ActionListener() {
            boolean isSidePanelVisible = true; // Track visibility state
            @Override
            public void actionPerformed(ActionEvent e) {
                    // Toggle state
                    isSidePanelVisible = !isSidePanelVisible;

                    // Hide or show the side panel
                    sidePanel.setVisible(isSidePanelVisible);

                    // Optionally adjust the button icon or background color
                    if (isSidePanelVisible) {
                        hideNavButton.setIcon(new ImageIcon(scaledHideNavImage)); // Use original icon
                        hideNavButtonContainer.setBackground(new Color(252, 73, 97)); // Restore background
                    } else {
                        hideNavButton.setIcon(new ImageIcon(scaledHideNavImage)); // Optionally use a "show" icon
                        hideNavButtonContainer.setBackground(new Color(mainPanel.getBackground().getRed(), mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(), 0)); // Transparent background
                    }

                    // Refresh the layout
                    frame.revalidate();
                    frame.repaint();
                }
        });

        // Add the hideNavButtonContainer directly to the main panel
        mainPanel.add(hideNavButtonContainer, BorderLayout.WEST);

        // profile page button
        JButton profileButton= new JButton();
        profileButton.setBorderPainted(false); // Remove button border
        profileButton.setContentAreaFilled(false); // Remove background
        profileButton.setFocusPainted(false); // Remove focus border
        profileButton.setOpaque(false); // Transparent background

        // Set the icon for the button
        //loading the image
        ImageIcon profileIcon =  new ImageIcon("src/main/java/org/example/profile.png");
        Image scaledImage = profileIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        profileButton.setIcon(new ImageIcon(scaledImage));

        // Container for the hideNavButton
        JPanel profileButtonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButtonContainer.setBackground(new Color(255, 134, 148));
        hideNavButtonContainer.setOpaque(true);
        profileButtonContainer.add(profileButton);
        //adding action listener to open the profile page
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProfileGUI();
            }
        });
        //adding it to the main panel
        mainPanel.add(profileButtonContainer, BorderLayout.EAST);


//            //CHAT HISTORY DROP DOWN PANEL
//        JPanel chatHistoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        chatHistoryPanel.setOpaque(false); // no background colour
//        //creating the chat history drop down
//        String[] chatHistoryOptions = {"Previous Chats", "Chat 1", "Chat 2"};
//        JComboBox<String> chatHistoryDropdown = new JComboBox<>(chatHistoryOptions);
//        chatHistoryDropdown.setPreferredSize(new Dimension(175, 70)); // Set size
//        //chatHistoryDropdown.setBackground(new Color(252, 73, 97)); // Dropdown background color
//        chatHistoryDropdown.setForeground(Color.BLACK); // Text color
//        chatHistoryDropdown.setOpaque(false); // no background colour
//
//
//         // Customize the dropdown font and alignment
//        chatHistoryDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
//        chatHistoryDropdown.setFocusable(false); // Prevent focus border on selection
//        // Add an action listener to handle dropdown selection
//        chatHistoryDropdown.addActionListener(e -> {
//            String selectedChat = (String) chatHistoryDropdown.getSelectedItem();
//            if (!selectedChat.equals("Select Chat")) {
//                JOptionPane.showMessageDialog(frame, "Loading: " + selectedChat, "Chat History", JOptionPane.INFORMATION_MESSAGE);
//
//            }
//        });
//
//// Add the dropdown to the panel
//        chatHistoryPanel.add(chatHistoryDropdown);
//
//        //FAQ DROP DOWN PANEL
//        JPanel faqPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        faqPanel.setOpaque(false);//no background colour
//        //creating the drop down
//        String[] faqOptions = {"FAQ","","",""};
//        JComboBox<String> faqDropDown = new JComboBox<>(faqOptions);
//        //setting the size
//        faqDropDown.setPreferredSize(new Dimension(175,70));
//        //setting the text colour
//        faqDropDown.setForeground(Color.BLACK);
//        faqDropDown.setOpaque(false);//no background colour
//        //customise drop down font and alignment
//        faqDropDown.setFont(new Font("Arial", Font.PLAIN, 18));
//        faqDropDown.setFocusable(false);//prevent focus border on selection
//        faqDropDown.addActionListener(e ->{
//            String selectedFAQ = (String) faqDropDown.getSelectedItem();
//            if(!selectedFAQ.equals("FAQ")){
//                JOptionPane.showMessageDialog(frame, "loading: " + selectedFAQ, "FAQ", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//        //add dropdown to the panel
//        faqPanel.add(faqDropDown);
//
//
//// Create a container panel for stacking the dropdowns vertically
//        JPanel dropdownContainer = new JPanel();
//        dropdownContainer.setOpaque(false); // Transparent background
//        dropdownContainer.setLayout(new BoxLayout(dropdownContainer, BoxLayout.Y_AXIS)); // Vertical layout
//
//// Add spacing between dropdowns and align them to the left
//        chatHistoryPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
//        faqPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
//        dropdownContainer.add(chatHistoryPanel); // Add chat history dropdown
//        dropdownContainer.add(Box.createVerticalStrut(10)); // Add spacing between dropdowns
//        dropdownContainer.add(faqPanel); // Add FAQ dropdown
//
//

//// Add the bottomContainer to the SOUTH of the side-Panel
//        sidePanel.add(bottomContainer, BorderLayout.SOUTH);

        // Chat History Dropdown
        Chat[] chatHistoryOptions = {
                new Chat("Previous Chats", -1),
                new Chat("Chat 1", 1),
                new Chat("Chat 2", 2)
        };
        JComboBox<Chat> chatHistoryDropdown = new JComboBox<>(chatHistoryOptions);
        // Apply custom renderer
        chatHistoryDropdown.setRenderer(new CustomComboBoxRenderer());

        // Apply rounded border
        chatHistoryDropdown.setBorder(new RoundedBorder(15)); // Adjust radius as needed

        chatHistoryDropdown.setPreferredSize(new Dimension(175, 70));
        chatHistoryDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        chatHistoryDropdown.setFocusable(false);
        chatHistoryDropdown.addActionListener(e -> {
            Chat selectedChat = (Chat) chatHistoryDropdown.getSelectedItem();
            if (selectedChat != null && selectedChat.getId() != -1) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Loading chat: " + selectedChat.getName(),
                        "Chat History",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JPanel chatHistoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chatHistoryPanel.setOpaque(false);
        chatHistoryPanel.add(chatHistoryDropdown);

        // FAQ Dropdown
        FAQ[] faqOptions = {
                new FAQ("Select FAQ", ""),
                new FAQ("What is this app?", "This app helps you interact with our chatbot."),
                new FAQ("How to use the chatbot?", "Simply type your question and press Enter.")
        };
        JComboBox<FAQ> faqDropDown = new JComboBox<>(faqOptions);
        // Apply custom renderer
        faqDropDown.setRenderer(new CustomComboBoxRenderer());

        // Apply rounded border
        faqDropDown.setBorder(new RoundedBorder(15)); // Adjust radius as needed
        faqDropDown.setPreferredSize(new Dimension(175, 70));
        faqDropDown.setFont(new Font("Arial", Font.PLAIN, 18));
        faqDropDown.setFocusable(false);
        faqDropDown.addActionListener(e -> {
            FAQ selectedFAQ = (FAQ) faqDropDown.getSelectedItem();
            if (selectedFAQ != null && !selectedFAQ.getQuestion().equals("Select FAQ")) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Answer: " + selectedFAQ.getAnswer(),
                        "FAQ",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JPanel faqPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        faqPanel.setOpaque(false);
        faqPanel.add(faqDropDown);

        // Combine dropdowns
        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setOpaque(false);
        dropdownContainer.setLayout(new BoxLayout(dropdownContainer, BoxLayout.Y_AXIS));
        chatHistoryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        faqPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dropdownContainer.add(chatHistoryPanel);
        dropdownContainer.add(Box.createVerticalStrut(10));
        dropdownContainer.add(faqPanel);

       // Create a container panel for bottom alignment
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setOpaque(false); // Transparent background
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0)); // Adjust padding to raise position
        bottomContainer.add(dropdownContainer, BorderLayout.WEST); // Add dropdown container to bottom

        bottomContainer.add(dropdownContainer, BorderLayout.WEST);
        sidePanel.add(bottomContainer, BorderLayout.SOUTH);


        //adding the contentContianer with a box layout so that we can add all the different panels that have the  different components fo the page
        //this will allow us to have all the components showing on the same page
        // Create a panel to hold the logoPanel and componentPanel
        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setOpaque(false);




        //component panel
        //creating a panel that will have the different components
        JPanel textFieldPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//columnn index (centered horizontally)
        gbc.gridy = 0;//row index (first row)
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.NONE; //do not stretch the component
        gbc.anchor = GridBagConstraints.CENTER; //center the component
        textFieldPanel.setOpaque(false);


        //adding the logo panel to the page
        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(50,220));
        logoPanel.setBackground(new Color(mainPanel.getBackground().getRed(), mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //create a JLabel for the text
        //using HTML for line breaks and colours
        JLabel logoLabel = new JLabel("<html><a href = ''><span style='text-decoration:none;'<font color='#481326'>Together<br>Culture<br></font><font color ='#ECD7DF'>Cambridge</font></a></span></html>");
        logoLabel.setFont(new Font("inter",Font.BOLD, 55));
        logoPanel.add(logoLabel);
        //adding a mouse listener to open a URL when the press on the label
        //add the logo panel to the top left corner of the  main panel
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    Desktop.getDesktop().browse(new URI("https://www.togetherculture.com"));
                }catch(IOException | java.net.URISyntaxException ex){
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e){
                logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));// change cursor to hand
            }
        });
        contentContainer.add(logoPanel);
        // Add spacing between the logoPanel and the text field
        contentContainer.add(Box.createVerticalStrut(20));



        //Creating the text-field where the user will initially type in there question to the chatbot
        org.example.RoundedTextField textEntryField = new org.example.RoundedTextField(40,20);
        //setting the preferred size
        textEntryField.setPreferredSize(new Dimension(250,40));
        //setting the text-field background to transparent
        textEntryField.setBackground(new Color(mainPanel.getBackground().getRed(), mainPanel.getBackground().getGreen(),
                mainPanel.getBackground().getBlue(),0));
        //setting placeholder text and initial styles
        textEntryField.setText("Type a question...");
        textEntryField.setForeground(Color.WHITE);
        //Apply the initial white border
        Border paddingBorder = BorderFactory.createEmptyBorder(10,20,10,20);
        Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);

        //calling the addBorder method and parsing the text entry field to it
        roundTextFieldBorder(textEntryField);
        //adding the padding for the text int he textfield to be moved to the right
        textEntryField.setBorder(BorderFactory.createCompoundBorder(whiteBorder, paddingBorder));
        textEntryField.setFocusable(false);//disbale focus initially

        //clearing the textfield from the default text when the user presses on the textfield
        textEntryField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //clear the textfield only if the textfield is till empty (displaying default text)
                if(textEntryField.getText().equals("Type a question...")){
                    textEntryField.setText("");//clearing the text
                    textEntryField.setForeground(Color.BLACK);//making the text colour black
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //if the textfield is still empty then the text will stay in grey
                //it won't empty the textfield
                if(textEntryField.getText().trim().isEmpty()){
                    textEntryField.setText("Type a question...");
                    textEntryField.setForeground(Color.WHITE);
                }

            }
        });
        //prevent the 'textEntryField' from being focused initially
        textEntryField.setFocusable(false);
        // Defer enabling focus until after the UI is fully initialized
        SwingUtilities.invokeLater(() -> {
            textEntryField.setFocusable(true);
            mainPanel.requestFocusInWindow(); // Redirect focus away from the text field
        });


// Define GridBagConstraints for the text field
        gbc.gridx = 0;  // Center column
        gbc.gridy = GridBagConstraints.RELATIVE;  // Relative position
        gbc.weightx = 1.0;  // Allow horizontal stretching
        gbc.weighty = 1.0;  // Push the text field down
        gbc.anchor = GridBagConstraints.SOUTH;  // Align at the bottom
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        gbc.insets = new Insets(10, 20, 20, 20);  // Add padding
        //adding textfield to componentPanel using gridbag
        textFieldPanel.add(textEntryField, gbc);
        gbc.gridy++;
        // Add the textFieldPanel below the logoPanel
        contentContainer.add(textFieldPanel);
        // Add contentContainer to the CENTER of horizontalContainer
        horizontalContainer.add(contentContainer, BorderLayout.CENTER);

        //adding the component panel to the mainpanel
        mainPanel.add(horizontalContainer, BorderLayout.CENTER);

        //adding the panel to the frame
        frame.add(mainPanel);
        //making the frame visible
        frame.pack();
        //setting the frame dimensions

        mainPanel.setSize(screenSize.width, screenSize.height);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void openProfileGUI(){
    Profile showProfile = new Profile();
    showProfile.showProfile();
    }
}
