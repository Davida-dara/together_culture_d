package org.example;

import java.security.spec.ECField;
import java.sql.*;


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
            System.out.println("Your Together Culture Database has been successfully connected!");
            return true;
        }catch ( Exception e) {
            e.printStackTrace();
            //System.out.println("Oops...an error occured...could not connect your database...");
            return false;
        }
    }

    //method to Update/ edit the sql database sql commands
    public void updateSql(String statement) {
        try {
            Statement sqlStat = this.db.createStatement();
            sqlStat.executeUpdate(statement); //update the database with commands like CREATE, ALTER etc
        }catch (Exception e) {
            System.out.println("Could not execute sql command");
        }
    }

    //method to execute sql query statements
    public ResultSet ExecuteQuery(String statement) {
        try {
            Statement sqlStat = this.db.createStatement();
            ResultSet rs = sqlStat.executeQuery(statement);
            System.out.println("SQL statement executed successfully");
            return rs;
        } catch (Exception e) {
            System.out.println("Could not execute the query statement. please rechek the statement");
        }
        return null;
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
