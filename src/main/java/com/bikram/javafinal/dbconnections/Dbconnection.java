package com.bikram.javafinal.dbconnections;

import com.bikram.javafinal.Models.LoginResult;
import com.bikram.javafinal.Models.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import  java.sql.*;

public class Dbconnection {
    Statement stm;
    static Connection conn;


    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String username = "user";
            String password = "pass";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafinal",username,password);
            stm = conn.createStatement();
            System.out.println("Connected");
        } catch (Exception e){
            System.out.println("cannot connect");
            e.printStackTrace();
        }
    }
    public static LoginResult login(String username, String password) {
        try {
            // check if provided username and password are correct
            PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()) {
                return new LoginResult(true,rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new LoginResult(false);
        }
        return  new LoginResult(false);
    }
    public static void signUp(String name,String username, String password) throws Exception{


        PreparedStatement pst = conn.prepareStatement("INSERT INTO Users(name,username,password) VALUES (?,?,?)");
        pst.setString(1,name);
        pst.setString(2,username);
        pst.setString(3,password);

        pst.executeUpdate();
    }
    public static void registerStudent(Students s) throws Exception {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO Student(name,roll,maths,science,social,english,nepali) VALUES (?,?,?,?,?,?,?)");
        pst.setString(1,s.getName());
        pst.setInt(2,s.getRoll());
        pst.setInt(3,s.getMaths());
        pst.setInt(4,s.getScience());
        pst.setInt(5,s.getSocial());
        pst.setInt(6,s.getEnglish());
        pst.setInt(7,s.getNepali());
        pst.executeUpdate();
    }
    //    void getAllData() throws Exception{
    //        final String command = "SELECT * FROM Students";
    //        ResultSet rs =stm.executeQuery(command);
    //        while (rs.next()){
    //            System.out.println(rs.getString(1));
    //        }
    //    }
    public static ObservableList<Students> getAllStudents() throws Exception {

        ObservableList<Students> list = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String statement = "SELECT * FROM student";
        ResultSet rs = st.executeQuery(statement);
        while (rs.next()) {
            list.add(new Students(rs.getInt("roll"),rs.getString("name"),rs.getInt("maths"),rs.getInt("science"),rs.getInt("social"),rs.getInt("english"),rs.getInt("nepali")));
        }
        return list;
    }
}
