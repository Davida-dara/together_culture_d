package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundedTextField extends JTextField {
        private int cornerRadius;
        public RoundedTextField(int columns, int cornerRadius) {
            super(columns);
            this.cornerRadius = cornerRadius;
            setOpaque(false); //make the background transparent to draw custom shape
            setHorizontalAlignment((JTextField.LEFT));
            //add padding for left alignment
            setMargin(new Insets(0,10,0,10));
        }
        @Override
        protected void paintComponent (Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            //fill the background with a rounded rectangle
            g2.setColor(getBackground());
            g2.fillRoundRect(0,0,getWidth(), getHeight(), cornerRadius, cornerRadius);
            //draw the border
            g2.setColor(getForeground());
            g2.drawRoundRect(0,0,getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
            //call super to render the text
            super.paintComponent(g2);
            //dispose of the Graphics2d object to free resources
            g2.dispose();
        }
        @Override
        public void setBorder(Border border){
            //prevents the default border from interfering with the rounded edges
            //nothing needs to be written in here to retain rounded edges
        }
        @Override
        public Insets getInsets() {
            //adjust insets for vertical centering
            //int verticalPadding = (getHeight() - getFont().getSize()) / 2-2;
            //adjust the text padding
            return new Insets (10,10,10,10);
        }
    }

