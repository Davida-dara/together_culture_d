package org.example;

import javax.swing.*;

import static org.example.MainInterface.showGUI;
import static org.example.Profile.showProfile;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void  main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                // Create an instance of LoggedInChat
//                LoggedInChat loggedInChat = new LoggedInChat();
//
//                // Call the non-static method on the instance
//                loggedInChat.showChatBot();
                LoggedInChat botting = new LoggedInChat();
                botting.showChatBot();
         //       botting.processConversation();

         //       LoggedInChat testing = new LoggedInChat();
        //        testing.processConversation();
            //    showGUI();

           }
        });
    }
}