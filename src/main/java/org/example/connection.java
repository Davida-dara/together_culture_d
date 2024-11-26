package org.example;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;


public class connection {
    private static String dbUrl;
    private static String adminName;
    private static String adminPass;
    private static Connection db;

    //database Initializiation
    public connection (String dbUrl, String adminName, String adminPass) {
        this.dbUrl = dbUrl;
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public boolean getConnected() {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            this.db = DriverManager.getConnection(dbUrl, adminName, adminPass);

            //Successsfull connection message
            //System.out.println("Your Together Culture Database has been successfully connected!");
            return true;
        }catch ( Exception e) {
            e.printStackTrace();
            //System.out.println("Oops...an error occured...could not connect your database...");
            return false;
        }
    }

    //close and disconnect tgc database
    public void getDisconnected() {
        try {
            this.db.close();
            System.out.println("Database has successfully been disconnected");
        }catch (Exception e) {
            System.out.println("Error...the database was not disconnected...please try again.");
        }
    }

}
