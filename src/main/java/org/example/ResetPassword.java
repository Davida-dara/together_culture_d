package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.LoginInterface.addBorder;
import static org.example.MainInterface.createRoundedButton;
import static org.example.MainInterface.openLogIn;
import static org.example.SignupInterface.setErrorText;
import static org.example.UserInput.readingFile;
import static org.example.UserInput.update;

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

        //creating the email text field
        JTextField newPasswordField = new JTextField(15);
        //making the background transparent
        newPasswordField.setBackground(Color.white);
        //user entry text color
        newPasswordField.setForeground(Color.BLACK);
        //set the placeholder text
        newPasswordField.setText("Enter your new password");
        //set the placeholder text colour
        newPasswordField.setForeground(Color.GRAY);
        //adding the border
        addBorder(newPasswordField);
        //setting the border
        newPasswordField.setBorder(BorderFactory.createCompoundBorder(lineBorder,paddingBorder));
        //adding the focus listener for the text-field
        newPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //clear the text field only if it has the default text
                if(newPasswordField.getText().equals("Enter your new password")){
                    newPasswordField.setText("");//clearing the text
                    newPasswordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //sets the default text only if the text field is empty
                if (newPasswordField.getText().trim().isEmpty()) {
                    newPasswordField.setText("Enter your new password");
                    newPasswordField.setForeground(Color.GRAY);
                    userPasswordText = newPasswordField.getText().trim();
                }

            }
        });

        mainPanel.add(newPasswordField, panelGBC);
        panelGBC.gridy++;
        //adding the password error message label
        JLabel passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setBackground(new Color(mainPanel.getBackground().getRed(),
                mainPanel.getBackground().getGreen(), mainPanel.getBackground().getBlue(),0));
        passwordErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(passwordErrorLabel, panelGBC);
        panelGBC.gridy++;

        MainInterface.RoundedButton resetButton = createRoundedButton("Reset", new Color(72, 19, 38),Color.WHITE, new Dimension(100,30));
        mainPanel.add(resetButton, panelGBC);
        panelGBC.gridy++;
        //adding the mouse listener for the button to check of the email exists and then run the password verifications
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Reset button clicked!");
                emailAndPasswordChecks(userNameField, userNameErrorLabel, userEmail, emailErrorLabel, newPasswordField, passwordErrorLabel, mainPanel);
            }
        });


        // Defer enabling focus until after the UI is fully initialized
        SwingUtilities.invokeLater(() -> {
            userEmail.setFocusable(true);
            userNameField.setFocusable(true);
            newPasswordField.setFocusable(true);
            mainPanel.requestFocusInWindow(); // Redirect focus away from the text field
        });



        //adding mainpanel to the frame
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void resettingPassword(String username, String email, String newPassword) {
        System.out.println("Resetting password called with:");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("New Password: " + newPassword);

        // Validate inputs
        if (username == null || email == null || newPassword == null ||
                username.trim().isEmpty() || email.trim().isEmpty() || newPassword.trim().isEmpty()) {
            System.out.println("Error: Invalid input. Please provide valid username, email, and password.");
            return;
        }

        // Retrieve all users from the file
        ArrayList<HashMap<String, String>> users = readingFile();

        // Find the user with matching username and email
        boolean userFound = false;
        int lineIndex = 0;

        for (int i = 0; i < users.size(); i++) {
            HashMap<String, String> user = users.get(i);
            if (username.equals(user.get("username")) && email.equals(user.get("email"))) {
                userFound = true;
                lineIndex = i + 1;  // File lines are 1-based
                break;
            }
        }

        if (!userFound) {
            System.out.println("No matching user found for username: " + username + " and email: " + email);
            System.out.println("Password reset failed. No matching user found.");
            return;
        }

        // Call updateUser to update the password
        boolean success = update(lineIndex, username, email, newPassword);
        if (success) {
            System.out.println("Password reset successfully for user: " + username);
        } else {
            System.out.println("Password reset failed during file update.");
        }
    }





    public static void emailAndPasswordChecks(JTextField usernameField, JLabel usernameErrorMessage, JTextField emailField, JLabel emailErrorMessage,
                                              JTextField passwordField, JLabel passwordErrorMessage, JPanel mainPanel){
        ArrayList<HashMap<String, String>> users = readingFile();

        ExistingUser existingUser = new ExistingUser();

        // Username checks
        String userNameInput = usernameField.getText().trim();
        boolean isUserNamePlaceHolder = userNameInput.equals("Enter your username");
        boolean isUserNameEmpty = userNameInput.isEmpty();
        boolean isExistingUserName = existingUser.existingUsername(userNameInput);
        boolean isValidUserName = NewUser.isValidUsername(userNameInput);
        String userNameError = "";

        if (isUserNamePlaceHolder || isUserNameEmpty) {
            userNameError = "Username required";
        } else if (!isExistingUserName) {
            userNameError = "Unrecognised username";
        } else if (!isValidUserName) {
            userNameError = "Invalid username";
        }
        setErrorText(!isUserNamePlaceHolder && !isUserNameEmpty && isExistingUserName && isValidUserName, usernameErrorMessage, userNameError, mainPanel);

        // Email checks
        String userEmailInput = emailField.getText().trim();
        boolean isEmailPlaceHolder = userEmailInput.equals("Enter your email");
        boolean isEmailEmpty = userEmailInput.isEmpty();
        boolean isValidEmail = NewUser.isValidEmail(userEmailInput);
        boolean isExistingEmail = existingUser.userExistingEmail(userEmailInput);
        String emailError = "";

        if (isEmailPlaceHolder || isEmailEmpty) {
            emailError = "Email required";
        } else if (!isExistingEmail) {
            emailError = "Unrecognised email";
        } else if (!isValidEmail) {
            emailError = "Invalid email format";
        }
        setErrorText(!isEmailPlaceHolder && !isEmailEmpty && isExistingEmail && isValidEmail, emailErrorMessage, emailError, mainPanel);

        // Password checks
        String userPasswordInput = passwordField.getText().trim();
        boolean isPasswordEmpty = userPasswordInput.isEmpty();
        String passwordError = "";

        if (isPasswordEmpty) {
            passwordError = "Password required";
        }
        setErrorText(!isPasswordEmpty, passwordErrorMessage, passwordError, mainPanel);

        // Proceed with resetting the password if all validations pass
        if (isValidUserName && isExistingUserName && isValidEmail && isExistingEmail && !isPasswordEmpty) {
            ResetPassword.resettingPassword(userNameInput, userEmailInput, userPasswordInput); // Correctly pass inputs in order
        }
    }

    static void openLoggedInChat(){
        LoggedInChat lIC = new LoggedInChat();
                lIC.showChatBot();
     }



}

