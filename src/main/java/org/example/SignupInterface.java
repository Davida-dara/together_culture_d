package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import org.example.LoginInterface;

import static org.example.ExistingUser.userExistingEmail;
import static org.example.LoginInterface.*;
import static org.example.NewUser.isValidEmail;
import static org.example.NewUser.isValidPassword;
import static org.example.UserInput.store;

public class SignupInterface {
    private static String userEmail = "";
    private static String userPassword = "";
    private static String userPasswordVerif = "";
    private static String username = "";
    public static void showSignup(){
        try{
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // the main panel will have all the different panels with components
            JPanel mainPanel = new JPanel(new BorderLayout()){
                Image backgroundImage = ImageIO.read(new File("/Users/gaiiaharb/Library/CloudStorage/OneDrive-AngliaRuskinUniversity/year2/OOP/TogetherCultureChatBot/src/main/java/org/example/background.png"));
                @Override
                public void paintComponent(Graphics b){
                    super.paintComponent(b);
                    //draw the background image
                    b.drawImage(backgroundImage,0,0,this);
                }

            };
            //panel for the logo
            JPanel logoPanel = new JPanel();
            logoPanel.setPreferredSize(new Dimension(50,200));
            logoPanel.setBackground(new Color(mainPanel.getBackground().getRed(), mainPanel.getBackground().getBlue(),mainPanel.getBackground().getBlue(),0));
            logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            //creating a JLabel for the logo
            // using HTML and css stying
            JLabel logoLabel = new JLabel("<html><a href = ''><span style='text-decoration:none;'<font color='#481326'>Together<br>Culture<br></font><font color ='#ECD7DF'>Cambridge</font></a></span></html>");
            logoLabel.setFont(new Font("inter",Font.BOLD, 55));
            logoPanel.add(logoLabel);
            //adding mouse listener
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
                    logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); //changes the courser to hand
                }
            });
            mainPanel.add(logoPanel, BorderLayout.NORTH);
            //creating a wrapper panel for the signup panel that will center the panel with all the components
            JPanel centerWrapper = new JPanel(new GridBagLayout());
            centerWrapper.setOpaque(false);//makes the wrapper panel transparent
            mainPanel.add(centerWrapper, BorderLayout.CENTER);
            //creating the Sign up panel
            JPanel signUpPanel = new JPanel(new GridBagLayout()){
                // making the panel have rounded edges
                @Override
                protected void paintComponent(Graphics g){
                    Graphics2D g2 = (Graphics2D) g.create();
                    //enable anti-aliasing for smoother edges
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    //set the background colour
                    g2.setColor(new Color(241,163,163));
                    //draw a filled rounded rectangle for the background
                    g2.fillRoundRect(0,0, getWidth()-1,getHeight()-1, 30,30);
                    g2.dispose();
                }
            };
            signUpPanel.setPreferredSize(new Dimension(500,500));
            signUpPanel.setOpaque(false);//makes the panel transparent so rounded corners are visible
            //setting the colour of the panel
            signUpPanel.setBackground(new Color(241,163,163));
            GridBagConstraints signUpPanelGBC = new GridBagConstraints();
            signUpPanelGBC.insets = new Insets(5,5,5,5);
            signUpPanelGBC.gridx = 0;
            signUpPanelGBC.gridy = 0;
            signUpPanelGBC.anchor = GridBagConstraints.CENTER;
            //label for the sign up panel
            JLabel signUpLab =  new JLabel("<html>Welcome to <br><font color='#481326'> Together Culture</font> <br><font color ='#ECD7DF'> " +
                    "Cambridge</font</html>");
            signUpLab.setFont(new Font("inter", Font.PLAIN,25));
            signUpPanel.add(signUpLab, signUpPanelGBC);
            signUpPanelGBC.gridy++;
            //adding the signUpPanel to the wrapper panel
            centerWrapper.add(signUpPanel);
            //adding the text-fields needed
            //border and padding for the text fields
            Border paddingBorder = BorderFactory.createEmptyBorder(5,20,5,20);
            //line border with desired colour
            Border lineBorder =  BorderFactory.createLineBorder(new Color(72,19,38));
            //adding the username textfield
             JTextField usernameField = new JTextField(15);
             usernameField.setPreferredSize(new Dimension(250,25));
             //setting the background color for the textfield
             usernameField.setBackground(Color.white);
             //setting the color of the text
            usernameField.setForeground(Color.BLACK);
            //setting the colour for the placeholder text
            usernameField.setText("Enter your username");
            usernameField.setForeground(Color.GRAY);

            //adding the border
            addBorder(usernameField);
            //setting the border and padding
            usernameField.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            //adding the focus listener for the text field
                usernameField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (usernameField.getText().equals("Enter your username")) {
                            usernameField.setText("");
                            usernameField.setForeground(Color.BLACK);

                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (usernameField.getText().trim().isEmpty()) {
                            usernameField.setText("Enter your username");
                            usernameField.setForeground(Color.GRAY);
                            username = usernameField.getText().trim();

                        }
                    }
                });

            //Profile.showProfile(String.valueOf(usernameField));
            signUpPanel.add(usernameField, signUpPanelGBC);
            signUpPanelGBC.gridy++;
            //creating the error message label for the username
            JLabel usernameError = new JLabel("");
            usernameError.setForeground(new Color(signUpPanel.getBackground().getRed(),
                    signUpPanel.getBackground().getGreen(),
                    signUpPanel.getBackground().getBlue(),0));
            usernameError.setHorizontalAlignment(SwingConstants.CENTER);
            signUpPanel.add(usernameError, signUpPanelGBC);
            signUpPanelGBC.gridy++;


            //email text field
            JTextField emailField = new JTextField(15);
            //setting the size of the text field
            emailField.setPreferredSize(new Dimension(250,25));
            //setting the field colour
            emailField.setBackground(Color.WHITE);
            //setting the text colour for the text field
            emailField.setForeground(Color.BLACK);
           emailField.setForeground(Color.GRAY);
