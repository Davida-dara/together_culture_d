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
import org.example.MainInterface.RoundedButton; // Import RoundedButton
import org.example.LoginInterface;

import static org.example.ExistingUser.userExistingEmail;
import static org.example.LoginInterface.addBorder;
import static org.example.LoginInterface.handleLogin;
import static org.example.NewUser.isValidEmail;
import static org.example.NewUser.isValidPassword;

public class SignupInterface {
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
            signUpPanel.setPreferredSize(new Dimension(400,400));
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
            Border paddingBorder = BorderFactory.createEmptyBorder(10,20,10,20);
            //line border with desired colour
            Border lineBorder =  BorderFactory.createLineBorder(new Color(72,19,38));
            //email text field
            JTextField emailField = new JTextField(15);
            //setting the size of the text field
            emailField.setPreferredSize(new Dimension(250,25));
            //setting the field colour
            emailField.setBackground(Color.WHITE);
            //setting the text colour for the text field
            emailField.setForeground(Color.BLACK);
            //setting the size for the text field
            addBorder(emailField);
            //setting the border
            emailField.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            //adding the focus listener for the textfield
            emailField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //clear the text field only if it has the default text (empty)
                   if(emailField.getText().equals("Enter your email")){
                       emailField.setText("");//clearing the text
                       emailField.setForeground(Color.BLACK);
                   }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    //sets the default instructions back only if the text field is empty
                    emailField.setText("Enter your email");
                    emailField.setForeground(Color.GRAY);

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
            RoundedButton signUpButton = new RoundedButton("Sign up");
            signUpButton.setBackground(new Color(252, 242, 242));
            signUpButton.setForeground(Color.BLACK);
            signUpButton.setPreferredSize(new Dimension(200, 30));
            signUpPanel.add(signUpButton, signUpPanelGBC);
            signUpPanelGBC.gridy++;

            //adding the action listener to call the handle signup method
            signUpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //calling the handle signuo method
                    handleSignup(emailField, emailError, passwordVerificationField, passError2,passwordField,  passError, signUpButton, signUpPanel);
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

    public static boolean handleSignup(JTextField signupEmail, JLabel emailError, JTextField password, JLabel passError, JTextField passVerif,JLabel passVerifError, JButton signUpButton,JPanel signupPanel ){
        NewUser newUser = new NewUser();
        //ArrayList<String, String> users = UserInputDatabase.readingFile();

        //get the text from the email textfield
        String emailText = signupEmail.getText().trim();//trim removes leading and trailing spaces
        //getting text from password textfield
        String passwordText = password.getText().trim();
        String passwordVerifText = passVerif.getText().trim();

        //step 1 is tot check if the email text field is empty and set an error message
        boolean validEmail = true;
        String emailErrorMessage = null;
        if(emailText.isEmpty()) {
            validEmail = false;
            //if the email field is empty we output an error
            emailErrorMessage = "Email required";
        }
        //checking if the email already exists
        if(userExistingEmail(signupEmail.getText())) {
            validEmail = false;
            emailErrorMessage = "this email already exists";
        } else {
            if(!isValidEmail(signupEmail.getText())) {
                validEmail = false;
                emailErrorMessage = "Invalid email";
            }
        }
        setErrorText(validEmail, emailError, emailErrorMessage, signupPanel);

        boolean gettingPassword = isValidPassword(password.getText());
        String passwordErrorMessage = null;
        String passwordVerifErrorMessage = null;
        boolean validPassword = true;
        //checking if the password fields are empty
        if(passwordText.isEmpty()) {
            validPassword = false;
            passwordErrorMessage = "Required Field";
            if(gettingPassword){
                validPassword = false;
                passwordErrorMessage = "Invalid Password";
            }
        }
        setErrorText(validPassword, passError, passwordErrorMessage, signupPanel);
        boolean gettingPasswordVerif = isValidPassword(passVerif.getText());
        boolean passwordValidation = true;
        if(passwordVerifText.isEmpty()){
            passwordValidation = false;
            passwordVerifErrorMessage = "Required Field";
        } else {
            if(!isValidPassword(passwordVerifText)) {
                passwordValidation = false;
                passwordVerifErrorMessage = "Invalid Password";
            } else {
                //if both fields are not empty, check if they match
                if(passwordText.equals(passwordVerifText)){
                    passwordValidation = true;
                }else{
                    passwordValidation = false;
                    passwordVerifErrorMessage = "password's don't match";
                }
            }
            setErrorText(passwordValidation, passVerifError, passwordVerifErrorMessage, signupPanel);
        }
        if(validEmail && validPassword && passwordValidation){
            UserInput.store(signupEmail.getText(), passVerif.getText());
            signUpButton.setBackground(Color.GREEN);
            signUpButton.setText("Account Created");
            signUpButton.setPreferredSize(new Dimension(200,30));

        }
        return false;

    }

    public static void setErrorText(boolean validation, JLabel errorLabel, String errorMessage, JPanel signupPanel){
        errorLabel.setText(errorMessage);
        if(validation){
            errorLabel.setForeground(new Color(signupPanel.getBackground().getRed(), signupPanel.getBackground().getGreen(), signupPanel.getBackground().getBlue(),0));
        } else {
            errorLabel.setForeground(Color.RED);
            errorLabel.setPreferredSize(new Dimension(300,15));
        }
    }

    public static void openWelcomePage(){
        MainInterface  welcomePage = new MainInterface();
        welcomePage.showGUI();
    }

}
