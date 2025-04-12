package com.example.module03_basicgui_db_interface;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnDBOpsCopy {


    final String MYSQL_SERVER_URL = "jdbc:mysql://csc311sandoval27.mysql.database.azure.com/";
    final String DB_URL = MYSQL_SERVER_URL + "DBname";
    final String USERNAME = "sandef3";
    final String PASSWORD = "FARM123!";

    public boolean connectToDatabase() {
        boolean hasRegistredUsers = false;


        //Class.forName("com.mysql.jdbc.Driver");
        try {
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS DBname");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users1 ("
//                    +"id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "fname VARCHAR(200) NOT NULL,"
                    + " lname VARCHAR(200) NOT NULL UNIQUE,"
                    + "department VARCHAR(200),"
                    + "major VARCHAR(200)"

                    + ")";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasRegistredUsers;
    }

    //Method to add data to the database using the textfields within the UI
    public void insertUser(String fname, String lname, String department, String major) {

        try {

            //establish connection
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //create query
            String sql = "INSERT INTO users1 (fname, lname, department, major) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, department);
            preparedStatement.setString(4, major);

            //strategy for checking id a change occurs
            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A new user was inserted successfully.");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method to update users data within the table using the last name as the condition
    public void editUser(String fname, String lname, String department, String major) {
        try {
            //Establish connection
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //create query based off of the last name of the query
            String sql = "UPDATE users1  set fname = ?, department = ?, major = ? WHERE lname = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, department);
            preparedStatement.setString(3, major);
            preparedStatement.setString(4, lname);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            }
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //method to delete a row with the database using the last and first name that corresponds to the target row
    public void deleteUser(String fname, String lname) {
        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            String sql = "DELETE FROM users1 WHERE fname = ? AND lname = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            }
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //method to show all the rows within the database
    public List<Person> listAllUsers() {
        List<Person> users = new ArrayList<>();
        try {

            //establish connection
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select all from QUERY call
            String sql = "SELECT * FROM users1 ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                String department = resultSet.getString("department");
                String major = resultSet.getString("major");
                System.out.println("First Name: " + fname + ", Last Name: " + lname + ", Department: " + department + ", Major: " + major);

                //Populate a list to return back to the UI
                users.add(new Person(fname,lname,department,major));
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
//    public  void queryUserByName(String name) {
//        try {
//            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//            String sql = "SELECT * FROM users1 WHERE name = ?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String email = resultSet.getString("email");
//                String phone = resultSet.getString("phone");
//                String address = resultSet.getString("address");
//                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address);
//            }
//
//            preparedStatement.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