//            //default text for password verification
            emailField.setText("Enter your email");
            addBorder(emailField);
            //setting the border
            emailField.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            //adding the focus listener for the text-field
            emailField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (emailField.getText().equals("Enter your email")) {
                        emailField.setText("");
                        emailField.setForeground(Color.BLACK);

                    }
                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (emailField.getText().trim().isEmpty()) {
                        emailField.setText("Enter your email");
                        emailField.setForeground(Color.GRAY);
                        userEmail = emailField.getText().trim();

                    }


                }
            });


            signUpPanel.add(emailField, signUpPanelGBC);
            signUpPanelGBC.gridy++;


            //email error message
            JLabel emailError = new JLabel("");
            emailError.setForeground(new Color(signUpPanel.getBackground().getRed(), signUpPanel.getBackground().getGreen(),signUpPanel.getBackground().getBlue(),0));
            emailError.setHorizontalAlignment(SwingConstants.CENTER);
            signUpPanel.add(emailError, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            //password text field
            JTextField passwordField = new JTextField(15);
            //set the preferred
            passwordField.setPreferredSize(new Dimension(250,25));
            //setting the text field background colour
            passwordField.setBackground(Color.WHITE);
            //setting the text colour
            passwordField.setForeground(Color.BLACK);
            passwordField.setForeground(Color.GRAY);
            //default text for password verification
            passwordField.setText("Enter your password");
            //adding the border change of colour
            addBorder(passwordField);
            //setting the border color and the padding
            passwordField.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            passwordField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //clear the text field if the text field only has the default text
                    if(passwordField.getText().equals("Enter your password")){
                        passwordField.setText("");//clearing the text
                        passwordField.setForeground(Color.BLACK);
                    }

                }

                @Override
                public void focusLost(FocusEvent e) {
                    //sets the default text only if the textfield is empty
                    if(passwordField.getText().trim().isEmpty()){
                        passwordField.setText("Enter your password");
                        passwordField.setForeground(Color.GRAY);
                        userPassword = passwordField.getText().trim();
                    }

                }
            });
            signUpPanel.add(passwordField, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            //adding the password error
            JLabel passError = new JLabel("");
            passError.setForeground(new Color(signUpPanel.getBackground().getRed(), signUpPanel.getBackground().getGreen(), signUpPanel.getBackground().getBlue(),0));
            passError.setHorizontalAlignment(SwingConstants.CENTER);
            signUpPanel.add(passError, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            // password verification text field
            JTextField passwordVerificationField = new JTextField(15);
            //set the preferred
            passwordVerificationField.setPreferredSize(new Dimension(250,25));
            //setting the text field background colour
            passwordVerificationField.setBackground(Color.WHITE);
            //setting the text colour
            passwordVerificationField.setForeground(Color.BLACK);
            //setting the text colour
            passwordVerificationField.setForeground(Color.GRAY);
            //default text for password verification
            passwordVerificationField.setText("Re-enter your password");
            //adding the border change of colour
            addBorder(passwordVerificationField);
            //setting the border color and the padding
            passwordVerificationField.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));

            passwordVerificationField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //clear the text field only if it has the default text
                    if(passwordVerificationField.getText().equals("Re-enter your password")){
                        passwordVerificationField.setText("");//clearing the text
                        passwordVerificationField.setForeground(Color.BLACK);
                    }

                }

                @Override
                public void focusLost(FocusEvent e) {
                    //sets the default text only if the text field is empty
                    if(passwordVerificationField.getText().trim().isEmpty()){
                        passwordVerificationField.setText("Re-enter your password");
                        passwordVerificationField.setForeground(Color.GRAY);
                        userPasswordVerif = passwordVerificationField.getText().trim();
                    }

                }
            });

            signUpPanel.add(passwordVerificationField, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            //adding the password errors
            JLabel passError2 = new JLabel("");
            passError2.setForeground(new Color(signUpPanel.getBackground().getRed(), signUpPanel.getBackground().getGreen(), signUpPanel.getBackground().getBlue(),0) );
            passError2.setHorizontalAlignment(SwingConstants.CENTER);
            signUpPanel.add(passError2, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            //creating the signup button
            MainInterface.RoundedButton signUpButton = new MainInterface.RoundedButton("Sign up");
            signUpButton.setBackground(new Color(252, 242, 242));
            signUpButton.setForeground(Color.BLACK);
            signUpButton.setPreferredSize(new Dimension(200, 30));
            signUpButton.setFocusPainted(false); //removes the focus border
            signUpPanel.add(signUpButton, signUpPanelGBC);
            signUpPanelGBC.gridy++;

           // adding the action listener to call the handle signup method
            signUpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //calling the handle signup method
                    handleSignup(usernameField,usernameError, emailField, emailError, passwordField, passError, passwordVerificationField, passError2, signUpButton, signUpPanel);
                }
        });

            JLabel backLabel = new JLabel("Go Back");
            Font labelFont = new Font("inter", Font.PLAIN, 10);
            backLabel.setFont(labelFont);
            signUpPanel.add(backLabel, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            backLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //go back to main page
                    openWelcomePage();

                }
                @Override
                public void mouseEntered(MouseEvent e){
                    //when hovered over colour will change
                    backLabel.setForeground(Color.WHITE);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    //when the mouse isn't hovered over
                    backLabel.setForeground(Color.BLACK);
                }
            });
            // Defer enabling focus until after the UI is fully initialized
            SwingUtilities.invokeLater(() -> {
                emailField.setFocusable(true);
                passwordField.setFocusable(true);
                passwordVerificationField.setFocusable(true);
                signUpPanel.requestFocusInWindow(); // Redirect focus away from the text field
            });



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

        }catch(IOException e){
            e.printStackTrace();
        }


    }
    public static boolean handleSignup(
            JTextField usernameField, JLabel usernameError,
            JTextField emailField, JLabel emailError,
            JTextField passwordField, JLabel passError,
            JTextField passwordVerificationField, JLabel passError2,
            JButton signupButton, JPanel signupPanel) {

        NewUser newUser = new NewUser();
        ArrayList<HashMap<String, String>> users = UserInput.readingFile();

        // Username validation
        String usernameInput = usernameField.getText().trim();
        boolean isPlaceholder = usernameInput.equals("Enter your username");
        boolean isEmpty = usernameInput.isEmpty();
        boolean validUsernameCheck = newUser.isValidUsername(usernameInput);
        String usernameErrorMessage = null;

        if (isPlaceholder || isEmpty) {
            usernameErrorMessage = "Username required";
        } else if (!validUsernameCheck) {
            usernameErrorMessage = "Invalid username";
        }
        setErrorText(!isPlaceholder && !isEmpty && validUsernameCheck, usernameError, usernameErrorMessage, signupPanel);

        // Email validation
        String userEmailInput = emailField.getText().trim();
        boolean isEmailPlaceHolder = userEmailInput.equals("Enter your email");
        boolean isEmailEmpty = userEmailInput.isEmpty();
        boolean validEmailCheck = newUser.isValidEmail(userEmailInput);
        boolean userEmailExisting = userExistingEmail(userEmailInput);
        String emailErrorMessage = null;

        if (isEmailPlaceHolder || isEmailEmpty) {
            emailErrorMessage = "Email required";
        } else if (userEmailExisting) {
            emailErrorMessage = "Email already exists";
        } else if (!validEmailCheck) {
            emailErrorMessage = "Invalid email";
        }
        setErrorText(!isEmailPlaceHolder && !isEmailEmpty && validEmailCheck && !userEmailExisting, emailError, emailErrorMessage, signupPanel);

        // Password validation
        String userPasswordInput = passwordField.getText().trim();
        boolean isPasswordPlaceHolder = userPasswordInput.equals("Enter your password");
        boolean isPasswordEmpty = userPasswordInput.isEmpty();
        boolean validPasswordCheck = newUser.isValidPassword(userPasswordInput);
        String firstPassErrorMessage = null;

        if (isPasswordPlaceHolder || isPasswordEmpty) {
            firstPassErrorMessage = "Password required";
        } else if (!validPasswordCheck) {
            firstPassErrorMessage = "Invalid password";
        }
        setErrorText(!isPasswordPlaceHolder && !isPasswordEmpty && validPasswordCheck, passError, firstPassErrorMessage, signupPanel);

        // Password verification
        String userPasswordVerifInput = passwordVerificationField.getText().trim();
        boolean isPasswordVerifPlaceHolder = userPasswordVerifInput.equals("Enter your password");
        boolean isPasswordVerifEmpty = userPasswordVerifInput.isEmpty();
        boolean validPasswordVerifCheck = newUser.isValidPassword(userPasswordVerifInput);
        String secondPassErrorMessage = null;
        boolean passwordValidation = userPasswordInput.equals(userPasswordVerifInput);

        if (isPasswordVerifPlaceHolder || isPasswordVerifEmpty) {
            secondPassErrorMessage = "Password required";
        } else if (!validPasswordVerifCheck) {
            secondPassErrorMessage = "Invalid password";
        } else if (!passwordValidation) {
            secondPassErrorMessage = "Passwords do not match";
        }
        setErrorText(!isPasswordVerifPlaceHolder && !isPasswordVerifEmpty && validPasswordVerifCheck && passwordValidation, passError2, secondPassErrorMessage, signupPanel);

        // Final validation and account creation
        if (!isPlaceholder && !isEmpty && validUsernameCheck &&
                !isEmailPlaceHolder && !isEmailEmpty && validEmailCheck && !userEmailExisting &&
                !isPasswordPlaceHolder && !isPasswordEmpty && validPasswordCheck &&
                !isPasswordVerifPlaceHolder && !isPasswordVerifEmpty && validPasswordVerifCheck && passwordValidation) {

            store(usernameInput, userEmailInput, userPasswordInput);
            signupButton.setBackground(Color.GREEN);
            signupButton.setText("Account Created");
            signupButton.setPreferredSize(new Dimension(200, 30));
            return true;
        }

        // If validation fails, return false
        return false;
    }


