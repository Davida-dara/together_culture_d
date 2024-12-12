package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class ExistingUser {
    public static boolean userExistingEmail(String userEmail) {
        ArrayList<HashMap<String, String>> users = UserInput.readingFile();
        boolean userExists = false;
        //this will check if the username entered exists in the user text file
        for (int i = 1; i < users.size(); i++) {
            if (userEmail.equals(users.get(i).get("email")) && !userExists) {
                userExists = true;

            }
        }
        return userExists;
    }

    //this will loop through the array list hash map and check if the password entered exists
    public boolean userExistingPswd(ArrayList<HashMap<String, String>> users, String password, String email, String username) {
        UserInput.readingFile();
        boolean pswdCorrect = false;
        boolean userExists = false;
        boolean userEmailExists = false;
        //this will check if the email entered exists in the user text file
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).get("username")) && !userExists) {
                userExists = true;
                if (email.equals(users.get(i).get("email")) && !userEmailExists) {
                    userEmailExists = true;
                    if (users.get(i).get("password").equals(password)) {
                        pswdCorrect = true;
                    }
                }
            }
        }
        return pswdCorrect;
    }

    public boolean existingUsername(String username){
        ArrayList<HashMap<String, String>> users = UserInput.readingFile();
        boolean userNameExists = false;
        //this will check if the email entered exists in the user text file
        for (int i = 0; i < users.size(); i++) {
                    if(users.get(i).get("username").equals(username)){
                        userNameExists = true;

                    }
                }
        return userNameExists;
    }


}