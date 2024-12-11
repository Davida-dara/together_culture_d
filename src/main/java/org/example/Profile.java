package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static org.example.MainInterface.createRoundedButton;
public class Profile {
    public static void showProfile(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creating a panel that will have all the component
        JPanel mainProfilePanel = new JPanel(new GridBagLayout());
        GridBagConstraints panelGBC = new GridBagConstraints();
        panelGBC.insets = new Insets(5,5,5,5);
        panelGBC.gridx = 0;
        panelGBC.gridy = 0;
        panelGBC.anchor = GridBagConstraints.CENTER;

        //setting the size of the panel
        mainProfilePanel.setPreferredSize(new Dimension(350,300));
        //setting the colour of the panel
        mainProfilePanel.setBackground(new Color(241,163,163));
        // Add a label to display the username
        JLabel usernameLabel = new JLabel("Welcome");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setForeground(new Color(72, 19, 38));
        mainProfilePanel.add(usernameLabel, panelGBC);
        panelGBC.gridy++;

        MainInterface.RoundedButton resetPasswordButton = createRoundedButton("Reset Password", new Color(72,19,38),Color.WHITE,  new Dimension (150,30) );
        mainProfilePanel.add(resetPasswordButton, panelGBC);
        panelGBC.gridy++;
        resetPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openResetPassword();

            }
        });


        MainInterface.RoundedButton logoutButton = createRoundedButton("Log Out", Color.WHITE, new Color(72, 19,38), new Dimension(150, 30));
        mainProfilePanel.add(logoutButton, panelGBC);
        panelGBC.gridy++;
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openMainInterface();

            }
        });




        //adding the main panel to the frame
        frame.add(mainProfilePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    public static void openMainInterface (){
        MainInterface mainInterface = new MainInterface();
        mainInterface.showGUI();
    }
    public static void openResetPassword(){
        ResetPassword resetPassGUI = new ResetPassword();
        resetPassGUI.showRP();
    }



}
