package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.example.LoginInterface.addBorder;
import org.example.RoundedTextField;


public class StayLoggedOut {

    public static void showStayLogOut(){
        try{
            //creating the frame
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //creating the Dimensions for the frame
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //creating the main panel
            JPanel mainPanel = new JPanel(new BorderLayout()){
                Image backgroundImage = ImageIO.read(new File("/Users/gaiiaharb/Documents/GitHub/together_culture/src/main/java/org/example/signedOutBackground.png"));
                @Override
                public void paintComponent(Graphics b){
                    super.paintComponent(b);
                    //drawing the background image
                    b.drawImage(backgroundImage, 0,0,getWidth(), getHeight(),this);
                }
            };
            //creating a panel for the logo label
            JPanel logoPanel  = new JPanel();
            logoPanel.setPreferredSize(new Dimension(50,200));
            logoPanel.setBackground(new Color(mainPanel.getBackground().getRed(), mainPanel.getBackground().getGreen(),
                    mainPanel.getBackground().getBlue(), 0));
            logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            //create a JLabel for the text
            //using HTML for line breaks and colours
            JLabel logoLabel = new JLabel("<html><a href = ''><span style='text-decoration:none;'<font color='#481326'>Together<br>Culture<br></font><font color ='#ECD7DF'>Cambridge</font></a></span></html>");
            logoLabel.setFont(new Font("inter",Font.BOLD, 55));
            logoPanel.add(logoLabel);

            //make the logo a hyperlink
            logoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try{
                        Desktop.getDesktop().browse(new URI("https://www.togetherculture.com"));
                    }catch (IOException | java.net.URISyntaxException ex){
                        ex.printStackTrace();
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    logoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR)); //changes cursor to hand when mouse hovered over the text
                }
            });
            mainPanel.add(logoPanel,BorderLayout.NORTH);
            //creating a panel that will have the different components
            JPanel componentPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;//columnn index (centered horizontally)
            gbc.gridy = 0;//row index (first row)
            gbc.insets = new Insets(10,10,10,10);
            gbc.fill = GridBagConstraints.NONE; //do not stretch the component
            gbc.anchor = GridBagConstraints.CENTER; //center the component
            componentPanel.setOpaque(false);

            //page title
            JLabel title  = new JLabel();
            title.setText("How Can I Help You?");
            title.setFont(new Font("Inter", Font.BOLD, 30));
            title.setForeground(new Color(72, 19, 38));
            //updating the GridBagConsrtraint for the JLabel
            gbc.gridx = 0; //keep the JLabel in the center column
            gbc.gridy = 0; //first row
            gbc.anchor = GridBagConstraints.NORTHWEST; //Center the JLabel horizontally
            gbc.insets = new Insets(10,10,10,10); // add some padding around the JLabel
            //add the JLabel to the componentPanel
            componentPanel.add(title, gbc);
            gbc.gridy++;





            //Creating the text-field where the user will initially type in there question to the chatbot
            RoundedTextField textEntryField = new RoundedTextField(40,20);
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
            //adding textfield to componentPanel using gridbag
            componentPanel.add(textEntryField, gbc);
            gbc.gridy++;
            //adding the component panel to the mainpanel
            mainPanel.add(componentPanel, BorderLayout.CENTER);
            //creating a panel that wpuld have all the buttons next to eachother under the textfield
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false);// transparent background for the button panel
            //creating the buttons
            JButton eventsButton = createStyledButton("Upcoming Events");
            JButton membershipButton = createStyledButton("Memberships");
            JButton aboutButton = createStyledButton("About Us");
            //adding the buttons to the buttonPanel
            buttonPanel.add(eventsButton);
            buttonPanel.add(aboutButton);
            buttonPanel.add(membershipButton);
            //adding the button panel to the mainpanel
            componentPanel.add(buttonPanel, gbc);
            gbc.gridy++;



           // mainPanel.setBackground(new Color(255, 134, 148));
            //setting the size f the main panel
            mainPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));

            //adding the main panel to the frame for it to be visible
            frame.add(mainPanel);
            //adding the frame
            frame.pack();
            frame.setSize(screenSize.width, screenSize.height);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }catch(IOException e){
            e.printStackTrace();
        }


    }
    static void roundTextFieldBorder(JTextField textField){
        Border defaultPadding = BorderFactory.createEmptyBorder(5,20,5,20);
        Border defaultColour = BorderFactory.createLineBorder(Color.WHITE);
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

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0,0,0,0));// transparent background
        button.setForeground(new Color(72,19,38));// text color
        Border buttonBorder = BorderFactory.createLineBorder(new Color(72,19,38),2);
        button.addMouseListener(new MouseAdapter() {
            private final Color hoverBackground = new Color(252, 73, 97);
            private final Color hoverForeground = Color.WHITE;
            private final Color normalBackground = new Color(0,0,0,0);
            private final Color normalForeground = new Color(72,19,38);
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.setBackground(hoverBackground);//changes the background colour
                button.setForeground(hoverForeground);//changes the text colour
            }
            @Override
            public void mouseExited(MouseEvent e){
                button.setForeground(normalForeground);//resets the text color
                button.setBackground(normalBackground);// resets the background color
            }
        });
        return button;
    }
}


