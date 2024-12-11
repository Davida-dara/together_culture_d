package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUser {

    //validating the email
    public static boolean isValidEmail(String email){
        //regular expression patter for a valid email address
        String emailRegex = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        //compile the pattern
        Pattern pattern = Pattern.compile(email);
        //create a Matcher object
        Matcher matcher = pattern.matcher(email);
        //return true if the email matches the pattern, false otherwise
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        //checking the length of the password
        if (password.length() < 8) {
            //password cant be less than 8 characters
            return false;
        }
        //check for at least one uppercase letter
        if(!password.matches(".*[A-Z].*")) {
            return false;
        }
        //checks if the user included lower case letters
        if(!password.matches(".*[a-z].*")){
            return false;
        }
        //checks for at least one digit
        if(!password.matches(".*\\d.*")){
            return false;
        }
        //checks for the least one special character
        if(!password.matches(".*[!@#$%^&*()-+=?].*")){
            return false;
        }
        // if all the criteria is met
        return true;
    }
    public static boolean isValidUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            return false; // Null or empty username
        }
           if (username == null || username.length() <= 3) {
                return false; // At least 4 characters required
            }

            boolean hasUpperCase = username.matches(".*[A-Z].*");
            boolean hasLowerCase = username.matches(".*[a-z].*");
            boolean hasDigit = username.matches(".*\\d.*");
        System.out.println("Username Length Valid: " + (username.length() > 3));
        System.out.println("Contains Uppercase: " + hasUpperCase);
        System.out.println("Contains Lowercase: " + hasLowerCase);
        System.out.println("Contains Digit: " + hasDigit);

            // Username must have at least one uppercase, one lowercase, and one digit
            return hasUpperCase && hasLowerCase && hasDigit;


    }

    }


