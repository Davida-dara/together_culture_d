package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInput {
    public static void store(String email, String password) {
        try{
            //open a file writer with append mode (to append new users)
            FileWriter fileWriter = new FileWriter("users.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //write user data to the file
            bufferedWriter.write(email);
            bufferedWriter.write(" ");
            bufferedWriter.write(password);
            bufferedWriter.newLine();

            //closing the buffered writer
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<HashMap<String, String>> readingFile(){
        ArrayList<HashMap<String, String>> users = new ArrayList<>();
        try{
            //opening a file reader
            FileReader fileReader = new FileReader("users.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            //read each line from the file
            while((line = bufferedReader.readLine()) != null){
                //empty hashmap
                HashMap<String, String> userData = new HashMap<String, String>();
                String[] lineSplit = line.split(" ");
                userData.put("email", lineSplit[0]);
                userData.put("password", lineSplit[1]);
                users.add(userData);
            }
            //clsoe the bufferedReader
            bufferedReader.close();
            return users;
        }catch(IOException e){
            e.printStackTrace();
            return users;
        }
    }
}
