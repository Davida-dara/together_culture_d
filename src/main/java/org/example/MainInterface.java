package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

//import static org.example.LoggedInChat.username;

public class MainInterface {
    static class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            //makes the background transparent
            setContentAreaFilled(false);
            setBorderPainted(false);// Ensures no border is painted by default
            setOpaque(false);// Ensures full transparency
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Enable anti-aliasing for smoother corners
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Check if the button is pressed and set the color accordingly
            if (getModel().isArmed()) {
                g2.setColor(getBackground().darker());
            } else {
                g2.setColor(getBackground());
            }

            // Draw a rounded rectangle with specified corner arcs
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);  // 30 is the arc width/height

            // Set the color for the text
            g2.setColor(getForeground());

            // Center the text within the button
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            //g2.drawString(getText(), x, y);

            // Dispose of the graphics object
            g2.dispose();

            super.paintComponent(g);
        }
    }

    public static void showGUI() {
        try {
            LoggedInChat testing = new LoggedInChat();
            //this is the main frame for the interface
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //JPanel mainPanel

            //this panel will have all the components for the page
            JPanel mainPanel = new JPanel(new GridBagLayout()) {
                //get path of bckgrd img
                String imgPath = Paths.get("src/main/java/org/example/", "background.png").toString();
                Image backgroundImage = ImageIO.read(new File(imgPath));

                //overriding the default background color
                @Override
                public void paintComponent(Graphics b) {
                    super.paintComponent(b);
                    // Draw the background image.
                    b.drawImage(backgroundImage, 0, 0, this);
                }
            };
            //panel that will have the logo which is a link to the website
            JPanel logoPanel = new JPanel();
            //making the logo panel transparent
            logoPanel.setBackground(new Color(mainPanel.getBackground().getRed(), mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
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
            GridBagConstraints logoGBC = new GridBagConstraints();
            logoGBC.gridx = 0; //column 0
            logoGBC.gridy = 0;
            logoGBC.anchor = GridBagConstraints.FIRST_LINE_START; //top left corner
            logoGBC.insets = new Insets(10,10,0,0);//adding the padding to the top and left bottom and right
            logoGBC.weightx = 0; // ensure no extra horizontal space
            logoGBC.weighty = 0; // ensures no extra vertical space
            mainPanel.add(logoPanel, logoGBC);

            JPanel welcomePanel = new JPanel (new GridBagLayout()){
               @Override
               protected void paintComponent(Graphics g) {
                   super.paintComponent(g);
                   Graphics2D g2 = (Graphics2D) g.create();

                   //enable anti-aliasing for smoother edges
                   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                   //set the background color
                   g2.setColor(new Color (241, 163, 163));
                   // draw a filled rounded rectangle for the background
                   g2.fillRoundRect(0,0,getWidth() - 1, getHeight() - 1, 30 , 30);
                   g2.dispose();
               }
            };
            welcomePanel.setPreferredSize(new Dimension(400,400));
           welcomePanel.setOpaque(false); //makes the panel transparent so rounded corners are visible

           //setting the background colour of the panel
            welcomePanel.setBackground(new Color(241, 163, 163));
            GridBagConstraints gbcTitle = new GridBagConstraints();
            gbcTitle.insets = new Insets(5,5,6,5);
            gbcTitle.gridx = 0;
            gbcTitle.gridy = 0;
            gbcTitle.anchor =  GridBagConstraints.CENTER;
            //creating the title for the panel
            JLabel title = new JLabel("Welcome");
            title.setForeground(Color.BLACK);
            Font font =  new Font("Intel", Font.BOLD, 30);
            title.setFont(font);
            welcomePanel.add(title,gbcTitle);
            gbcTitle.gridy++;

            //creating a string for the text
            String text = "Sign up or log in " +
                    "to save chat data and have access" +
                    " to previous chats.";
            //creating the text area that will store the text String
            JTextArea intro = new JTextArea(text);
            intro.setEditable(false);// non-editable
            //enabling line wrap and word wrap
            intro.setLineWrap(true);
            intro.setWrapStyleWord(true);
            //set the preferred size to control the wrapping width and height
            intro.setPreferredSize(new Dimension(200,70));
            //intro.setLineWrap(true); // to make it a paragraph
            intro.setFont(new Font ("intel", Font.PLAIN, 15));
            //setting the background colour of the textarea to transparent
            intro.setBackground(new Color(241,163,163,200));
            //setting the text color
            intro.setForeground(Color.BLACK);
            //creating a seperate Grid bag constraint for the text
            GridBagConstraints gbcIntro = new GridBagConstraints();
            gbcIntro.insets = new Insets(5,5,5,5);
            gbcIntro.gridx = 0;
            gbcIntro.gridy = 1;
            gbcIntro.anchor = GridBagConstraints.CENTER;
            welcomePanel.add(intro, gbcIntro);

            //creating the login button
//            RoundedButton logIn = new RoundedButton("Log in");
//            logIn.setBackground(new Color(72, 19, 38));
//            logIn.setForeground(Color.WHITE);
//            logIn.setPreferredSize(new Dimension(100, 30));
            RoundedButton logIn = createRoundedButton("Log in", new Color(72,19,38), Color.WHITE, new Dimension (100,30));
            //creating a new layout for thr log in button so I have independent control over its position
            GridBagConstraints gbcLogin = new GridBagConstraints();
            gbcLogin.insets = new Insets(5,5,5,5);
            gbcLogin.gridx = 0;
            gbcLogin.gridy = 2;
            gbcLogin.anchor = GridBagConstraints.CENTER;
            welcomePanel.add(logIn, gbcLogin);
            //opening the log-in page
            logIn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //opens the log in page
                    openLogIn();
                }
            });
            //creating the signup button
//            RoundedButton signUp = new RoundedButton("Sign up");
//            signUp.setBackground(new Color(252, 242, 242));
//            signUp.setForeground(Color.BLACK);
//            signUp.setPreferredSize(new Dimension(100, 30));
            RoundedButton signUp = createRoundedButton("Sign up", new Color(252, 242, 242), Color.BLACK, new Dimension(100,30));
            //creating a new layout for the signup to have full control over its position
            GridBagConstraints gbcSignUp = new GridBagConstraints();
            gbcSignUp.gridx = 0;
            gbcSignUp.gridy = 3;
            gbcSignUp.anchor = GridBagConstraints.CENTER;
            welcomePanel.add(signUp, gbcSignUp);
            //adding the mouse listenr so when the user presses the button it will take them to the Signup page
            signUp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //opens the sign up page
                    openSignUp();
                }
            });
            //adding the stayed signed out option
            JLabel signedOut = new JLabel("<html><u>Stay Signed out...</u></html>");
            signedOut.setFont(new Font("intel", Font.PLAIN, 10));
            signedOut.setForeground(Color.BLACK);
            GridBagConstraints gbcSignedOut = new GridBagConstraints();
            gbcSignedOut.insets = new Insets(10,5,5,5);
            gbcSignedOut.gridx = 0;
            gbcSignedOut.gridy = 4;
            gbcSignedOut.anchor = GridBagConstraints.CENTER;
            welcomePanel.add(signedOut, gbcSignedOut);
            //adding mouse listener to change its colour when you hiver over it
            signedOut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openLoggedOut();

                }
                @Override
                public void mouseEntered(MouseEvent e){
                    //mouse hovered over it will turn to white
                    signedOut.setForeground(Color.WHITE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    //mouse not hovered over text
                    signedOut.setForeground(Color.BLACK);
                }
            });
            //add welcome panel to the main panel and center it
            GridBagConstraints mainGBC = new GridBagConstraints();
            mainGBC.gridx = 0;// sam ecolumn as logo panel
            mainGBC.gridy = 1;//below logo panel
            mainGBC.anchor = GridBagConstraints.CENTER;
            mainGBC.insets = new Insets(20,0,0,0);
            mainGBC.weightx = 1;//allowing resizing horizontally
            mainGBC.weighty = 1;//allowing resizing vertically
            mainPanel.add(welcomePanel, mainGBC);


            //adding the main panel to the frame that has all the other components
            frame.add(mainPanel);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mainPanel.setSize(screenSize.width, screenSize.height);
            //making the frame visible
            frame.pack();
            //setting the frame dimensions
            frame.setSize(screenSize.width, screenSize.height);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //method for styling the buttons
    static RoundedButton createRoundedButton(String text, Color bgColor, Color fgColor, Dimension size){
        RoundedButton button = new RoundedButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setPreferredSize(size);
        button.setFocusPainted(false); //removes the focus border
        return button;
    }
    public static void openLogIn() {
        LoginInterface loginPage = new LoginInterface();
        loginPage.showLogIn();
    }
    public static void openSignUp() {
        SignupInterface signupPage = new SignupInterface();
        signupPage.showSignup();
    }
    public static void openLoggedOut() {
        StayLoggedOut loggedOut = new StayLoggedOut();
        loggedOut.showStayLogOut();
    }

    //////Backend//////
}