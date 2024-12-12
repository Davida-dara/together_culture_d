package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


import static org.example.ExistingUser.userExistingEmail;
import static org.example.SignupInterface.openWelcomePage;
import static org.example.SignupInterface.setErrorText;

public class LoginInterface {
        private static String userEmail = "";
        private static String userPassword = "";
        private static String username = "";

    public static void showLogIn(){



            try{
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    // the main panel that will have all the different panels with components
                    JPanel mainPanel = new JPanel(new BorderLayout()){
                            //code to get the file path of background image
                            String imgPath1 = Paths.get("src/main/java/org/example/", "background.png").toString();

                            Image backgroundImage = ImageIO.read(new File(imgPath1));
                            @Override
                            public void paintComponent(Graphics b) {
                                    super.paintComponent(b);
                                    // Draw the background image.
                                    b.drawImage(backgroundImage, 0, 0, this);
                            }

                    };


                    //Panel for the logo
                    JPanel logoPanel = new JPanel();
                    logoPanel.setPreferredSize(new Dimension(50,200));
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
                    mainPanel.add(logoPanel,BorderLayout.NORTH);
                    //wrapper panel for the log in panel so that it doesnt fill the bottom section of the screen
                    JPanel centerWrapper = new JPanel(new GridBagLayout());
                    centerWrapper.setOpaque(false); //makes the wrapper transparent
                    mainPanel.add(centerWrapper, BorderLayout.CENTER);
                    //log in panel
                    JPanel logInPanel = new JPanel(new GridBagLayout()){
                            @Override
                            protected void paintComponent(Graphics g){
                                    Graphics2D g2 = (Graphics2D) g.create();
                                    //enable anti-aliasing for smoother edges
                                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                            RenderingHints.VALUE_ANTIALIAS_ON);
                                    //set the background color
                                    g2.setColor(new Color(241, 163, 163));
                                    //draw a filled rounded rectangle for the background
                                    g2.fillRoundRect(0,0,getWidth()-1, getHeight()-1,30,30);
                                    g2.dispose();
                            }
                    };
                    logInPanel.setPreferredSize(new Dimension(400,400));
                    logInPanel.setOpaque(false);//makes the panel transparent so rounded corners are visible
                    //setting the colour of the panel
                    logInPanel.setBackground(new Color(241, 163, 163));
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    //that is the title label for the log in panel
                    JLabel loginLabel = new JLabel("<html>Welcome to <br><font color='#481326'> Together Culture</font> <br><font color ='#ECD7DF'> " +
                                                    "Cambridge</font</html>");
                    loginLabel.setFont(new Font("inter", Font.PLAIN, 25));
                    logInPanel.add(loginLabel, gbc);
                    gbc.gridy++;
                    //adding the padding for the border and the border color
                    Border paddingBorder = BorderFactory.createEmptyBorder(5,20,5,20);
                    //line border with desired colour
                    Border lineBorder = BorderFactory.createLineBorder(new Color(72, 19, 38));
                    //adding te username text-field
                    JTextField usernameField = new JTextField(15);
                    usernameField.setPreferredSize(new Dimension(250, 20));
                    //setting the text-field colour
                    usernameField.setBackground(Color.WHITE);
                    //setting the placeholder text
                    usernameField.setText("Enter username");
                    //setting the text colour for the placeholder
                    usernameField.setForeground(Color.GRAY);
                    addBorder(usernameField);
                    usernameField.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
                    //clearing the textfield from default text when the user clicks on it
                    usernameField.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                    //clear the text field only if the textfield is still empty
                                    if(usernameField.getText().equals("Enter username")){
                                            usernameField.setText("");//clearing the text
                                            usernameField.setForeground(Color.BLACK);

                                    }

                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                    //if the text field is empty it will reset the text
                                    if(usernameField.getText().trim().isEmpty()){
                                            usernameField.setText("Enter username");
                                            usernameField.setForeground(Color.GRAY);
                                            username = usernameField.getText().trim();
                                    }

                            }
                    });
                    //creating the error message label for the username field
                    //adding the email error messages
                    JLabel usernameError = new JLabel("");
                    usernameError.setForeground(new Color(logInPanel.getBackground().getRed(), logInPanel.getBackground().getGreen(), logInPanel.getBackground().getBlue(),0));
                    usernameError.setHorizontalAlignment(SwingConstants.CENTER);
                    logInPanel.add(usernameError, gbc);
                    gbc.gridy++;



                    //textfield for the user to enter email
                    JTextField emailField = new JTextField(15);
                    //setting the size of the textfield
                    emailField.setPreferredSize(new Dimension(250, 25));
                    //setting the text field colour
                    emailField.setBackground(Color.WHITE);
                    emailField.setForeground(Color.BLACK);
                    emailField.setForeground(Color.GRAY);
                    //default text for password verification
                    emailField.setText("Enter your email");
                    addBorder(emailField);
                    //adding the padding for the text in the textField to be moved to right
                    emailField.setBorder(BorderFactory.createCompoundBorder(lineBorder,paddingBorder));
                    //clearing the textfield from default text when the user clicks on the textfield
                    emailField.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                    //clear the text field only if the textfield is still empty
                                   if(emailField.getText().equals("Enter your email")){
                                           emailField.setText("");//clearing the text
                                           emailField.setForeground(Color.BLACK);

                                   }

                            }
                            @Override
                            public void focusLost(FocusEvent e) {
                                    //if the text field is empty it will reset the text
                                    if(emailField.getText().trim().isEmpty()){
                                            emailField.setText("Enter your email");
                                            emailField.setForeground(Color.GRAY);
                                            userEmail = emailField.getText().trim();
                                    }

                            }
                    });
                    logInPanel.add(emailField, gbc);
                    gbc.gridy++;

                    //adding the email error messages
                    JLabel emailError = new JLabel("");
                    emailError.setForeground(new Color(logInPanel.getBackground().getRed(), logInPanel.getBackground().getGreen(), logInPanel.getBackground().getBlue(),0));
                    emailError.setHorizontalAlignment(SwingConstants.CENTER);
                    logInPanel.add(emailError, gbc);
                    gbc.gridy++;

                    // text field for the user to enter their log in password
                    JPasswordField passwordField = new JPasswordField(15);
                    //setting the textfield size
                    passwordField.setPreferredSize(new Dimension(250,25));
                    //setting the background color and the text color
                    passwordField.setBackground(Color.WHITE);
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Enter your password");
                    passwordField.setEchoChar((char)0);
                    //setting the border color change for the border
                    addBorder(passwordField);
                    passwordField.setBorder(BorderFactory.createCompoundBorder(lineBorder,paddingBorder));

                    //adding the focus listener to add and remove default text in the textfield when user clicks on the field
                    passwordField.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                    //text will disappear
                                    passwordField.getText().equals("Enter your password");
                                    passwordField.setText(""); // clearing the text field
                                    passwordField.setForeground(Color.BLACK);
                                    passwordField.setEchoChar('*'); // Enable password masking
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                    //if the textfield is empty it will reset otherwise will keep the
                                    //user's text
                                    if(passwordField.getText().trim().isEmpty()){
                                            //resets to placeholder text
                                            passwordField.setText("Enter your password");
                                            passwordField.setForeground(Color.GRAY);
                                            passwordField.setEchoChar((char)0);
                                            userPassword = passwordField.getText().trim();
                                    }

                            }
                    });
                    logInPanel.add(passwordField, gbc);
                    gbc.gridy++;
                    //forgot password button
                    JLabel forgotPassword = new JLabel("Forgot Password");
                    forgotPassword.setFont(new Font("inter", Font.BOLD, 10));
                    gbc.anchor = GridBagConstraints.NORTHEAST;
                    logInPanel.add(forgotPassword, gbc);
                    gbc.gridy++;
                    forgotPassword.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                    openRestPassword();
                            }

                            @Override
                            public void mouseEntered(MouseEvent e){
                                    //when hovered over it will change
                                    forgotPassword.setForeground(new Color(235, 64, 52));

                            }
                            @Override
                            public void mouseExited(MouseEvent e){
                                    //when mouse not hovered over it
                                    forgotPassword.setForeground(Color.BLACK);
                            }
                    });



                    //password error field
                    JLabel passwordError = new JLabel("");
                    passwordError.setForeground(new Color(logInPanel.getBackground().getRed(), logInPanel.getBackground().getGreen(), logInPanel.getBackground().getBlue(),0));
                    passwordError.setHorizontalAlignment(SwingConstants.CENTER);
                    logInPanel.add(passwordError, gbc);
                    gbc.gridy++;

                    //creating the log in button
                    MainInterface.RoundedButton logInButton = new MainInterface.RoundedButton("Log in");
                    logInButton.setBackground(new Color(72, 19, 38));
                    logInButton.setForeground(Color.WHITE);
                    logInButton.setPreferredSize(new Dimension(200,30));
                    logInButton.setFocusPainted(false); //removes the focus border
                    gbc.anchor = GridBagConstraints.CENTER;
                    logInPanel.add(logInButton,gbc);
                    logInButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    //calling the handle login method
                                    handleLogin(usernameField,usernameError, emailField, passwordField, emailError, passwordError, logInPanel);
                            }
                    });
                    gbc.gridy++;
                    JLabel backLabel = new JLabel("Go Back");
                    Font labelFont = new Font("inter", Font.PLAIN, 10);
                    backLabel.setFont(labelFont);
                    gbc.anchor = GridBagConstraints.CENTER;
                    logInPanel.add(backLabel, gbc);
                    gbc.gridy++;
                    backLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {

                                    openWelcomePage();
                            }

                            @Override
                            public void mouseEntered(MouseEvent e){
                                    //when mouse is hovered over the text it will change colour
                                    backLabel.setForeground(Color.WHITE);
                            }
                            @Override
                            public void mouseExited(MouseEvent e){
                                    //when mouse is not over the text
                                    backLabel.setForeground(Color.BLACK);
                            }
                    });

                    // Defer enabling focus until after the UI is fully initialized
                    SwingUtilities.invokeLater(() -> {
                            emailField.setFocusable(true);
                            passwordField.setFocusable(true);
                            usernameField.setFocusable(true);
                            logInPanel.requestFocusInWindow(); // Redirect focus away from the text field
                    });
                    centerWrapper.add(logInPanel);
                    //adding the panel to the frame
                    frame.add(mainPanel);
                    //making the frame visible
                    frame.pack();
                    //setting the frame dimensions
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    mainPanel.setSize(screenSize.width, screenSize.height);
                    frame.setSize(screenSize.width, screenSize.height);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
            }catch (IOException e){
                    e.printStackTrace();
            }



    }
    public static boolean handleLogin(JTextField usernameField, JLabel usernameError, JTextField emailField, JTextField passwordField, JLabel emailError, JLabel passwordError, JPanel panel){
            ExistingUser existingUser = new ExistingUser();
            ArrayList<HashMap<String, String>> users = UserInput.readingFile();
            //username checks
            String userNameInput = usernameField.getText().trim();
            boolean isUserNamePlaceHolder = userNameInput.equals("Enter username");
            boolean isNameEmpty = userNameInput.isEmpty();
            boolean isExistingUsername = existingUser.existingUsername(userNameInput);
            boolean isValidUserName = NewUser.isValidUsername(userNameInput);
            String userNameErrorMessage = "";
            if(isUserNamePlaceHolder || isNameEmpty){
                    //checks if the user-name text field is empty or is the placeholder text
                    //if true outputs the following error message
                  userNameErrorMessage = "Username required";
            }else if(!isExistingUsername){
                    //checks if the username doesn't exist in the user text file
                    // if true outputs the following error message
                    userNameErrorMessage = "Username not recognised";
            }else if (!isValidUserName){
                    //checks if the username follows the requirements
                    //if it false oputputs the following error
                    userNameErrorMessage = "Invalid username";

            }
            setErrorText(!isUserNamePlaceHolder && !isNameEmpty && isExistingUsername && isValidUserName, usernameError, userNameErrorMessage,panel);

            //email checks
            String userEmailInput = emailField.getText().trim();
            boolean userEmailPlaceHolder = userEmailInput.equals("Enter your email");
            boolean isEmailEmpty = userEmailInput.isEmpty();
            boolean existingEmailCheck = userExistingEmail(userEmailInput);
            boolean isValidEmailCheck = NewUser.isValidEmail(userEmailInput);
            String emailErrorMessage = "";
            //checks if the email field is empty or it is the place-holder text
            if(userEmailPlaceHolder || isEmailEmpty){
                    //error message
                    emailErrorMessage = "Email Required";
            }
            //if the email does not have all the characteristics
            else if(!isValidEmailCheck){
                    //if the email does not pass the email requirements an error will be outputted
                    emailErrorMessage = "Invalid email";
            } else if(!existingEmailCheck){
                           //checks if thr email exists in the db if not outputs an error
                            emailErrorMessage = "Email not recognised";
            }

            setErrorText(!userEmailPlaceHolder && !isEmailEmpty && existingEmailCheck && isValidEmailCheck , emailError, emailErrorMessage, panel);

            String passwordErrorMessage = "";
            String userPasswordInput = passwordField.getText().trim();
            boolean userPasswordPlaceHolder = userPasswordInput.equals("Enter your password");
            boolean isPasswordEmpty = userPasswordInput.isEmpty();
            boolean existingPasswordCheck = existingUser.userExistingPswd(users,userNameInput, userPasswordInput, userEmailInput);
            boolean isValidPasswordCheck = NewUser.isValidEmail(userPasswordInput);
            //checks if the password field is empty or is the place-holder text
            if (userPasswordPlaceHolder || isPasswordEmpty){
                    passwordErrorMessage = "Password required";

            }
            if (userPasswordPlaceHolder || isPasswordEmpty) {
                    passwordErrorMessage = "Password is required.";
            } else if (!isValidPasswordCheck) {
                    passwordErrorMessage = "Invalid password.";
            } else if (!existingPasswordCheck) {
                    passwordErrorMessage = "Password not recognised.";
            }
                    setErrorText(!userPasswordPlaceHolder && !isPasswordEmpty && existingPasswordCheck && isValidPasswordCheck, passwordError, passwordErrorMessage, panel);

      return false;
    }

        static void addBorder(JTextField textField){
            Border defaultPadding = BorderFactory.createEmptyBorder(5,20,5,20);
            Border defaultColour = BorderFactory.createLineBorder(Color.GRAY);
            //when the user clicks and unclicks the textfield the border colour will change
            textField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                            //change the colour when focus is gained
                            textField.setBorder(BorderFactory.createLineBorder(new Color(72, 19, 38)));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                            //change colour to default when focus is lost
                            textField.setBorder(BorderFactory.createCompoundBorder(defaultColour, defaultPadding));

                    }
            });

    }

    static void openRestPassword(){
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.showRP();
    }



}
