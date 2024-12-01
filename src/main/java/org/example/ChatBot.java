package org.example;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.example.MainInterface.RoundedButton; // Import RoundedButton

import static org.example.ExistingUser.userExistingEmail;
import static org.example.SignupInterface.openWelcomePage;
import static org.example.SignupInterface.setErrorText;
public class ChatBot {
    private Bot bot;

    private static final String BOTNAME = "super";
    private Chat chatSession;
//    Loading AIML Files for chatbot conversations
    public ChatBot() {
        String botClassPath = "src/main/resources";
        this.bot = new Bot(BOTNAME, botClassPath);
        this.chatSession = new Chat(bot);
        generateResponse(null);
    }

    //Testing conversation
    public void generateResponse(String userInput) {
        userInput = "I have a question";
        if (this.chatSession != null) {
        String response = chatSession.multisentenceRespond(userInput);
        System.out.println("Bot: " + response);
        } else System.out.println("chat is not properly initialized");
    }
    public class RoundedTextField extends JTextField{
        private int cornerRadius;

        public RoundedTextField(int cornerRadius) {
            super();
            //set the corner radius
            this.cornerRadius = cornerRadius;
            //make the background transparent
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Fill the background with a rounded rectangle
            g2.setColor(getBackground());
            g2.fillRoundRect(0,0,getWidth(), getHeight(),cornerRadius, cornerRadius);
            // draw the textfield
            super.paintComponent(g);
            g2.dispose();
        }
        @Override
        protected void paintBorder (Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            //draw the rounded border
            g2.setColor(getForeground());
            g2.drawRoundRect(0,0,getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);

        }
        @Override
        public void setBorder (Border border){
            //disable default borders to prevent conflicts
        }
        @Override
        public Insets getInsets(){
            //add padding to the text field
            return new Insets(5,5,5,5);
        }
    }
    public static void showChatBot(){
        //creating the frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //creating the main panel
        JPanel mainPanel  = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 134, 148));


        //creating the side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(252, 73, 97));
        int customWidth = 300;
        sidePanel.setPreferredSize(new Dimension(customWidth,screenSize.height));
        mainPanel.add(sidePanel, BorderLayout.WEST);

        //creating the text field in which ghe user will enter their queries
        JTextField mainTextField = new JTextField();



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
}