//    public static boolean handleSignup(JTextField usernameField,JLabel usernameError, JTextField emailField ,JLabel emailError,JTextField passwordField,  JLabel passError, JTextField passwordVerificationField, JLabel passError2,JButton signupButton,
//                                       JPanel signupPanel ) {
//
//        NewUser newUser = new NewUser();
//        ArrayList<HashMap<String, String>> users = UserInput.readingFile();
//
//
//// Check username input during validation
//        String usernameInput = usernameField.getText().trim();
//        boolean isPlaceholder = usernameInput.equals("Enter your username");
//        boolean isEmpty = usernameInput.isEmpty();
//        boolean validUsernameCheck = newUser.isValidUsername(usernameInput);
//
//        String usernameErrorMessage = null;
//
//// Validation rules
//        if (isPlaceholder || isEmpty) {
//            usernameErrorMessage = "Username required";
//        } else if (!validUsernameCheck) {
//            usernameErrorMessage = "Invalid username";
//        }
//
//// Set error message
//        setErrorText(!isPlaceholder && !isEmpty && validUsernameCheck, usernameError, usernameErrorMessage, signupPanel);
//
//        String userEmailInput = emailField.getText().trim();
//        boolean isEmailPlaceHolder = userEmailInput.equals("Enter your email");
//        boolean isEmailEmpty = userEmailInput.isEmpty();
//        boolean validEmailCheck = newUser.isValidEmail(userEmailInput);
//        boolean userEmailExisting = userExistingEmail(userEmailInput);
//
//        String emailErrorMessage = null;
//        if (isEmailPlaceHolder || isEmailEmpty) {
//            //output an error
//            emailErrorMessage = "Email required";
//        }
//        //checks if the user email entered already exists
//        if (userEmailExisting) {
//            emailErrorMessage = "Email already exists";
//        } else {
//            if (!validEmailCheck) {
//                emailErrorMessage = "Invalid email";
//            }
//        }
//        setErrorText(!isEmailPlaceHolder && !isEmailEmpty && validEmailCheck, emailError, emailErrorMessage, signupPanel);
//
//
//
//
//        String userPasswordInput = passwordField.getText().trim();
//        boolean isPasswordPlaceHolder = userPasswordInput.equals("Enter your password");
//        boolean isPasswordEmpty = userPasswordInput.isEmpty();
//        boolean validPasswordCheck = newUser.isValidPassword(userPasswordInput);
//        String firstPassErrorMessage = null;
//        // checking if the password fields are empty
//        if (isPasswordPlaceHolder || isPasswordEmpty) {
//            //checks if the password field is the place-holder text or is empty and outputs an error accordingly
//            firstPassErrorMessage = "Invalid password";
//            if (!validPasswordCheck) {
//                //checks if the password entered by the user passes the validation conditions
//                firstPassErrorMessage = "Password required";
//            }
//        }
//
//        setErrorText(!isPasswordPlaceHolder && !isPasswordEmpty && validPasswordCheck, passError, firstPassErrorMessage, signupPanel);
//        boolean passwordValidation = true;
//
//        String userPasswordVerifInput = passwordVerificationField.getText().trim();
//        boolean isPasswordVerifPlaceHolder = userPasswordVerifInput.equals("Enter your password");
//        boolean isPasswordVerifEmpty = userPasswordInput.isEmpty();
//        boolean validPasswordVerifCheck = newUser.isValidPassword(userPasswordVerifInput);
//        String secondPassErrorMessage = null;
//        if (isPasswordVerifPlaceHolder || isPasswordVerifEmpty) {
//            //checks if the field is the place-holder or id empty and outputs an error
//            secondPassErrorMessage = "Invalid password";
//        } else {
//            if (!validPasswordVerifCheck) {
//                //checks if the password passes te verification conditions set
//                secondPassErrorMessage = "Password required";
//            } else
//                // if both fields are not empty, check if they match
//                if (userPasswordInput.equals(userPasswordVerifInput)) {
//                    passwordValidation = true;
//                    //secondPassErrorMessage = "Password already exists";
//                } else {
//                    passwordValidation = false;
//                    secondPassErrorMessage = "password does not match the requirements";
//                }
//        }
//        setErrorText(!isPasswordVerifPlaceHolder && !isPasswordVerifEmpty && validPasswordVerifCheck && passwordValidation, passError2, secondPassErrorMessage,signupPanel);
//
//        //if (!isEmailPlaceHolder && !isEmailEmpty && validEmailCheck && !isPasswordPlaceHolder && !isPasswordEmpty && validPasswordCheck && !isPasswordVerifPlaceHolder && !isPasswordVerifEmpty && validPasswordVerifCheck && passwordValidation && validUsernameCheck) {
//
//            store(usernameInput,userEmailInput, userPasswordVerifInput);
//            signupButton.setBackground(Color.GREEN);
//            signupButton.setText("Account Created");
//            signupButton.setPreferredSize(new Dimension(200,30));
//
//        //}
//
//        return false;
//
//    }

public static void setErrorText(boolean isValid, JLabel errorLabel, String errorMessage, JPanel signupPanel) {
    if (isValid) {
        // Clear the error message and make the label transparent
        errorLabel.setText("");
        errorLabel.setForeground(new Color(0, 0, 0, 0)); // Fully transparent
    } else {
        // Display the error message in red
        errorLabel.setText(errorMessage);
        errorLabel.setForeground(Color.RED);
        errorLabel.setPreferredSize(new Dimension(300, 15)); // Optional: Adjust size for consistency
    }
    signupPanel.revalidate(); // Revalidate the panel to reflect changes
    signupPanel.repaint();   // Repaint the panel to ensure updates are visible
}



    public static void openWelcomePage(){
        MainInterface  welcomePage = new MainInterface();
        welcomePage.showGUI();
    }
        private static void openChatGUI() {
            LoggedInChat loggedInChat = new LoggedInChat();
            loggedInChat.showChatBot();

        }

}
