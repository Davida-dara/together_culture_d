package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public static String[] topics = {"bookings", "events", "memberships", "members"};


    public void Conversation(String text) {
        //Database Connection
        //Call and instantiate the Together Culture Database
        TGCDB = new connection();
        TGCDB.getConnected();
        //  TGCDB.getDisconnected();

        this.text = text;
        populate_array_list();
        chatbot_logic();


        //create chatbot logic
        //select the topic of discussion
        //select if statement based on user input

    }

    //populate the arraylist created with a list of all the words in the user's sentence
    public void populate_array_list() {
        String[] temp = text.split(" ");
        for (int i = 0; i < temp.length; i++) {
            words.add(temp[i]);
        }
    }

    public void chatbot_logic() {
        //determine the text focus
        discussion = select_topic();
        //Case statement to determine the next course of action
        switch (discussion) {
            case "booking":
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
                String t = chatbot_event_rules();
                //event booking sql statements
                break;
            case "memberships":
                //memberships sql statements
                break;
            case "members":
                //members sql statements
                break;
        }
    }

    public String chatbot_event_rules() {
        if (text.contains("how many")) {
            return how_many_events();
        }
        if (text.toLowerCase().contains("when is")) {
            return locate_date();
        }

        return null;
    }

    public String locate_date() {
        String query1, output;
        String query2, query3;
        int rowCount;
        //get the event name
        String event_name = "Tech talks";

        //get the total number of events in the table
        query1 = "SELECT count(*) AS events_count FROM events;";
        ResultSet result1 = TGCDB.ExecuteQuery(query1);
        try {
            rowCount = Integer.parseInt(result1.getString("events_count"));
        } catch (SQLException e) {
            return sqlErrorMessage();
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


        query2 = "SELECT event_name, date_of_event  FROM events WHERE event_name = " + event_name;
        ResultSet result2 = TGCDB.ExecuteQuery(query2);
        try {
            output = result2.getString("event_name") + " is happening on ";
            output += result2.getString("events_count");
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
