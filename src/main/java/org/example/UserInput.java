package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInput {



    public static void store(String username, String email, String password) {
        // Validate inputs
        if (username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty()) {
            System.err.println("Error: One or more inputs are null or empty.");
            return;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("users.txt", true))) {
            // Write user data to the file with clear separators
            bufferedWriter.write(username + "," + email + "," + password);
            bufferedWriter.newLine();
            System.out.println("User data successfully stored.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing user data: " + e.getMessage());
        }
    }

//    public static ArrayList<HashMap<String, String>> readingFile(){
//        ArrayList<HashMap<String, String>> users = new ArrayList<>();
//        try{
//            //opening a file reader
//            FileReader fileReader = new FileReader("users.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String line;
//            //read each line from the file
//            while((line = bufferedReader.readLine()) != null){
//                //empty hashmap
//                HashMap<String, String> userData = new HashMap<String, String>();
//                String[] lineSplit = line.split(" ");
//                userData.put("username", lineSplit[0]);
//                lineSplit = line.split(" ");
//                userData.put("email", lineSplit[1]);
//                userData.put("password", lineSplit[2]);
//
//                users.add(userData);
//            }
//            //close the bufferedReader
//            bufferedReader.close();
//            return users;
//        }catch(IOException e){
//            e.printStackTrace();
//            return users;
//        }
//    }
public static ArrayList<HashMap<String, String>> readingFile() {
    ArrayList<HashMap<String, String>> users = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Skip empty or invalid lines
            if (line.trim().isEmpty()) continue;

            // Split the line into parts (assuming comma-separated values)
            String[] parts = line.split(",");

            // Validate the line format
            if (parts.length < 3) {
                System.out.println("Invalid line: " + line); // Debugging output
                continue; // Skip this line
            }

            // Create a user object with username, email, and password
            HashMap<String, String> user = new HashMap<>();
            user.put("username", parts[0].trim());
            user.put("email", parts[1].trim());
            user.put("password", parts[2].trim());

            users.add(user);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return users;
}


//    public static boolean updating(Integer i, String email, String newPass, String pass){
//        try{
//           //read the content fo the file into the memory
//           File file = new File("users.txt");
//           BufferedReader reader = new BufferedReader(new FileReader(file));
//           StringBuilder contentBuilder = new StringBuilder();
//           String line;
//           int lineNumber  = 1;
//           while((line = reader.readLine()) != null){
//               //check if this is the line to update
//               if(lineNumber == i){
//                   line = email + " " + newPass;
//               }
//               contentBuilder.append(line).append("\n");
//               lineNumber++;
//           }
//           reader.close();
//           // write teh updated content back to the file
//            FileWriter writer  = new FileWriter(file);
//            writer.write(contentBuilder.toString());
//            writer.close();
//            return true;
//        }catch(IOException e){
//            System.out.println("An error occurred while updating the file: " + e.getMessage());
//            e.printStackTrace();
//
//        }
//        return false;
//
//    }
public static boolean update(int lineNumber, String username, String email, String newPassword) {
    try {
        File file = new File("users.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        int currentLine = 1;

        while ((line = reader.readLine()) != null) {
            if (currentLine == lineNumber) {
                // Update the line
                line = username + "," + email + "," + newPassword;
            }
            contentBuilder.append(line).append("\n");
            currentLine++;
        }
        reader.close();

        // Write the updated content back to the file
        FileWriter writer = new FileWriter(file);
        writer.write(contentBuilder.toString());
        writer.close();

        return true;
    } catch (IOException e) {
        System.out.println("An error occurred while updating the file: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}



}
