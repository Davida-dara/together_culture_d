package org.example;

import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Conversation {
    private String text;
    private String discussion;
    //Segment all possible questions into topic of discussion
    private final static int BOOKINGS = 0;
    private final static int EVENTS = 1;
    private final static int MEMBERSHIPS = 2;
    private final static int MEMBERS = 3;
    private connection TGCDB;
    private static ArrayList<String> words = new ArrayList<String>();
    public static String[] topics = {"bookings", "events", "memberships", "members", "workspaces"};


    public void Conversation(String text) {
        //Database Connection
        //Call and instantiate the Together Culture Database
        TGCDB = new connection();
        TGCDB.getConnected();
        //  TGCDB.getDisconnected();

        //text stores the user's input as a lowercase String
        this.text = text.toLowerCase();
        populate_array_list();
        chatbot_logic();


        //create chatbot logic
        //select the topic of discussion
        //select if statement based on user input

    }

    //populate the arraylist created with a list of all the words in the user's sentence

    public void populate_array_list() {
        //splits the text input into individual words using spaces as delimiters

        String[] temp = text.split(" ");
        for (int i = 0; i < temp.length; i++) {
            //adds these words to the words Array list
            words.add(temp[i]);
        }
    }

    public void chatbot_logic() {
        //identifies the topic of discussion e.g. bookings , events etc..
        discussion = select_topic();
        //Case statement to determine the next course of action
        switch (discussion) {
            case "booking":
                //searches for key words like "event" or "workspace" to refine the booking type
                for (int m = 0; m < words.size(); m++) {
                    String tempWord = words.get(m).toLowerCase();
                    if (tempWord.contains("event")) {
                        //event booking sql statements
                    } else if ((tempWord.contains("workspace")) || (tempWord.contains("space"))) {
                        //workspace booking sql statements
                    }
                }
                break;
            case "events":
                //calls the  chatbpt_event_rules method
                System.out.println(chatbot_event_rules());
                //event booking sql statements
                break;
            case "memberships":
                //calls the memberships method
                System.out.println(membership_rules());
                break;
            case "members":
                //members sql statements
                break;
        }
    }

    public String chatbot_event_rules() {
        //if the input contains "how many" it calls the how_many_events method
        if (text.contains("how many")) {
            return how_many_events();
        }
        //if the input contains "when is" it calls locate_date()
        if (text.contains("when is")) {
            return locate_date();
        }
        return null;
    }

    public String locate_date() {
        String output = "";
        String query1, query2, query3;
        int rowCount = 0;
        //get the event name
        String event_name = "Tech talks";

        //get the total number of events in the table
        query1 = "SELECT count(*) AS events_count FROM events;";
        ResultSet result1 = TGCDB.ExecuteQuery(query1);
        try {
            while (result1.next()) {
            rowCount = Integer.parseInt(result1.getString("events_count"));
            }
            System.out.println(rowCount);
        } catch (SQLException e) {
           // ..//  throw Exception (e);
            //    return sqlErrorMessage();
        }

        //Create an array storing the list of events in the table
        query3 = "SELECT event_name FROM events;";
        ResultSet result3 = TGCDB.ExecuteQuery(query3);
        int loopCounter = 0;
        String[] eventList = new String[rowCount];
        try {
            while (((result3.next()) && (loopCounter < rowCount))) {
                eventList[loopCounter] = result3.getString("event_name");
                loopCounter++;
            }

            for (int i = 0; i < eventList.length; i++) {
                System.out.println(eventList[i]);
            }
        } catch (SQLException e) {
            return sqlErrorMessage();
        }

        //get event name
        String[] tempWrds = text.split("");
        for (String ename: eventList) {
            int index = Arrays.binarySearch(tempWrds, ename);
        }
     /*   for (int i = 0; i < eventList.length; i++) {
         if (text.contains(eventList[i])) {
             event_name = eventList[i];
         }
        } */

        query2 = "SELECT event_name, date_of_event  FROM events WHERE event_name = \"" + event_name + "\";";
      //  System.out.println(query2);
        ResultSet result2 = TGCDB.ExecuteQuery(query2);
        try {
            while (result2.next()) {
                output = result2.getString("event_name") + " is happening on ";
                output += result2.getString("date_of_event");
            }
        } catch (SQLException e) {
            return sqlErrorMessage();
        }
        return output;
    }

    public String how_many_events() {
        //Declaring Variables
        String output, query1, tmpOutput, query2;
        int rowCounter = 0;
        //First output response
        output = "We at Together culture, have numerous event's planned all year round. Here is a list of all our upcoming events";
        ///Query statements
        query1 = "SELECT event_name, DATE_FORMAT(date_of_event, '%W, %e %M, %Y') AS date " +
                "FROM events " + "WHERE date_of_event > CURRENT_DATE";
        ResultSet result1 = TGCDB.ExecuteQuery(query1);
        while (true) {
            try {
                while (result1.next()) {
                    rowCounter++;
                    tmpOutput = "";
                    //loops through the content in a row and stores them into a string variable
                    int loopCount = result1.getMetaData().getColumnCount();
                    for (int n = 1; n <= loopCount; n++) {
                        if (n == 1) {
                            tmpOutput += result1.getString(n) + " happening on ";
                        } else tmpOutput += result1.getString(n);
                    }
                    //2nd output response
                    output += "\n" + tmpOutput;
                }

                //3rd output response
                output += "\nThere are " + rowCounter + " upcoming events. Do let me know if you would like to book onto anyone of them.";
                //    System.out.println(output);
                return output;
            } catch (SQLException e) {
                //display error message
                return sqlErrorMessage();
            }
        }
        //  return null;
    }
    public String membership_types_rules(){
        //declaring the variables
        String outPut, gettingMembershipsQuery, tempOut;
        int rowCount = 0;
        outPut = "we have different types of memberships at together culture, here is a list of the memberships that we have";
        //query that will get the memberships in the database
        gettingMembershipsQuery ="SELECT membership_type" +"FROM memberships";
        ResultSet membershipTypeResult = TGCDB.ExecuteQuery(gettingMembershipsQuery);
        while (true){
            try{
                while(membershipTypeResult.next()){
                    rowCount++;
                    tempOut = "";
                    //loops through the content in a row and stores them into a String
                    int loopCount = membershipTypeResult.getMetaData().getColumnCount();
                    for(int i = 1; i <= loopCount; i++){
                        if( i == 1){
                            tempOut += membershipTypeResult.getString(i) + "available";
                        }else tempOut += membershipTypeResult.getString(i);
                    }
                    //2nd output response
                    outPut += "\n" + tempOut;
                }
                //3rd output response
                outPut += "\n There are " + rowCount + " memberships. Do let me know if you want to know the prices";
                return outPut;

            } catch(SQLException e){
                //display the error messaged
                return sqlErrorMessage();
            }
        }

    }

    public String membership_prices(){
        String outPut, gettingmembershipPriceQuery, tempOut;
        int rowCounter = 0;
        outPut = "Different memberships have different prices, here is a list of the membership prices:";
        //query that will get the membership_type name and the prices
        gettingmembershipPriceQuery = "SELECT membership_type, price_per_month"+"FROM memberships";
        ResultSet membershipPricesResult = TGCDB.ExecuteQuery(gettingmembershipPriceQuery);
        while(true){
            try{
                while(membershipPricesResult.next()){
                    rowCounter++;
                    tempOut = "";
                    //loops through the content in a row and stored them into a string
                    int loopCount = membershipPricesResult.getMetaData().getColumnCount();
                    for(int i = 1; i <= loopCount; i++){
                        if( i == 1){
                            tempOut += membershipPricesResult.getString(i) + "GBP";
                        }else tempOut += membershipPricesResult.getString(i);
                    }
                    //2nd output response
                    outPut += "\n"+ tempOut;

                }

                //3rd output
                outPut +="\n Some membership types do not have a price, this is for the user to choose the amount they want to pay" ;
                return outPut;

            }catch(SQLException e){
                //display the sql error message
                return sqlErrorMessage();
            }

        }
    }
    public String free_memberships(){
        String outPut, gettingFreeMembershipsQuery, tempOut;
        int rowCounter = 0;
        outPut = "These are the membership types that dont have a set subscription price:";
        //query that will get the data needed from the database
        gettingFreeMembershipsQuery = "SELECT membership_type, price_per_month FROM memberships WHERE price_per_month IS NULL";
        ResultSet freeMembershipsResults = TGCDB.ExecuteQuery(gettingFreeMembershipsQuery);
        while(true){
            try{
                while(freeMembershipsResults.next()) {


                    rowCounter++;
                    tempOut = "";
                    //looping through the content in a row and store them into a string
                    int loopCount = freeMembershipsResults.getMetaData().getColumnCount();
                    for (int i = 1; i <= loopCount; i++) {
                        if (i == 1) {
                            tempOut += freeMembershipsResults.getString(i) + "GBP";
                        } else tempOut += freeMembershipsResults.getString(i);
                    }
                    //2nd output response
                    outPut += "\n" + tempOut;
                }
                //3rd response
                return outPut;


            }catch(SQLException e){
                return sqlErrorMessage();
            }
        }

    }
    public String become_member(){
        String websiteURL = "https://www.togetherculture.com/about-our-membership";
        return "To become a Together Culture member, please visit our website by clicking the following link"
                + websiteURL;

    }

    public String membership_rules(){
        if(text.contains("membership types")){
            //calls the related method into
            System.out.println(membership_types_rules());

        }
        if(text.contains("membership prices")){
            System.out.print(membership_prices());

        }
        if(text.contains("become member")){
            System.out.println(become_member());

        }
        if(text.contains("show free memberships")){
            System.out.println(free_memberships());
        }
        if(text.contains("What memberships are free")){
            System.out.println(free_memberships());
        }
        return general_response();

    }

    public String member_info_rule(){
        String websiteURL = "https://www.togetherculture.com/blog";
        return "To find out more about the people in our community, please follow the following link" + websiteURL;
    }
     public String accessing_member_info (){
        if(text.contains("how many members")){
            System.out.println(member_info_rule());
        }
        if(text.contains("who are the members")){
            System.out.println(member_info_rule());
        }
        return general_response();
     }


    public String sqlErrorMessage() {
        return "Sorry there was an error loading your response, could you retype your question?";
    }


    //method to determine the topic of discussion /focus
    public String select_topic() {
        String focus = "";
        String[] words = text.split(" ");

        //search algorithym used to determine the topic of conversation
        for (int i = 0; i < topics.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if ((words[j].length() > 3) && (topics[i].contains(words[j]))) {
                    focus = topics[i];
                    System.out.println(focus);
                    return topics[i];
                }
            }
        }
        System.out.println(focus);
        return null;
    }


    public String general_response() { //general response for when the chatbot does not understand a user's question
        return "I'm not quite sure what you mean? can you rephrase your question.";
    }
}
