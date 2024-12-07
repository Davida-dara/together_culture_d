package org.example;

import javax.swing.*;
import java.awt.*;

//public class RoundedButton extends JButton {
//    private final int arcWidth;
//    private final int arcHeight;
//    public RoundedButton (String text, int arcWidth, int arcHeight){
//        super(text);
//        this.arcWidth = arcWidth;
//        this.arcHeight = arcHeight;
//        setContentAreaFilled(false); //Disable default button background rendering
//        setFocusPainted(false); // remove focus border for better UI
//        setOpaque(false); //ensure transparency
//    }
//    @Override
//    protected void paintComponent(Graphics g){
//        Graphics2D g2 = (Graphics2D) g.create();
//        //enable anti-aliasing for smooth edges
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        // Drawing the button background
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0,0,getWidth(),getHeight(),arcWidth, arcHeight);
//        //draw the button text
//        FontMetrics fm = g2.getFontMetrics();
//        g2.setColor(getForeground());
//        String text = getText();
//        int x = (getWidth() - fm.stringWidth(text)) / 2;
//        int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
//        g2.drawString(text, x, y);
//        g2.dispose();
//        //call parent to handle focus painting
//        super.paintComponent(g);
//    }
//    @Override
//    protected void paintBorder(Graphics g){
//        Graphics2D g2 = (Graphics2D) g.create();
//        //enable anti-aliasing for smooth edges
//       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//       //draw the rounded border
//        g2.setColor(getForeground());
//        g2.drawRoundRect(0,0,getWidth() -1, getHeight() -1, arcWidth, arcHeight);
//        g2.dispose();
//    }
//}
public class RoundedButton extends JButton {
    // Constructor with just a label (string)
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false); // Ensures no border is painted by default
        setOpaque(false); // Ensures full transparency
    }

    // Constructor with label, background color, foreground color, and preferred size
    public RoundedButton(String label, Color background, Color foreground, Dimension size) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(background);
        setForeground(foreground);
        setPreferredSize(size);
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
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);  // 50 is the arc width/height

        // Set the color for the text
        g2.setColor(getForeground());

        // Center the text within the button
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString(getText(), x, y);

        // Dispose of the graphics object
        g2.dispose();

        super.paintComponent(g);
    }
}

