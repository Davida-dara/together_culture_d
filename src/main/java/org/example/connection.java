package org.example;

import java.security.spec.ECField;
import java.sql.*;


public class connection {
    private static String dbUrl;
    private static String adminName;
    private static String adminPass;
    private static Connection db;

    //database Initializiation
    public  connection () {
        this.dbUrl = "jdbc:mysql://localhost:3306/together_culture";
        this.adminName = "tgcadmin";
        this.adminPass = "1234tgc";
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
    public boolean updateSql(String statement) {
        try {
            Statement sqlStat = this.db.createStatement();
            sqlStat.executeUpdate(statement); //update the database with commands like CREATE, ALTER etc
            return true;
        }catch (Exception e) {
            return false;
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
          //  throw new RuntimeException(e);
            System.out.println("Could not execute the query statement. please recheck the statement");
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
