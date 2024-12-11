package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.LoginInterface.addBorder;
import static org.example.MainInterface.createRoundedButton;
import static org.example.SignupInterface.setErrorText;

public class ResetPassword {
     private static String userEmailText = "";
    private static String userPasswordText = "";
    private static String userNameText = "";

    public static void showRP(){
        JFrame frame  = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(241,163,163));
        mainPanel.setPreferredSize(new Dimension(350,300));
        GridBagConstraints panelGBC = new GridBagConstraints();
        panelGBC.gridx = 0;
        panelGBC.gridy = 0;
        panelGBC.insets = new Insets(5,5,5,5);
        panelGBC.anchor = GridBagConstraints.CENTER;

        JLabel title  = new JLabel("Reset Password");
        title.setForeground(new Color(72, 19, 38));
        title.setFont(new Font( "Inter", Font.BOLD, 20));
        mainPanel.add(title, panelGBC);
        panelGBC.gridy++;

        //adding the padding for the border and the border color
        Border paddingBorder = BorderFactory.createEmptyBorder(5,20,5,20);
        //line border with desired colour
        Border lineBorder = BorderFactory.createLineBorder(new Color(72, 19, 38));
        //creating the username field
        JTextField userNameField = new JTextField(15);
        //making the background transparent
        userNameField.setBackground(Color.white);
        //user entry text color
        userNameField.setForeground(Color.BLACK);
        //set the placeholder text
        userNameField.setText("Enter your username");
        //set the placeholder text colour
        userNameField.setForeground(Color.GRAY);
        //adding the border
        addBorder(userNameField);
        //setting the border
        userNameField.setBorder(BorderFactory.createCompoundBorder(lineBorder,paddingBorder));
        userNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //clear the text field only if it has the default text
                if(userNameField.getText().equals("Enter your username")){
                    userNameField.setText("");//clearing the text
                    userNameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //sets the default text only if the text field is empty
                if (userNameField.getText().trim().isEmpty()) {
                    userNameField.setText("Enter your username");
                    userNameField.setForeground(Color.GRAY);
                    userNameText = userNameField.getText().trim();

                }
            }
        });
        mainPanel.add(userNameField, panelGBC);
        panelGBC.gridy++;
        //adding the error label for the username field
        JLabel userNameErrorLabel = new JLabel("");
        userNameErrorLabel.setBackground(new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        userNameErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(userNameErrorLabel, panelGBC);
        panelGBC.gridy++;


        //creating the email text field
        JTextField userEmail = new JTextField(15);
        //making the background transparent
        userEmail.setBackground(Color.white);
        //user entry text color
        userEmail.setForeground(Color.BLACK);
        //set the placeholder text
        userEmail.setText("Enter your email");
        //set the placeholder text colour
        userEmail.setForeground(Color.GRAY);
        //adding the border
        addBorder(userEmail);
        //setting the border
        userEmail.setBorder(BorderFactory.createCompoundBorder(lineBorder,paddingBorder));
        //adding the focus listener for the text-field
        userEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //clear the text field only if it has the default text
                if(userEmail.getText().equals("Enter your email")){
                    userEmail.setText("");//clearing the text
                    userEmail.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //sets the default text only if the text field is empty
                if (userEmail.getText().trim().isEmpty()) {
                    userEmail.setText("Enter your email");
                    userEmail.setForeground(Color.GRAY);
                    userEmailText = userEmail.getText().trim();

                }
            }
        });

        mainPanel.add(userEmail, panelGBC);
        panelGBC.gridy++;
        //adding the error label for the email field
        JLabel emailErrorLabel = new JLabel("");
        emailErrorLabel.setBackground(new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        emailErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(emailErrorLabel, panelGBC);
        panelGBC.gridy++;


        //creating the password  text-field
        JTextField newPasswordField = new JTextField(15);
        //hiding the password text-field
        newPasswordField.setBackground(new Color (mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        Border defaultPadding = BorderFactory.createEmptyBorder(10,30,10,30);
        Border defaultBorderColour = BorderFactory.createLineBorder(new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        newPasswordField.setBorder(BorderFactory.createCompoundBorder(defaultBorderColour, defaultPadding));
        //setting the place-holder text
        newPasswordField.setText("Enter new password");
        //setting the colour for the place-holder text
        newPasswordField.setForeground(new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        //setting the user entry text colour
        //newPasswordField.setForeground(Color.BLACK);
        mainPanel.add(newPasswordField, panelGBC);
        panelGBC.gridy++;
        //adding the password error message label
        JLabel passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setBackground(new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        passwordErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(passwordErrorLabel, panelGBC);
        panelGBC.gridy++;

        MainInterface.RoundedButton resetButton = createRoundedButton("Reset", new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0),Color.WHITE, new Dimension(100,30));
        mainPanel.add(resetButton, panelGBC);
        panelGBC.gridy++;
        //adding the mouse listener for the button to check of the email exists and then run the password verifications
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                emailAndPasswordChecks(userNameField, userNameErrorLabel, userEmail, emailErrorLabel, newPasswordField, passwordErrorLabel, mainPanel);
            }
        });

        // Defer enabling focus until after the UI is fully initialized
        SwingUtilities.invokeLater(() -> {
            userEmail.setFocusable(true);
            newPasswordField.setFocusable(true);
            mainPanel.requestFocusInWindow(); // Redirect focus away from the text field
        });



        //adding mainpanel to the frame
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void  resettingPassword(String newPass, String userEmail){
        ArrayList<HashMap<String, String>> users = UserInput.readingFile();
        boolean userExists = false;
        //this will check if the username entered exists in the user text file
        for(int i = 1; i<users.size(); i++){
            if(userEmail.equals(users.get(i).get("email"))&& !userExists){
                userExists = true;
                users.get(i).put("password", newPass);

                UserInput.updating(i, users.get(i).get("email"), newPass);
            }
        }

    }

    public static void emailAndPasswordChecks(JTextField usernameField, JLabel usernameErrorMessage, JTextField emailField, JLabel emailErrorMessage,
                                              JTextField passwordField, JLabel passwordErrorMessage, JPanel mainPanel){
        ArrayList<HashMap<String, String>> users = UserInput.readingFile();

        ExistingUser existingUser = new ExistingUser();
        //username checks
        String userNameInput = usernameField.getText().trim();
        boolean isUserNamePlaceHolder = userNameInput.equals("Enter your username");
        boolean isUserNameEmpty = userNameInput.isEmpty();
        boolean isExistingUserName = existingUser.existingUsername(userNameInput);
        boolean isValidUserName = NewUser.isValidUsername(userNameInput);
        String userNameError = "";
        if(isUserNamePlaceHolder || isUserNameEmpty){
            // checks if the username text field value is empty or the placeholder text
            //if true outputs the following error
            userNameError = "Username required";
        }else if(!isExistingUserName){
            //checks if the username does not exist
            //if true will output the following error
            userNameError = "Unrecognised username";

        } else if(!isValidUserName){
            //checks if the username entered does not follow the set requirements
            //if true outputs the following error
            userNameError = "Invalid username";
        }
        String userEmailInput = emailField.getText().trim();
        boolean isEmailPlaceHolder = userEmailInput.equals("Enter your email");
        boolean isEmailEmpty = userEmailInput.isEmpty();
        boolean isEmailExistingEmail = existingUser.userExistingEmail(userEmailInput);
        boolean isValidUserEmail = NewUser.isValidEmail(userEmailInput);
        String emailError = null;
        if(isEmailPlaceHolder || isEmailEmpty){
            //checks if the data in the email field is the placeholder text or is empty
            //if true outputs the following error
            emailError = "Email required";
        }
        setErrorText(!isEmailPlaceHolder && !isEmailEmpty ,emailErrorMessage, emailError,mainPanel);

        if(!isEmailEmpty) {
            //error message transparent if the email is not empty
            emailErrorMessage.setForeground(new Color(mainPanel.getBackground().getRed(),
                    mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));

            if(!isEmailExistingEmail){
                emailError = "Email doesn't exist";

            } else if (!isValidUserEmail) {
                emailError = "Invalid email";

            }

            if (isEmailExistingEmail) {
                emailErrorMessage.setForeground(new Color(mainPanel.getBackground().getRed(),
                        mainPanel.getBackground().getGreen(),
                        mainPanel.getBackground().getBlue(), 0));
                Border changeBorderColour = BorderFactory.createLineBorder(new Color(72, 19, 38));
                Border defaultPadding = BorderFactory.createEmptyBorder(10, 30, 10, 30);
                //changing the colour of the text field
                passwordField.setBorder(BorderFactory.createCompoundBorder(changeBorderColour, defaultPadding));
                //changing the colour of the place-holder
                passwordField.setText("Enter new password");
                passwordField.setForeground(Color.GRAY);
                //setting the text colour for the user entry
                passwordField.setForeground(Color.BLACK);
                //setting the background colour for the text-field
                passwordField.setBackground(Color.BLACK);
                //adding the border changes
                addBorder(passwordField);
                passwordField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        //clear the text field only if it has the default text
                        if (passwordField.getText().equals("Enter your new password")) {
                            passwordField.setText("");//clearing the text
                            passwordField.setForeground(Color.BLACK);
                        }
                    }
                        @Override
                        public void focusLost (FocusEvent e){
                            //sets the default text only if the text field is empty
                            if (passwordField.getText().trim().isEmpty()) {
                                passwordField.setText("Enter your new password");
                                passwordField.setForeground(Color.GRAY);
                                userPasswordText = passwordField.getText().trim();

                            }
                        }

                });


            } else {
                setErrorText(isEmailExistingEmail, emailErrorMessage, emailError, mainPanel);
            }
        } else {
            setErrorText(isEmailExistingEmail && isValidUserEmail, emailErrorMessage, emailError, mainPanel);
        }

        //password checks
        String userPasswordInput = passwordField.getText().trim();
        boolean isPasswordPlaceHolder = userEmailInput.equals("Enter new password");
        boolean isPasswordEmpty = userPasswordInput.isEmpty();
        boolean isExistingPassword = existingUser.userExistingPswd(users,userNameInput,userPasswordInput, userEmailInput);
        boolean isValidUserPassword = NewUser.isValidPassword(userPasswordInput);
        String passwordError = null;

        if(isPasswordPlaceHolder || isPasswordEmpty){
            //checks if the password text-field's value is the place-holder os is empty
            //if true outputs the following error message
            passwordError = "new password required";
        }else if (isExistingPassword){
            //if thr new password entered exists the following error message will be outputted
            passwordError = "Password already exists";

        } else if (isValidUserPassword){
            //if the password doesn't exist however doesn't follow the requirements
            //the following error messages will be displayed
            passwordError = "Invalid password";
        }
        setErrorText(!isPasswordPlaceHolder && !isPasswordEmpty && isExistingPassword && isValidUserPassword,passwordErrorMessage, passwordError, mainPanel);


    }



}

