package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class ExistingUser {
    public static boolean userExistingEmail(String userEmail) {
        ArrayList<HashMap<String, String>> users = UserInput.readingFile();
        boolean userExists = false;
        //this will check if the username entered exists in the user text file
        for(int i = 0; i < users.size(); i++){
            if(userEmail.equals(users.get(i).get("email")) && !userExists){
                userExists = true;

            }
        }
        return userExists;
    }

    //this will loop through the array list hash map and check if the password entered exists
    public boolean userExistingPswd(ArrayList<HashMap<String, String>> users, String password, String email){
        UserInput.readingFile();
        boolean pswdCorrect = false;
        boolean userExists = false;
        //this will check if the email entered exists in the user text file
        for(int i = 0; i < users.size(); i++){
            if(email.equals(users.get(i).get("email")) && !userExists){
                userExists = true;
                if(users.get(i).get("password").equals(password)) {
                    pswdCorrect = true;
                }
            }
        }
        return pswdCorrect;
    }
    //this method will loop through the array list and print it out
    public static void passwordRequirements(){
        ArrayList<String> passwordRequirements = new ArrayList<String>();
        passwordRequirements.add("Password should have at least have 8 characters");
        passwordRequirements.add("Password should at least include Upper case letter");
        passwordRequirements.add("Password should contain at least one digit");
        passwordRequirements.add("Password should include at least one special character");
        for(int i = 0; i < passwordRequirements.size(); i++){
            System.out.println(passwordRequirements.get(i));
        }
    }
}
